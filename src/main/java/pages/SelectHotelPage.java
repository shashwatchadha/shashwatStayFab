package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import baseSetup.DriverManager;
import utilities.ActionUtility;

public class SelectHotelPage {

	// This is the page load condition for select hotel page.
	public SelectHotelPage waitForHotelList(int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'FabHotel')]")));
		return this;
	}

	// This mwthod will select the hotel.
	// This method will fail the test if there is not hotel or hotel is sold out
	public void selectHotel(String hotel) {

		DriverManager.setDefaultImplicitWait(2);
		if (ActionUtility.getWebElementList(By.xpath("//a[contains(text(),'" + hotel + "')]")).size() == 0)
			Assert.fail("No Hotel is present for the said dates");
		if (ActionUtility
				.getWebElementList(
						By.xpath("//a[contains(text(),'" + hotel + "')]/../../../..//span[text()='Sold Out']"))
				.size() == 0)
			Assert.fail("Hotel" + hotel + " is sold out");
		DriverManager.setDefaultImplicitWait(30);

		List<WebElement> hotelList = ActionUtility.getWebElementList(
				By.xpath("//div[@class='tabs__content']//article//div[@class='fleft hlc__title']/h3/a"));
		int index = 0;
		for (; index < hotelList.size(); index++) {
			if (hotelList.get(index).getText().contains(hotel))
				break;
		}

		index++;
		ActionUtility.scroll(index * 400);
		// JavascriptExecutor js = (JavascriptExecutor)
		// DriverManager.getDriver();
		// js.executeScript("window.scrollBy(0," + (index * 400) + ")", "");

		ActionUtility.click(By.xpath("//a[contains(text(),'" + hotel + "')]/ancestor::article"));

	}

	// This method will wait for the book noow button and then will click on it
	public ReviewDetailsPage bookNow() {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Book now']")));
		ActionUtility.click(By.xpath("//button[text()='Book now']"));
		return new ReviewDetailsPage();
	}

}
