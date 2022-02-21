package CommonLibs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {

	private InputStream fileReader;
	private OutputStream fileWriter;

	private Workbook excelWorkbook;

	private String excelSheet;

	public void createWorkbook(String workbookName) throws Exception {
		// required when to create a workbook

		workbookName = workbookName.trim(); // trimming the workbook name

		File file = new File(workbookName); // initializing File class

		if (file.exists()) {
			throw new Exception("File already exists..."); // verifying file name exists or not
		}
		if (workbookName.endsWith(".xls")) { // if "xls", initialize it with HSSFWorkbook();
			excelWorkbook = new HSSFWorkbook();
		} else if (workbookName.endsWith(".xlsx")) { // if "xlsx", initialize it with XSSFWorkbook();
			excelWorkbook = new XSSFWorkbook();
		} else {
			throw new Exception("Invalid file format..."); // invalid if none file format exists
		}

		fileWriter = new FileOutputStream(workbookName); // initialize fileWriter

		excelWorkbook.write(fileWriter); // as of now it will be an empty file

		fileWriter.close();

		excelWorkbook.close();

	}

	public void openWorkbook(String workbookName) throws Exception {
		// can be applicable on new as well as existing workbook

		workbookName = workbookName.trim();

		excelSheet = workbookName;

		File file = new File(workbookName); // create a file instance

		if (!file.exists()) {
			throw new Exception("File does not exists..."); // for verifying file exists or not
		}

		fileReader = new FileInputStream(workbookName); // initialize fileReader

		excelWorkbook = WorkbookFactory.create(fileReader); // pass fileReader to WorkbookFactory an assign to
															// excelWorkbook

	}

	public void createSheet(String sheetName) throws Exception {
		// required when creating a sheet, can be applicable on new as well as existing
		// workbook

		sheetName = sheetName.trim(); // trimming sheetName

		Sheet sheet = excelWorkbook.getSheet(sheetName); // getting sheetName

		if (sheet != null) {
			throw new Exception("Sheet already exists..."); // verifying if sheet already exists
		}

		excelWorkbook.createSheet(sheetName); // Actual method to create the sheet
	}

	public int getRowCount(String sheetName) throws Exception {
		// get the row count if data needs to be added

		sheetName = sheetName.trim();

		Sheet sheet = excelWorkbook.getSheet(sheetName);

		if (sheet == null) {
			throw new Exception("Sheet does not exists...");
		}

		return sheet.getLastRowNum(); // get the last row number

	}

	public int getCellCountFromRow(String sheetName, int rowNumber) throws Exception {
		// get the cell count

		sheetName = sheetName.trim(); // sheet trim check

		Sheet sheet = excelWorkbook.getSheet(sheetName); // getting the sheet

		if (sheet == null) {
			throw new Exception("Sheet does not exists..."); // if sheet is null throw exception
		}

		if (rowNumber < 0) {
			throw new Exception("Invalid row number...");
		}

		Row row; // initialize row

		row = sheet.getRow(rowNumber); // get row number

		if (row == null) { // if row is null then return 0
			return 0;
		} else {
			return row.getLastCellNum(); // otherwise return the lastcell number
		}

	}

	public String getCellData(String sheetName, int rowNumber, int cellNumber) throws Exception {
		// get the data from sheetName and rowNumber

		sheetName = sheetName.trim(); // sheet trim check

		Sheet sheet = excelWorkbook.getSheet(sheetName); // getting the sheet

		if (sheet == null) {
			throw new Exception("Sheet does not exists..."); // if sheet is null throw exception
		}

		if (rowNumber < 0 || cellNumber < 0) {
			throw new Exception("Invalid row or cell number...");
		}

		Row row; // initialize row

		row = sheet.getRow(rowNumber); // get row number

		if (row == null) { // if row is null then return empty value
			return "";
		}

		Cell cell = row.getCell(cellNumber); // if row is not null then get cell number

		if (cell == null) {			
			return "";		// if cell is null then return empty value
		} else {

			if (cell.getCellType() == CellType.NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else  {
				return cell.getStringCellValue();
			}
		}
	}

	public void setCellData(String sheetName, int rowNumber, int cellNumber, String data) throws Exception {
		// set the date
		
		sheetName = sheetName.trim(); // sheet trim check

		Sheet sheet = excelWorkbook.getSheet(sheetName); // getting the sheet

		if (sheet == null) {
			throw new Exception("Sheet does not exists..."); // if sheet is null throw exception
		}

		if (rowNumber < 0 || cellNumber < 0) {
			throw new Exception("Invalid row or cell number...");
		}

		Row row; // initialize row

		row = sheet.getRow(rowNumber); // get row number

		if (row == null) { 
			
			row = sheet.createRow(rowNumber);
			row = sheet.getRow(rowNumber); 

		}

		Cell cell = row.getCell(cellNumber); // if row is not null then get cell number

		if (cell == null) {			
			
			cell = row.createCell(cellNumber);
			cell = row.getCell(cellNumber);
			
		}
		
		cell.setCellValue(data);
	}

	public void save() throws Exception {
		// save the file
		
		fileWriter = new FileOutputStream(excelSheet);
		
		excelWorkbook.write(fileWriter);
		
		fileWriter.close();
		
	}

	public void saveAs(String newWorkbookFilename) throws Exception {
		// saving with new name
		
		fileWriter = new FileOutputStream(newWorkbookFilename);
		
		excelWorkbook.write(fileWriter);
		
		fileWriter.close();
	}

	public void close() throws Exception {
		// closing a file
		
		fileReader.close();
		
		fileWriter.close();
		
		excelWorkbook.close();
	}
}
