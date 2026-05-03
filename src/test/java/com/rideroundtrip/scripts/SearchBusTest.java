package com.rideroundtrip.scripts;

import org.testng.annotations.Test;

import com.rideroundtrip.features.SearchRefBusFeature;
import com.rideroundtrip.generic.FrameworkConfig;
import com.rideroundtrip.generic.baseLibrary;

/**
 * Exercises the public RedBus search flow using configurable route inputs.
 */
public class SearchBusTest extends baseLibrary
{
    /**
     * Performs a bus search with the configured origin and destination values.
     */
  @Test
  public void searchBuses()
  {
      FrameworkConfig config = FrameworkConfig.getInstance();
      SearchRefBusFeature searchFeature = new SearchRefBusFeature(driver);
      searchFeature.enterSource(config.resolve("bus.source", "", "Delhi"));
      searchFeature.selectSource();
      searchFeature.enterDestination(config.resolve("bus.destination", "", "Manali"));
      searchFeature.selectDestination();
      searchFeature.clickSearch();
  }
}
