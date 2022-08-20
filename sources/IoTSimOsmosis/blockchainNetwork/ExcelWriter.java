package IoTSimOsmosis.blockchainNetwork;

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
	static XSSFWorkbook workbook = new XSSFWorkbook();
	public static String sheetName;

	public static void printToExcel() {

//		
//		if (Consensus.getAassignLeader().getTransactionsPool().getTransactionsPool().size()>0 ) {
//			config();
//			transcationPool();
//		}if(Consensus.getAassignLeader().getBlockchainLedger().size()==1){
//			config();
//		} 

//		if(InputConfig.getSimulatorRun()<=1) {
//			config();
//			result();
//			blockchainLedger();
//			blockchainTranscations();
//			transcationLatency();	
//		}

//		if (InputConfig.getSimulatorRun()>1) {
//			if (config()==count);
//			//result();
//		}

		//config();
		result();

		try (FileOutputStream outputStream = new FileOutputStream("IoTBlockchain.xlsx")) {
			workbook.write(outputStream);
			outputStream.close();
			outputStream.flush();
//			outputStream.close();

			// Open file
			//if (InputConfig.getSimulatorRun() == Statistics.getRunNumber()) {
				Desktop.getDesktop().open(new File("IoTBlockchain.xlsx"));
			//}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void config() {

		//setSheetName("config");
		ArrayList<Object[]> df1 = new ArrayList<>();
		df1.add(new Object[] { "No. of Node", "consensus Algorithm", "No. of Transactions", "Transaction Gas Limit",
				"Maximum Transaction Size", "Minimum Transaction Size", "Maximum Block Size", "Block Gas Limit",
				"Block Interval", "Simulator No. Run" });
		for (int i = 0; i < Statistics.getRunNumber(); i++) {

			df1.add(new Object[] { InputConfig.getNumberOfNodes(), InputConfig.getConsensusalgorithm(),
					Statistics.noTransactionsConfig, InputConfig.getTransactionGaslimit(),
					InputConfig.getMaxTransactionSize(), InputConfig.getMinTransactionSize(),
					InputConfig.getMaxblocksize(), InputConfig.getBlockGasLimit(), InputConfig.getBinterval(),
					InputConfig.getSimulatorRun()

			});
			// writing data frames to workbook
			writeData(df1,workbook,"config");
		}

	}

	public static void result() {
		ArrayList<Object[]> df2 = new ArrayList<>();
		df2.add(new Object[] { "Simulator No. Run", "No. of light Node", "No. of Miner Node", "Total No. of Blocks",
				"Total No of Transactions", "Block Propagation Time", "Average Transaction Latency",
				"Transaction Throughput", "Transactions execution (secs)" });
		df2.add(new Object[] { Statistics.getRunNumber(), Node.getNodes().size(), Node.getMiners().size(),
				Statistics.TotalNumberOfBlock, Statistics.TotalNumberOfTx, Statistics.BlockPropagationTime,
				Statistics.averageLatency, Statistics.transactionsThroughput, Statistics.totalTransactionsTime

		});

		// writing data frames to workbook
		 writeData(df2,workbook,"Results");
		
	}

	public static void blockchainLedger() {
		ArrayList<Object[]> df3 = new ArrayList<>();
		df3.add(new Object[] { "Simulator No. Run", "Node ID", "Block ID", "Previous Block ID", "Block Depth",
				"Block Timestamp", "Block Size", "No. of Transactions" });
		for (Object[] chain : Statistics.getChains()) {
			df3.add(chain);
		}
		// writeData(df3, workbook, "Blocks");

	}

	public static void blockchainTranscations() {
		ArrayList<Object[]> df4 = new ArrayList<>();
		df4.add(new Object[] { "Simulator No. Run", "Transaction ID", "Creation time ", "Confirmation time",
				"Transaction size", "Transaction Used Gas", "Block ID", "From Address", "To Address" });
		for (Object[] transaction : Statistics.getTransactions()) {
			df4.add(transaction);
		}
		// writeData(df4, workbook, "Transactions");

	}

	public static void transcationLatency() {
		ArrayList<Object[]> df5 = new ArrayList<>();
		df5.add(new Object[] { "Simulator No. Run", "Transaction ID", "Creation time ", "Confirmation time",
				"Transaction Latency", });
		for (Object[] transactionLatency : Statistics.getTransactionLatencies()) {
			df5.add(transactionLatency);
		}
		// writeData(df5, workbook, "TransactionLatency");

	}

	public static void transcationPool() {
		ArrayList<Object[]> df6 = new ArrayList<>();
		df6.add(new Object[] { "Simulator No. Run", "Transaction ID", "Creation time ", "Confirmation time",
				"Status", });
		for (Object[] transactionLatency : Statistics.getTransactionsPool()) {
			df6.add(transactionLatency);
		}
		// writeData(df6, workbook, "TransactionPool");

	}

	public static String getSheetName() {
		return sheetName;
	}

	public static void setSheetName(String sheetName) {
		ExcelWriter.sheetName = sheetName;
	}

	// Writes each Array within Data frame as a Row in the excel sheet.
	private static void writeData(ArrayList<Object[]> DataFrame,XSSFWorkbook workbook,String sheetName) {
		// XSSFSheet sheet = workbook.createSheet(getSheetName());
		XSSFSheet sheet = workbook.getSheet(sheetName);
		// Check if the workbook is empty or not
		if (workbook.getNumberOfSheets() != 0) {
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				if (workbook.getSheetName(i).equals(sheetName)) {
					sheet = workbook.getSheet(sheetName);
				} else {
					sheet = workbook.createSheet(sheetName);
				}
			}
		} else {
			// Create new sheet to the workbook if empty
			sheet = workbook.createSheet(sheetName);
		}

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
