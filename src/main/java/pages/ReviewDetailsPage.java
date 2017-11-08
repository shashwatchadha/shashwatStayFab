package pages;

import java.awt.Desktop.Action;
import java.util.HashMap;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseSetup.DriverManager;
import utilities.ActionUtility;

public class ReviewDetailsPage {

	public static HashMap<String, Double> costMap = new HashMap<String, Double>();




	By totalCost = By.xpath("//div[text()='Total']//following-sibling::div");

	public ReviewDetailsPage waitForHotelList(int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Review Details']")));
		return this;
	}

	// This method will save the intial total amount in hashmap.
	public void getInitialTotal() {
		String initialTotal = ActionUtility.getText(totalCost);
		costMap.put("initialTotal", Double.parseDouble(initialTotal));
	}

	// This methd will sate the total cost after applying coupon in hash map
	public void getTotalAfterCoupon() {
		String totalAfterCoupon = ActionUtility.getText(totalCost);
		costMap.put("totalAfterCoupon", Double.parseDouble(totalAfterCoupon));
	}

	// THis method will set discount code
	public void setCouponCode(String code) {
		ActionUtility.scroll(400);
		// JavascriptExecutor js = (JavascriptExecutor)
		// DriverManager.getDriver();
		// js.executeScript("window.scrollBy(0,400)", "");
		//

		ActionUtility.setData(By.xpath("//input[@placeholder='Enter Coupon Code']"), code);

		ActionUtility.click(By.xpath("//button[text()='Apply']"));

	}
	
	
	
	
	
	public void verifyCouponApplied(String code)
	{
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='payment__review__coupon__text']")));
		
		if(!ActionUtility.getElement(By.xpath("//div[@class='payment__review__coupon__text']")).getText().equals(code))
			Assert.fail("Coupon: "+code+" is not applied");
	
	}
	
public void verifyCouponRemoved(String code)
{
	ActionUtility.click(By.xpath("//button[text()='Edit']"));
	ActionUtility.scroll(400);
	if(ActionUtility.getWebElementList(By.xpath("//div[@class='payment__review__coupon__text']")).size()>0)
	{
		if(!ActionUtility.getElement(By.xpath("//div[@class='payment__review__coupon__text']")).equals(code))
			Assert.fail("Coupon: "+code+" is not removed");
	}
}
	// This method will click continue after coupon has been added
	public void clickContinue() {
		ActionUtility.click(By.xpath("//button[text()=' Continue ']"));

	}

	// This method will set the client details and willl take us to payment page
	public void setDetails(String fullName, String email, String mobileNumber) {

		ActionUtility.setData(By.xpath("//label[contains(text(),'Full name')]//following-sibling::input"), fullName);
		ActionUtility.setData(By.xpath("//label[contains(text(),'Email')]//following-sibling::input"), email);
		ActionUtility.setData(
				By.xpath("//label[contains(text(),'Mobile')]//following-sibling::div//input[@type='tel']"),
				mobileNumber);
		// ActionUtility.setData(By.xpath(""), "");
		//
		// ActionUtility.setData(fullNameField, fullName);
		// ActionUtility.setData(emailAddress, email);
		// ActionUtility.setData(mobileNumberField, mobileNumber);
		// selectTCbox();

		ActionUtility.scroll(400);
		// JavascriptExecutor js = (JavascriptExecutor)
		// DriverManager.getDriver();
		// js.executeScript("window.scrollBy(0,400)", "");
		ActionUtility.click(By.xpath("//button[text()='Proceed to Pay']"));
		// ActionUtility.clickElement(proceed);
	}

	public void selectTCbox() {
		if (!ActionUtility.getElement(By.xpath("//span[contains(text(),'I agree with')]/../div")).isSelected()) {

			ActionUtility.click(By.xpath("//span[contains(text(),'I agree with')]/../div"));
			// ActionUtility.clickElement(termConditionCheck);
		}
	}

	// This method willl click on remove coupon button
	public void removeCoupon() {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("window.scrollBy(0,400)", "");
		ActionUtility.click(By.xpath("//button[contains(text(),'Remove')]"));
	}

	// This method will verify the pay button after user select pay ae hotel
	public void verifyPayButton() {
		String payButtonText = ActionUtility.getText(By.xpath("//div[@class='payment__card__total__continue']/button"));
		String[] splitText = payButtonText.split(" ");
		String afterRemoveCpnPrice = splitText[2].replace(",", "");
		costMap.put("afterRmvCpnPayNow", Double.parseDouble(afterRemoveCpnPrice));

		if (costMap.get("afterRmvCpnPayNow") != costMap.get("initialTotal")
				&& costMap.get("afterRmvCpnPayNow") == costMap.get("totalAfterCoupon"))
			Assert.fail("Amount shown on the pay button should be: " + costMap.get("totalAfterCoupon")
					+ ",but it is showing: " + costMap.get("afterRmvCpnPayNow"));

	}

	// THis method will verify pay at hotel button after user select pay at
	// hotel
	public void verifyPayAtHotelButton() {
		String payButtonText = ActionUtility.getText(By.xpath("//div[@class='payment__payAtHotel ']/button"));
		String[] splitText = payButtonText.split(" ");
		String afterRemoveCpnPrice = splitText[2].replace(",", "");
		costMap.put("aftrRmvPayHotel", Double.parseDouble(afterRemoveCpnPrice));

		if (costMap.get("aftrRmvPayHotel") != costMap.get("initialTotal")
				&& costMap.get("aftrRmvPayHotel") == costMap.get("totalAfterCoupon"))
			Assert.fail("Amount shown on the pay button should be: " + costMap.get("totalAfterCoupon")
					+ ",but it is showing: " + costMap.get("aftrRmvPayHotel"));

	}

	// THis method will verify review details after user select pay at hotel
	public void verifyReviewDetails() {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("window.scrollBy(0,-600)", "");

		ActionUtility.click(By.xpath("//heading[text()='Review details']"));
		String reviewPageText = ActionUtility
				.getText(By.xpath("//span[text()='Total Amount : ']//following-sibling::span"));
		String[] splitText = reviewPageText.split(" ");
		String afterRemoveCpnPrice = splitText[1].replace(",", "");
		costMap.put("reviewPagePrice", Double.parseDouble(afterRemoveCpnPrice));

		if (costMap.get("reviewPagePrice") != costMap.get("initialTotal")
				&& costMap.get("reviewPagePrice") == costMap.get("totalAfterCoupon"))
			Assert.fail("Amount shown on the pay button should be: " + costMap.get("totalAfterCoupon")
					+ ",but it is showing: " + costMap.get("reviewPagePrice"));

	}

}
