package com.rideroundtrip.scripts;
import com.rideroundtrip.generic.baseLibrary;
import org.testng.annotations.Test;

import com.rideroundtrip.features.SearchRefBusFeature;

public class SearchBusTest extends baseLibrary {

	  @Test (priority=1)
	  public void searchBuses() throws InterruptedException  
	  {
		  
		  SearchRefBusFeature sf = new SearchRefBusFeature(driver);
		  sf.enterSource();
		  sf.selectSource();
		  sf.enterDestination();
		  sf.selectDestination();
		  sf.clickSearch();
	  }
}
