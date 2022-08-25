package IoTSimOsmosis.blockchainNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Table.Cell;

import java.awt.Desktop;

public class Excel {
	static ArrayList<Object[]> df3 = new ArrayList<>();
	

	public static ArrayList<Object[]> getDf3() {
		return df3;
	}



	public static void printToExcel(int simulationRunNumber) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		ArrayList<Object[]> df4 = new ArrayList<>();

		df4.add(new Object[] { "Run simulator", "Node ID", "Node Type", });

			for (Object[] chain : getDf3()) {
				df4.add(chain);
			}
			writeData(df4, workbook, "tes");
		

		String fname = "Statistics.xlsx";

		// Write to file
		try (FileOutputStream outputStream = new FileOutputStream("output/Statistics.xls")) {
			workbook.write(outputStream);

			// outputStream.close();

			// Open file
			// Desktop.getDesktop().open(new File("output/Statistics.xls"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}


	private static void writeData(ArrayList<Object[]> DataFrame, XSSFWorkbook workbook, String sheetName) {
		
		XSSFSheet sheet = workbook.createSheet(sheetName);
		
		int rowCount = 0;
		
		for (Object[] rowData : DataFrame) {
			XSSFRow row = sheet.createRow(++rowCount);
			
			int columnCount = 0;
			for (Object field : rowData) {
				XSSFCell cell = row.createCell(++columnCount);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				} else if (field instanceof Double) {
					cell.setCellValue((Double) field);
				} else if (field instanceof Long) {
					cell.setCellValue((Long) field);
				}
			}
		}
		
		// Basic aesthetic formating of Excel Sheet
		formatExcelSheet(DataFrame.get(0).length, sheet, workbook);

	}



	// Aesthetic formating of excel sheet
	private static void formatExcelSheet(int columns, XSSFSheet sheet, XSSFWorkbook workbook) {

		// Creating header font.
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());

		// Setting header font and header filling.
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFRow header = sheet.getRow((short) 1);
		// Setting style for each cell in the header row.
		for (int i = 1; i < header.getLastCellNum(); i++) {
			header.getCell(i).setCellStyle(style);
		}

		// Resize column widths
		for (int i = 1; i < columns + 1; i++) {
			sheet.autoSizeColumn(i);
		}
	}




}
