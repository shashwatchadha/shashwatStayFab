package baseSetup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;




public class DriverManager {

	
	
	
	
	
private static AndroidDriver<WebElement> driver = null;
	
	private static DesiredCapabilities capabilities = new DesiredCapabilities();
	

	
	//This method is used to access the driver object in whole code
	public static AndroidDriver<WebElement> getDriver()
	{
		return DriverManager.driver;
	}
	
	//THis method is used to set desired capabilities
	public static DesiredCapabilities setDesiredCapabilities()
	{
	
		 	capabilities.setCapability("deviceName", "Android");
		    capabilities.setCapability("platformName", "Android");
		    capabilities.setCapability("platformVersion", "6.0.1");
		    capabilities.setBrowserName("chrome");
		    capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		    
		    return capabilities;
	}
	
	
	//this method is used to initiate android driver.
	public static void initiateAndoridDriver(String appiumURL)
	{
		
		try {
			driver = new AndroidDriver<WebElement>(new URL(appiumURL), DriverManager.setDesiredCapabilities());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//this method is used to set implicit wait
	public static void setDefaultImplicitWait(int timeInSeconds)
	{
		DriverManager.getDriver().manage().timeouts().implicitlyWait(timeInSeconds,TimeUnit.SECONDS);
	}

	//this method is used to destroy the driver object.
	public static void tearDownDriver()
	{
		if(DriverManager.driver!=null){
		DriverManager.driver.quit();
		driver=null;}
	}
	
	
	
	
	
	
	
}
