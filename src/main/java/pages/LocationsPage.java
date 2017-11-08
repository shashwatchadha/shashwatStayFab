package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseSetup.DriverManager;
import utilities.ActionUtility;

public class LocationsPage {

	
	
	
	
	
	
By locationList = By.xpath("//div[@class='locations-wrap']/a//h3[@class='ltb__title']");



	
	
	
	
	
	//This is page load condition for Locations page
	
	public LocationsPage waitForLocationList(int timeOutInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locationList));
		return this;
	}
	
	
	//This method will return the count of the locations
	public int getLocationCount()
	{
		return ActionUtility.getWebElementList(locationList).size();
	}
	
	//This method will click on the location at given index.
	public HotelListPage clickLocation(int index)
	{
		ActionUtility.click(By.xpath("//div[@class='locations-wrap']/a["+index+"]"));
		return new HotelListPage();
	}
	
	
	//This method will retur nthe name of the location at given index.
	public String getLocation(int index)
	{
		return ActionUtility.getText(By.xpath("//div[@class='locations-wrap']/a["+index+"]"));
	}
	
	
	
	
}
