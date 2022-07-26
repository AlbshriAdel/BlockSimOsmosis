package blockchainNetwork;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

//	public static void printToExcel() {
//		ArrayList<Object[]> df1 = new ArrayList<>();
//		df1.add(new Object[]{
//				"No. of light Node",
//				"No. of Miner Node",
//				"Total No. of Blocks",
//				"Total Transactions",
//				"Average Transaction Latency",
//				"Transaction Throughput",
//				"Simulation Duration (secs)"});
//		df1.add(new Object[]{
//				InputConfig.getNODES().size(),
//				InputConfig.getMiners().size(),
//				Statistics.TotalNumberOfBlock,
//				Statistics.TotalNumberOfTx,
//				Statistics.averageTransactionLatency,
//				Statistics.transactionsThroughput,
//				Statistics.totalTransactionsTime});
//	
//			// Initialising new workbook
//			XSSFWorkbook workbook = new XSSFWorkbook();
//			
//			// writing data frames to workbook
//			writeData(df1, workbook, "Results");
//			
//
//				ArrayList<Object[]> df2 = new ArrayList<>();
//				df2.add(new Object[]{
//						"Block Depth",
//						"Block ID",
//						"Block Timestamp",
//						"No. of Transactions"});
//				for (Object[] chain : Statistics.getChains()) {
//					df2.add(chain);
//				}
//				
//				ArrayList<Object[]> df3 = new ArrayList<>();
//				df3.add(new Object[]{
//						"Tx ID",
//						"Tx size",
//						"Creation time ",
//						"Confirmation time",
//						});
//				for (Object[] transaction : Statistics.getTransactions()) {
//					df3.add(transaction);
//			}
//				
//				ArrayList<Object[]> df4 = new ArrayList<>();
//				df4.add(new Object[]{
//						"Tx ID",
//						"Transaction Latency",
//						});
//				for (Object[] transactionLatency : Statistics.getTransactionLatencies()) {
//					df4.add(transactionLatency);
//			}
//				
//				
//				writeData(df2, workbook, "Blocks");
//				writeData(df3, workbook, "Transactions");
//				writeData(df4, workbook, "TransactionLatency");
//				
//
//			try (FileOutputStream outputStream = new FileOutputStream("IoTBlockchain.xlsx")) {
//				workbook.write(outputStream);
//				outputStream.close();
//				
//				// Open file
//				Desktop.getDesktop().open(new File("IoTBlockchain.xlsx"));
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//	}
//	
//	// Writes each Array within Data frame as a Row in the excel sheet.
//		private static void writeData(ArrayList<Object[]> DataFrame, XSSFWorkbook workbook, String sheetName) {
//					
//			XSSFSheet sheet = workbook.createSheet(sheetName);
//			
//			int rowCount = 0;
//			
//			for (Object[] rowData : DataFrame) {
//				XSSFRow row = sheet.createRow(++rowCount);
//				
//				int columnCount = 0;
//				for (Object field : rowData) {
//					XSSFCell cell = row.createCell(++columnCount);
//					if (field instanceof String) {
//						cell.setCellValue((String) field);
//					} else if (field instanceof Integer) {
//						cell.setCellValue((Integer) field);
//					} else if (field instanceof Double) {
//						cell.setCellValue((Double) field);
//					} else if (field instanceof Long) {
//						cell.setCellValue((Long) field);
//					}
//				}
//			}
//			
//			// Basic aesthetic formating of Excel Sheet
//			formatExcelSheet(DataFrame.get(0).length, sheet, workbook);
//
//		}
//		
//		// Aesthetic formating of excel sheet
//		private static void formatExcelSheet(int columns, XSSFSheet sheet, XSSFWorkbook workbook) {
//
//			
//			// Creating header font.
//			XSSFFont font = workbook.createFont();
//			font.setFontHeightInPoints((short)11);
//			font.setBold(true);
//			font.setColor(IndexedColors.WHITE.getIndex());
//			
//			//Setting header font and header filling.
//			XSSFCellStyle style = workbook.createCellStyle();
//			style.setFont(font);
//			style.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
//			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//			
//			
//			XSSFRow header = sheet.getRow((short) 1);
//			// Setting style for each cell in the header row.
//			for (int i =1; i<header.getLastCellNum();i++) {
//			header.getCell(i).setCellStyle(style);
//		}
//			
//			// Resize column widths 
//			for (int i = 1; i < columns+1; i++) {
//				sheet.autoSizeColumn(i);
//			}
//		}
//
//		
//	
}
