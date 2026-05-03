package com.rideroundtrip.generic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Resolves framework configuration from system properties, environment variables, and defaults.
 */
public final class FrameworkConfig
{
    /** Classpath resource containing default framework settings. */
    private static final String FRAMEWORK_PROPERTIES = "framework.properties";
    /** Singleton instance reused across the framework. */
    private static final FrameworkConfig INSTANCE = new FrameworkConfig();

    /** Holds the properties loaded from the framework resource file. */
    private final Properties properties = new Properties();

    /** Loads the configuration file once when the singleton is created. */
    private FrameworkConfig()
    {
        loadProperties();
    }

    /**
     * Returns the shared configuration accessor.
     */
    public static FrameworkConfig getInstance()
    {
        return INSTANCE;
    }

    /**
     * Reads a configuration value with system properties and environment variables taking precedence.
     */
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

    /**
     * Resolves a value from a TestNG parameter first, then from configuration, then from a fallback.
     */
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

    /**
     * Parses a long configuration value while falling back safely when parsing fails.
     */
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

    /**
     * Parses a boolean configuration value while falling back safely when absent.
     */
    public boolean getBoolean(String key, boolean defaultValue)
    {
        String value = get(key);
        if (!isPresent(value)) {
            return defaultValue;
        }

        return Boolean.parseBoolean(value);
    }

    /**
     * Loads the framework property file from the test runtime classpath.
     */
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

    /**
     * Checks whether a candidate string contains a usable non-blank value.
     */
    private boolean isPresent(String value)
    {
        return value != null && !value.trim().isEmpty();
    }
}
