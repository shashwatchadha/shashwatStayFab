package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseSetup.DriverManager;
import utilities.ActionUtility;

public class HotelListPage {



	By hotelsList = By.xpath("//div[@class='tabs__content']//article//div[@class='fleft hlc__title']/h3/a");
	

	
	//This is the page load condition for the hotel list page
	public HotelListPage waitForHotelList(int timeOutInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(hotelsList));
		return this;
	}
	

	//This method will return the list of hotels.
	public List<WebElement> getHotelsList()
	{
		return ActionUtility.getWebElementList(hotelsList);
	}
	
	
	//This method will check the presence of the hotels.
	public boolean isHotelPresent()
	{
		if(DriverManager.getDriver().findElements(hotelsList).size()>0)
return true;

else
	return false;
	}
	
	


}
