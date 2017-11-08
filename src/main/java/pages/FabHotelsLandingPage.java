package pages;

import java.security.Key;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseSetup.DriverManager;
import utilities.ActionUtility;

public class FabHotelsLandingPage {

	@FindBy(xpath = "//span[@class='head__icon-hamburger icon-Menu']")
	WebElement hamBurger;

	@FindBy(xpath = "//span[text()='Our Locations']")
	WebElement ourLocation;

	public FabHotelsLandingPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	// This is the fabHotelsLandingPage load condition
	public FabHotelsLandingPage waitForPageLoad(int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='head__icon-hamburger icon-Menu']")));
		return new FabHotelsLandingPage();
	}
	
	
	public FabHotelsLandingPage pageLoadCondition(int timeOutInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//h1[@class='head__title']/a[@title='Fab Hotels']")));
		return new FabHotelsLandingPage();
	}
	
	

	// This method will navigate to fabhotels mobile website.
	public FabHotelsLandingPage navigateToFabHotels() {
		DriverManager.getDriver().navigate().to("https://www.fabhotels.com/");
		return new FabHotelsLandingPage();
	}

	// This method is used to set location on fabHotels landing page
	public HotelsInDelhiPage setLocation(String location) {

		ActionUtility.staleClick(ActionUtility.getElement(By.xpath("//input[@class='home__top-search-input']")));
		ActionUtility.setData(By.id("gp-input"), location);
		DriverManager.getDriver().findElement(By.id("gp-input")).sendKeys(Keys.ENTER);

		return new HotelsInDelhiPage();
	}

	// This method will navigate us to ourLocation page
	public LocationsPage goToOurLocation() {
		ActionUtility.staleClick(hamBurger);
		ourLocation.click();
		return new LocationsPage();
	}

}
