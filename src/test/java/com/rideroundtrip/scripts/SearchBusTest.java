package com.rideroundtrip.scripts;

import org.testng.annotations.Test;

import com.rideroundtrip.features.SearchRefBusFeature;
import com.rideroundtrip.generic.FrameworkConfig;
import com.rideroundtrip.generic.baseLibrary;

public class SearchBusTest extends baseLibrary
{
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

