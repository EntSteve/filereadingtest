package excelReading;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//For Excel file
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class loadClusters {

	public static void main(String[] args) {
		String file = "data/bubbles2.xlsx";
		
		System.out.println("Reading from "  + file);
		readExcelFile(file);

	}
	
	private static void readExcelFile(String file) {
		int rowCount = 1;
		int clusterCount = 0;
		int custX = 0;
		int custY = 0;
		
		// Open the requested file
		XSSFWorkbook workbook = new XSSFWorkbook();
		FileInputStream fis;
		XSSFSheet sheet = null;
		XSSFRow curRow = null;
		
		try { 
			fis = new FileInputStream(new File(file));
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			curRow = sheet.getRow(rowCount);
		} catch (Exception e) {
			System.out.println("File is not present");
			System.out.println("Exception: " + e);
		}
		//Reading in the centroid and total number of custsNodes
		try { 
			//curRow.getCell(0) + curRow.getCell(1) for centroid details
			
			clusterCount = (int) curRow.getCell(6).getNumericCellValue();
			System.out.println("There are " + clusterCount + " Customers");
		}catch (Exception e){
			System.out.println("Failed to read from file");
		}
		//Reading in each customer into a customerNode  
		try {
			rowCount = 3; 
 			for (int i = 0; i < clusterCount; i++) {
				//Read in each row from 3 to clusterCount
				//Starting on row clusterCount + 4
				curRow = sheet.getRow(rowCount);
				custX = (int) curRow.getCell(0).getNumericCellValue();
				custY = (int) curRow.getCell(1).getNumericCellValue();
				System.out.println("Customer " + i + " " + custX + " " + custY);
				rowCount++;
				
				CustomerNode cust = new CustomerNode(custX, custY);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
