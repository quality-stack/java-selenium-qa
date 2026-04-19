package com.rideroundtrip.generic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class FrameworkConfig
{
    private static final String FRAMEWORK_PROPERTIES = "framework.properties";
    private static final FrameworkConfig INSTANCE = new FrameworkConfig();

    private final Properties properties = new Properties();

    private FrameworkConfig()
    {
        loadProperties();
    }

    public static FrameworkConfig getInstance()
    {
        return INSTANCE;
    }

    public String get(String key)
    {
        String systemValue = System.getProperty(key);
        if (isPresent(systemValue)) {
            return systemValue.trim();
        }

        String envKey = key.toUpperCase().replace('.', '_');
        String environmentValue = System.getenv(envKey);
        if (isPresent(environmentValue)) {
            return environmentValue.trim();
        }

        return properties.getProperty(key, "").trim();
    }

    public String resolve(String key, String parameterValue, String defaultValue)
    {
        if (isPresent(parameterValue)) {
            return parameterValue.trim();
        }

        String configValue = get(key);
        if (isPresent(configValue)) {
            return configValue;
        }

        return defaultValue == null ? "" : defaultValue.trim();
    }

    public long getLong(String key, long defaultValue)
    {
        String value = get(key);
        if (!isPresent(value)) {
            return defaultValue;
        }

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException exception) {
            return defaultValue;
        }
    }

    private void loadProperties()
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            return;
        }

        try (InputStream inputStream = classLoader.getResourceAsStream(FRAMEWORK_PROPERTIES)) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load " + FRAMEWORK_PROPERTIES, exception);
        }
    }

    private boolean isPresent(String value)
    {
        return value != null && !value.trim().isEmpty();
    }
}
