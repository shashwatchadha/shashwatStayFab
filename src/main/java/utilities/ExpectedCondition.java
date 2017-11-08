package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseSetup.DriverManager;

public class ExpectedCondition {
	
	
	
	
	
	
	
	
	private static WebDriverWait wait;
	
	
	
	
	
	
	
	public static void elementVisibilityWait(WebElement element, int time)
	{
		wait = new WebDriverWait(DriverManager.getDriver(), time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	
	
	
	
	
	
	
	

}
