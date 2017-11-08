package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import baseSetup.DriverManager;
import utilities.ActionUtility;
import utilities.DatePicker;

public class HotelsInDelhiPage {

	
	
	
	
	
	

	
	
	
	public SelectHotelPage findHotel()
	{
		ActionUtility.click(By.xpath("//button[text()='Find FabHotels']"));
		return new SelectHotelPage();
	}
	
	
	
	
	
	//This method will navigate us to hotels in delhi page
	public HotelsInDelhiPage navigateToPage()
	{
		DriverManager.getDriver().navigate().to("https://www.fabhotels.com/hotels-in-new-delhi");
		return new HotelsInDelhiPage();
	}
	
	
	//This is the page load condition for hotels in delhi page
	public HotelsInDelhiPage waitForPageLoad(int timeOutInSeconds)
	{
		FluentWait<WebDriver> wait = new WebDriverWait(DriverManager.getDriver(),timeOutInSeconds).ignoring(StaleElementReferenceException.class);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Check In']")));
		return new HotelsInDelhiPage();
	}
	
	
	
	//This method will click on checkIn
	public void clickCheckIn()
	{
		
		ActionUtility.staleClick(DriverManager.getDriver().findElement(By.xpath("//div[text()='Check In']")));
		
		
	}
	
	//This method will click on forward button on calender.
	public void clickForwardArrow(int numberOfClicks)
	{
		for(int i=0;i<numberOfClicks;i++)
		{
			ActionUtility.click(By.xpath("//div[@class='calendar']//th[@class='icon-Arrow calendar__arrow-icon']"));
			//DriverManager.getDriver().findElement(By.xpath("//div[@class='calendar']//th[@class='icon-Arrow calendar__arrow-icon']")).click();
		}
	}
	
	
	//This method will count the number of clicks need to navigate to given date
	public int numberOfClick(String date)
	{
		
		
		DatePicker.getTargerDateMonthAndYear(DatePicker.changeDateFormat(date));
		DatePicker.getCurrentDateAndYear();
		int numberOfClicks = DatePicker.numberOfClicks();
		
		if(numberOfClicks<0)
		{
			Assert.fail("Invalid Date");
		}

		return numberOfClicks;
	}
	
	
	//This method will click on the required day
	public void selectDay(int day)
	{
		
		ActionUtility.click(By.xpath("//div[@class='calendar']//div[text()='"+day+"']"));
	}
	
	
	//This method will select the date
	public void setDate(String date)
	{
		clickForwardArrow(numberOfClick(date));
		selectDay(DatePicker.getTargetDay());
		
	}
	
	
	
	
}
