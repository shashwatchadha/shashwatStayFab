package TestCases;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseSetup.Setup;
import pages.FabHotelsLandingPage;
import pages.HotelsInDelhiPage;
import pages.ReviewDetailsPage;
import pages.SelectHotelPage;
import utilities.ExcelManager;

public class ValidatePayAtHotel extends Setup {

	
	
	
	
	
	
	
	
	@Test(dataProvider="data")
	public void verifyPayments(HashMap<String,String> dataMap)
	{
		FabHotelsLandingPage obj = new FabHotelsLandingPage();
		HotelsInDelhiPage hotelPageObj=obj.navigateToFabHotels().pageLoadCondition(30).setLocation(dataMap.get("Location"));
		hotelPageObj.waitForPageLoad(20);
		hotelPageObj.clickCheckIn();
		hotelPageObj.setDate(dataMap.get("CheckInDate"));
		SelectHotelPage selectHotelObj = hotelPageObj.findHotel();
		selectHotelObj.waitForHotelList(30);
		selectHotelObj.selectHotel(dataMap.get("HotelName"));
		ReviewDetailsPage reviewObj = selectHotelObj.bookNow();
		reviewObj.getInitialTotal();
		reviewObj.setCouponCode(dataMap.get("Coupon"));
		reviewObj.verifyCouponApplied(dataMap.get("Coupon"));
		
		reviewObj.getTotalAfterCoupon();
		reviewObj.clickContinue();
		reviewObj.setDetails(dataMap.get("FullName"), dataMap.get("Email"),dataMap.get("PhoneNUmber"));
		reviewObj.removeCoupon();
		reviewObj.verifyPayButton();
		reviewObj.verifyPayAtHotelButton();
		reviewObj.verifyReviewDetails();
		reviewObj.verifyCouponRemoved(dataMap.get("Coupon"));
		
		
	}
	
	
	
	@DataProvider(name="data")
	public Object[][] getData(Method m )
	{
		String sheetName = m.getName();
		ExcelManager excelObj = new ExcelManager(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\Book1.xlsx");
	int rows=	excelObj.getRowCount(sheetName);
	int cells = excelObj.getColumnCount(sheetName);
		
	HashMap<String, String> map=null;
 		Object[][] data = new Object[rows-1][1];
		
		for (int rowNum = 2; rowNum <= rows; rowNum++)
		{
			map = new HashMap<String, String>();
			for (int colNum = 0; colNum <cells; colNum++)
			{ 
				String tempKey =excelObj.getCellData(sheetName, colNum, 1);
				String tempValue=excelObj.getCellData(sheetName, colNum, rowNum);
				System.out.println(tempKey);
				System.out.println(tempValue);
				
				map.put(tempKey, tempValue);
				
			}
			data[rowNum - 2][0] = map;
		}
		
		
		
		
		
		
		
		
		return data;
	}
	
	
	
}
