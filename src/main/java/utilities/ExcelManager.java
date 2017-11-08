package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public ExcelManager(String path) {

		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}

	public int getColumnCount(String sheetName) {
		

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();

	}
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			if (cell.getCellTypeEnum() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				

				return cellText;
			} else if (cell.getCellTypeEnum() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}
	

	
//This method will write the locaiton list and the list of hotels in excelSh
	public void setCellData(String sheetName, HashMap<String, ArrayList<String>> mapss) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);

//			
//			for (Map.Entry<String,String> entry : mapss.entrySet()) 
//			{
//				
//			}
			
			Set<String> locations = mapss.keySet();

			java.util.Iterator<String> itr = locations.iterator();
			int indexCol = 0;
			while (itr.hasNext()) {

				String tempLocation = itr.next();
				row = sheet.getRow(0);
				if (row == null)
					row = sheet.createRow(0);
				cell = row.createCell(indexCol);
				cell.setCellValue(tempLocation);
				ArrayList<String> hotels = mapss.get(tempLocation);
				for (int i = 0; i < hotels.size(); i++) {
					row = sheet.getRow(i + 1);
					if (row == null)
						row = sheet.createRow(i + 1);

					cell = row.createCell(indexCol);

					cell.setCellValue(hotels.get(i));

				}
				indexCol++;
			}

			

			fileOut = new FileOutputStream(path);

			workbook.write(fileOut);

			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
