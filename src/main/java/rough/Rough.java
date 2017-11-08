package rough;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import utilities.ExcelManager;

public class Rough {

	
	
	
	
	
	
	
	public static void main(String[] args) throws ParseException {
		
		
		
		
		
		
		
		
		
		String str = "3444.2";
		
		System.out.println(Double.parseDouble(str));
		
		
		
//		ExcelManager objj = new ExcelManager("C:\\CantataHealth\\StayFab\\src\\test\\resources\\testData\\Book1.xlsx");
//		
//		HashMap<String,ArrayList<String>>ms = new HashMap<String, ArrayList<String>>();
//		
//		ArrayList<String> str = new ArrayList<String>();
//		str.add("sdf");
//		str.add("fbxc");
//		str.add("zddv");
//		str.add("zvsd");
//		
//		ArrayList<String> str2 = new ArrayList<String>();
//		str2.add("sdf");
//		str2.add("fbxc");
//		str2.add("zddv");
//		
//		
//		
//		ms.put("one", str);
//		ms.put("tow",str2);
//		objj.setCellData("Shashwat",ms );
//		
		

		
		
	}
	public static void getToday() throws ParseException {

		
		
		
		
		
		
		
		
		 Date date = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		    String strDate = formatter.format(date);  
		    System.out.println("Date Format with MM/dd/yyyy : "+strDate);  
	}
	//30-november-2017
	public static void datesss(String date)
	{
		
		
		String[] splitDate = date.split("-");
		
		String day = splitDate[0];
		String month =splitDate[1];
		String year = splitDate[2];
		
		String currentYear=null;
		
		
		
		
		
		if(Integer.parseInt(year) == Integer.parseInt(currentYear))
		{
			int click = 12-Integer.parseInt(month);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
