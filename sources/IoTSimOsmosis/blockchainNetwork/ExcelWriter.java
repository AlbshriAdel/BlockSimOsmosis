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
	public static int runNumber;
	

	public static void printToExcel(int simulationRunNumber) {
		runNumber=simulationRunNumber;
		if (InputConfig.getConsensusalgorithm() == "PoW") {
			configPoW();
			resultPoW();
			blockchainLedgerPoW();
			blockchainTranscations();
			transcationPool();
			transcationLatency();
			statisticResult();
		} else if (InputConfig.getConsensusalgorithm() == "raft") {
			configRaft();
			resultRaft();
			blockchainLedgerRaft();
			blockchainTranscations();
			transcationLatency();
			transcationPool();
			statisticResult();
			nodeLog();
		}
		


		

		
		

		// Generate filename + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyy-HH:mm:ss"))
//		String fname = "Blockchain-" 
//				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyy-HH:mm:ss"))+"-"+(simulationRunNumber)+".xlsx";
//
//		try (FileOutputStream outputStream = new FileOutputStream("output/"+ fname)) {
//			workbook.write(outputStream);
//			outputStream.close();
		String fname = "Blockchain-" +(simulationRunNumber)+".xlsx";

		try (FileOutputStream outputStream = new FileOutputStream("output/"+ fname)) {
			workbook.write(outputStream);
			outputStream.close();


		Desktop.getDesktop().open(new File("output/"+fname));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/************************ to print simulator configuration for PoW *****************************/

	public static void configPoW() {
		ArrayList<Object[]> df1 = new ArrayList<>();
		
		df1.add(new Object[] { "Simulator No. Run",
				"No. of Node",
				"No. of Miner", 
				"consensus Algorithm", 
				"No. of Transactions", 
				"Block Gas Limit",
				"Transaction Gas Limit", 
				"Block Interval", 
				"Simulation Time"
				 });

		df1.add(new Object[] { 
				runNumber,
				Node.getNodes().size(),
				InputConfig.getNumberOfMiner(), 
				InputConfig.getConsensusalgorithm(),
				InputConfig.getTransactionNumber(),
				InputConfig.getBlockGasLimit(),
				InputConfig.getTransactionGaslimit(), 
				InputConfig.getBlockInterval(),
				InputConfig.getSimulationTime(),
				 });


		
		
		// writing data frames to workbook
		writeData(df1, workbook, "config");

	}
	
	/************************ to print simulator configuration for Raft *****************************/

	public static void configRaft() {
		ArrayList<Object[]> configRaft = new ArrayList<>();
		
		
		configRaft.add(new Object[] { "Simulator No. Run",
				"No. of Node",
				"No. of Miner", 
				"consensus Algorithm", 
				"Total No of Transactions Per Sec",
				"Max Block Size",
				"Max Tx Size", 
				"Min Tx Size", 
				"Block Interval", 
				"Simulation Time",
				});

			configRaft.add(new Object[] {runNumber,
					Node.getNodes().size(),
					InputConfig.getNumberOfMiner(), 
					InputConfig.getConsensusalgorithm(),
					InputConfig.getTransactionNumber(),
					InputConfig.getMaxBlockSize(), 
					InputConfig.getMaxTransactionSize(), 
					InputConfig.getMinTransactionSize(),
					InputConfig.getBlockInterval(),
					InputConfig.getSimulationTime(),
					});
		
		// writing data frames to workbook
		writeData(configRaft, workbook, "config");

	}

	/************************ to print simulator result PoW *********************************/

	public static void resultPoW() {
		ArrayList<Object[]> df2 = new ArrayList<>();
		
		df2.add(new Object[] { "Simulator No. Run", 
				"Total No. of Blocks", 
				"Total No. of Blocks include Tx",
				"Total No. of  Blocks without Tx",
				"Total No of Transactions Per Sec",
				"Avg. No. of Tx per block",
				"Avg. of Tx Inclusion Time (secs)",
				"Avg. Tx Used Gas",
				"Total No. of Pending Tx",
				"Avg. Block Propagation (secs)", 
				"Avg. Transaction Latency (secs)",
				"Transactions execution (secs)",
				"Transaction Throughput (Tx/secs)",
				 });

		df2.add(new Object[] { runNumber,
				Statistics.totalNumberOfBlock,
				Statistics.blockIncludeTx,
				Statistics.blockWithoutTx,
				Statistics.totalNumberOfTx,
				Statistics.TxPerBlock,
				Statistics.TxInclusionTime,
				Statistics.TxUsedGas,
				Statistics.pendingTx,
				Statistics.blockPropagationTime, 
				Statistics.averageLatency,
				Statistics.totalTransactionsTime,
				Statistics.transactionsThroughput, 
				 });

		// writing data frames to workbook
		writeData(df2, workbook, "Results");

	}
	
	/************************ to print simulator result for Raft *********************************/

	public static void resultRaft() {
		ArrayList<Object[]> df2 = new ArrayList<>();
	
			df2.add(new Object[] { "Simulator No. Run", 
					"Total No. of Blocks", 
					"Total No. of Blocks include Tx",
					"Total No. of  Blocks without Tx",
					"Avg. Block Size (MB)",
					"Total No of Transactions",
					"Avg. No. of Tx per block",
					"Avg. of Tx Inclusion Time (secs)",
					"Avg. Tx Size (MB)",
					"Total No. of Pending Tx",
					"Avg. Block Propagation (secs)", 
					"Avg. Transaction Latency (secs)",
					"Transactions execution (secs)",
					"Transaction Throughput (Tx/secs)",
					 });

			df2.add(new Object[] { runNumber,
					Statistics.totalNumberOfBlock,
					Statistics.blockIncludeTx,
					Statistics.blockWithoutTx,
					Statistics.blockSize,
					Statistics.totalNumberOfTx,
					Statistics.TxPerBlock,
					Statistics.TxInclusionTime,
					Statistics.TxSize,
					Statistics.pendingTx,
					Statistics.blockPropagationTime, 
					Statistics.averageLatency,
					Statistics.totalTransactionsTime,
					Statistics.transactionsThroughput, 
					 });
			
	
		// writing data frames to workbook
		writeData(df2, workbook, "Results");

	}

	/************************ to print blockchain blocks PoW *********************************/

	public static void blockchainLedgerPoW() {

		ArrayList<Object[]> df3 = new ArrayList<>();

		df3.add(new Object[] { "Simulator No. Run", "Block ID", "Previous Block ID", "Block Depth", "Block Timestamp",
				"Block Used Gas", "No. of Transactions", "Mined by", "hash power" });

		for (Object[] chain : Statistics.getChains()) {
			df3.add(chain);
		}

		writeData(df3, workbook, "block");

	}
	
	/************************ to print blockchain blocks Raft *********************************/

	public static void blockchainLedgerRaft() {

		ArrayList<Object[]> df3 = new ArrayList<>();

		df3.add(new Object[] { "Simulator No. Run", "Block ID", "Previous Block ID", "Block Depth", "Block Timestamp",
				"Block Size","No. of Transactions", "Mined by"});

		for (Object[] chain : Statistics.getChains()) {
			df3.add(chain);
		}

		writeData(df3, workbook, "block");

	}

	/************************ to print global blockchain *********************************/

	public static void globalBlockchain() {

		ArrayList<Object[]> df4 = new ArrayList<>();

		df4.add(new Object[] { "Simulator No. Run", "Block ID", "Previous Block ID", "Block Depth", "Block Timestamp",
				"Block Received Time", "Block Size", "No. of Transactions" });

		for (Object[] globalBlockchain : Statistics.getGlobalBlockchain()) {
			df4.add(globalBlockchain);
		}

		writeData(df4, workbook, "globalBlockchain");

	}

	/************************ to print transcations *********************************/

	public static void blockchainTranscations() {

		ArrayList<Object[]> df5 = new ArrayList<>();
		if (InputConfig.getConsensusalgorithm()=="PoW") {
		df5.add(new Object[] { "Simulator No. Run", "Transaction ID", "Creation time ", "Confirmation time",
				"Transaction size", "Transaction Used Gas", "Block ID"});
		for (Object[] transaction : Statistics.getTransactions()) {
			df5.add(transaction);
		}} else if (InputConfig.getConsensusalgorithm()=="raft") {
			df5.add(new Object[] { "Simulator No. Run", "Transaction ID", "Creation time ", "Confirmation time",
					"Transaction size", "Block ID"});
			for (Object[] transaction : Statistics.getTransactions()) {
				df5.add(transaction);
		}
		}

		writeData(df5, workbook, "Transcations");

	}
	
	

	/************************ to print transcations latency *********************************/

	public static void transcationLatency() {

		ArrayList<Object[]> df6 = new ArrayList<>();
		df6.add(new Object[] { "Simulator No. Run", "Transaction ID", "Creation time ", "Confirmation time",
				"Transaction Latency" });

		for (Object[] transactionLatency : Statistics.getTransactionLatencies()) {
			df6.add(transactionLatency);
		}

		writeData(df6, workbook, "TransactionLatency");

	}

	/************************ to print transcations pool *********************************/

	public static void transcationPool() {
		ArrayList<Object[]> df7 = new ArrayList<>();

		if (InputConfig.getConsensusalgorithm() == "PoW") {
		df7.add(new Object[] { "Simulator No. Run", "Transaction ID", "Creation time ", "Tx Used Gas ","Status" });
		for (Object[] transactionLatency : Statistics.getTransactionsPool()) {
			df7.add(transactionLatency);
		}} else if (InputConfig.getConsensusalgorithm() == "raft") {
			df7.add(new Object[] { "Simulator No. Run", "Transaction ID", "Creation time ","Transaction Size","Status" });
			for (Object[] transactionLatency : Statistics.getTransactionsPool()) {
				df7.add(transactionLatency);
			
		}}

		writeData(df7, workbook, "TransactionPool");

	}

	/************************ to print nodes log for raft *********************************/

	public static void nodeLog() {
		ArrayList<Object[]> df8 = new ArrayList<>();

		df8.add(new Object[] { "Stage", "Node ID", "Node Type", "Joining Time" });

		for (Object[] NodesLog : Consensus.getNodesLog()) {
			df8.add(NodesLog);
		}

		writeData(df8, workbook, "NodesLog");

	}
	
	/************************ to print statistic Result *********************************/

	public static void statisticResult() {
		ArrayList<Object[]> df9 = new ArrayList<>();

		df9.add(new Object[] { "Item", "Minimum", "Maximum", "Mean", "Standard Deviation" });

		df9.add(new Object[] {"Block Time", Statistics.minBlockTime, Statistics.maxBlockTime, Statistics.meanBlockTime, Statistics.SDBlockTime});

		writeData(df9, workbook, "statistic");

	}

	/**
	 * Writes each Array within Data frame as a Row in the excel sheet.
	 * 
	 * @param DataFrame
	 * @param workbook
	 * @param sheetName
	 */
	private static void writeData(ArrayList<Object[]> DataFrame, XSSFWorkbook workbook, String sheetName) {

		XSSFSheet sheet = workbook.getSheet(sheetName);
		if(sheet == null) {
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

	/**
	 * Aesthetic formating of excel sheet
	 * 
	 * @param columns
	 * @param sheet
	 * @param workbook
	 */
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
