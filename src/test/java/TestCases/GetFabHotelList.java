package TestCases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import baseSetup.DriverManager;
import baseSetup.Setup;
import pages.FabHotelsLandingPage;
import pages.HotelListPage;
import pages.LocationsPage;
import utilities.ActionUtility;
import utilities.ExcelManager;

public class GetFabHotelList extends Setup {

	// This method will get list of all the locations under our locations
	// section and will also get the names of the hotels in those locations.
	// In the end this method will right all the details in excel file
	@Test
	public void getLocationAndHotels() {

		List<WebElement> hotelsList = null;
		HashMap<String, ArrayList<String>> locationHotelMap = new HashMap<String, ArrayList<String>>();
		

		FabHotelsLandingPage landingPageObj = new FabHotelsLandingPage();
		LocationsPage locationPageObject = landingPageObj.navigateToFabHotels().waitForPageLoad(30).goToOurLocation();
		int nummberOfLocations = locationPageObject.waitForLocationList(20).getLocationCount();

		for (int locationIndex = 1; locationIndex <= nummberOfLocations; locationIndex++) {
			ArrayList<String> hotelsName = new ArrayList<String>();
			String tempLocation = locationPageObject.getLocation(locationIndex);
			HotelListPage hotelPage = locationPageObject.clickLocation(locationIndex);
			hotelsList = hotelPage.getHotelsList();
			if (hotelsList.size() > 0) {
				for (WebElement hotel : hotelsList) {
					hotelsName.add(hotel.getText());
				}
				locationHotelMap.put(tempLocation, hotelsName);
			} else if (hotelsList.size() == 0) {
				hotelsName.add("No hotel is present for this location Right now.");
				locationHotelMap.put(tempLocation, hotelsName);
			}
			landingPageObj.goToOurLocation().waitForLocationList(20);

			if (locationIndex > nummberOfLocations / 3) {
				ActionUtility.scroll(locationIndex * 45);
				// JavascriptExecutor js = (JavascriptExecutor)
				// DriverManager.getDriver();
				// js.executeScript("window.scrollBy(0," + (locationIndex * 50)
				// + ")", "");
			}
		}
		ExcelManager excelObj = new ExcelManager(
				System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\Book1.xlsx");
		excelObj.setCellData("LocationList", locationHotelMap);
	}
}
