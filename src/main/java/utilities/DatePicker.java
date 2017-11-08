package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePicker {

	static int targetMonth;
	static int targetDay;
	static int targetYear;
	static int currentMonth;
	static int currentYear;

	public static void getTargerDateMonthAndYear(String dateToSet) {

		String[] dates = dateToSet.split("-");

		targetDay = Integer.parseInt(dates[0]);
		targetMonth = Integer.parseInt(dates[1]);
		targetYear = Integer.parseInt(dates[2]);

	}

	
	
	public static int numberOfClicks()
	{int clicks=0;
		if(targetYear!=currentYear)
		{
			int yearDifference = targetYear-currentYear;
			 clicks = (yearDifference*12+targetMonth)-currentMonth;
		}
		else
		{
			clicks = targetMonth - currentMonth;
		}
		
		return clicks;
	}
	
	public static int getTargetDay()
	{
		return targetDay;
	}
	
	public static void getCurrentDateAndYear() {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(date);

		String[] currentDate = strDate.split("-");
		currentMonth = Integer.parseInt(currentDate[1]);
		currentYear = Integer.parseInt(currentDate[2]);

	}
	
	public static String changeDateFormat(String checkInDate)
	{
		 SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMMM-yyyy");
         Date date = null;
         try {
               date = simpleDate.parse(checkInDate);
         } catch (ParseException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
         }

         simpleDate = new SimpleDateFormat("dd-MM-yyyy");
         return simpleDate.format(date);
	}
	
	

}
