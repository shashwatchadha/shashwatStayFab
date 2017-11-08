package baseSetup;

import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utilities.LoadPropertiesFiles;

public class Setup {









	static Properties configProperties;
	
	




//This willl load the config file
	@BeforeSuite
	public void beforeSuite()
	{
		LoadPropertiesFiles.setConfigPropertiesPath(System.getProperty("user.dir") +"\\src\\test\\resources\\propertiesFiles\\config.properties");
		configProperties= LoadPropertiesFiles.loadPropertiesFile();
		
	}
	//this method will initiate the driver object before every class
	@BeforeClass
	public void beforeClass()
	{
		DriverManager.initiateAndoridDriver(configProperties.getProperty("appiumURL"));
		DriverManager.setDefaultImplicitWait(Integer.parseInt(configProperties.getProperty("implicitWait")));
	
	}
	
	//this method will run before every method and will tell which test is currently executing
	@BeforeMethod
	public void beforeMethod(Method m)
	{
		System.out.println("Begin execution of test method: "+m.getName());
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		if (result.getStatus() == ITestResult.FAILURE)
			System.out.println("Test failed");
		else if(result.getStatus() == ITestResult.SUCCESS)
			System.out.println("Test failed");
	}

	@AfterClass
	public void afterClass()
	{
		DriverManager.tearDownDriver();
		
	}
	
}
