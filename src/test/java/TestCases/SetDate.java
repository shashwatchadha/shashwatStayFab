package TestCases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseSetup.Setup;
import pages.HotelsInDelhiPage;

public class SetDate extends Setup {

	
	
	
	
	
	
	
	//This method will navigate to the fabHotels url, will click on check in and will navigate to the passed date.
	//Date should be in format DD-MONTH-YYYY (eg. 30-March-2018)
	//Also, If we pass past date then it will fail and will give message as "invalid date"
	//Parameter date need to be passed in textng.xml
	
	@Parameters({"date"})
	
	@Test
	public void setCheckInDate(String date)
	{
		
		
		
		
		HotelsInDelhiPage obj = new HotelsInDelhiPage();
		obj.navigateToPage().waitForPageLoad(30).clickCheckIn();
		obj.setDate(date);}
		
		
		
		
		

	
	
	
}
