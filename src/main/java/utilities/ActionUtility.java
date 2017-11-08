package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;


import com.gargoylesoftware.htmlunit.javascript.host.Iterator;

import baseSetup.DriverManager;
import baseSetup.Setup;

public class ActionUtility {

	
	
	
	
	
	
	
	//this class contains some common operation methods
	
	public static void staleClick(WebElement element) {
     
		
        int attempts = 0;
        while(attempts < 3) {
            try {
                element.click();
              
                break;
            } catch(StaleElementReferenceException e) {
            }
            catch(ElementNotVisibleException e){}
            attempts++;
        }
     
}
	
	public static  List<WebElement>  getWebElementList(By elementLocator)
	{
		return DriverManager.getDriver().findElements(elementLocator);
	}
	
	
	
	public static String getText(By locator)
	{
		return DriverManager.getDriver().findElement(locator).getText();
	}
	
	
	public static void click(By locator)
	{
		
		DriverManager.getDriver().findElement(locator).click();;
		
	}
	
	public static WebElement getElement(By locator)
	{
		return DriverManager.getDriver().findElement(locator);
	}
	
	
	
	public static void setData(WebElement element,String data)
	{
		element.sendKeys(data);;
	}
	
	public static void setData(By locator,String data)
	{
		
		DriverManager.getDriver().findElement(locator).sendKeys(data);
	}
	
	
	public static void scroll(int dimension)
	{
		
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("window.scrollBy(0," + dimension + ")", "");
	}
	
	
	
	
}
