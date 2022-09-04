package net.j2store.genericUtiltiy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;

public final class ExcelUtility {
	private Workbook wb; 
	
	/**
	 * This method used to open the excel workbook
	 * @param filePath
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public  void openExcel(String filePath) throws EncryptedDocumentException, IOException {
		FileInputStream fisExcel=new FileInputStream(filePath);
		wb = WorkbookFactory.create(fisExcel);
		Reporter.log("Excel workbook Opened successfully", true);
	}
	
	/**
	 * This method is used to fetch the data from the excel sheet based on key
	 * @param sheetName
	 * @param key
	 * @return
	 */
	public  String getDataFromExcel(String sheetName, String key) {
		String data=null;
		Sheet sheet = wb.getSheet(sheetName);
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(key)) {
				data=sheet.getRow(i).getCell(1).getStringCellValue();
			}
		}
		Reporter.log("Successfully Data fetched from the excel sheet", true);
		return data;
	}
	
	/**
	 * This method is used to fetch the data from the excel sheet and stored in list<map>
	 * @param sheetName
	 * @return
	 */
	public List<Map<String, String>> getMapDataFromExcel(String sheetName) {
		List<Map<String, String>> list=new ArrayList<>();
		Map<String, String> map=new HashMap<>();
		Sheet sheet = wb.getSheet(sheetName);
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			map.put(sheet.getRow(i).getCell(0).getStringCellValue(), sheet.getRow(i).getCell(1).getStringCellValue());
		}
		list.add(map);
		Reporter.log("Successfully Data fetched from the excel sheet", true);
		return list;
	}
	


	/**
	 * This method is used to close the Excel Connection
	 */
	public  void closeExcel() {
		try {
			wb.close();
			Reporter.log("Excel workbook closed successfully", true);
		} catch (IOException e) {
			e.printStackTrace();
			Reporter.log("while closing excel some exception accured.. please check the ExcelUtility", true);
		}
	}


}
