package com.alphonse.bankback.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.alphonse.bankback.entities.TraceData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class TraceDataExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;//feuille excel
	
	public TraceDataExporter() {
		workbook = new XSSFWorkbook();//workbook
		sheet = workbook.createSheet("Trace Data");
	}

	/**
	 * prepareHeaders
	 */
	private void prepareHeaders() {
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);//ligne bold
        font.setFontHeight(16);
        style.setFont(font);
        
        createCell(row, 0, "Login", style);
        createCell(row, 1, "Method", style);
        createCell(row, 2, "Date", style);
        createCell(row, 3, "Args", style);
	}

	/**
	 *
	 * @param row
	 * @param index
	 * @param value
	 * @param style
	 */
	private void createCell(Row row, int index, String value, CellStyle style) {
        sheet.autoSizeColumn(index);
        Cell cell = row.createCell(index);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

	/**
	 *
	 * @param tracesData
	 * @param output
	 * l'utilisation du outpout ici me permet les best practices pour séparer le web(controller) du frontend
	 */
	public void export(List<TraceData> tracesData, OutputStream output) {
		prepareHeaders();
		if(tracesData != null) {
			CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(false);
	        font.setFontHeight(14);
	        style.setFont(font);
	        int index = 1;
	        
	        for(TraceData traceData: tracesData) {
	        	writeLine(traceData, index++, style);//ecrire une ligne
	        }
		}
		
		try {
			workbook.write(output);
			workbook.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param traceData
	 * @param rowIndex
	 * @param style
	 */
	
	private void writeLine(TraceData traceData, int rowIndex, CellStyle style) {
		Row row = sheet.createRow(rowIndex);//instancions la ligne ici
		createCell(row, 0, traceData.getLogin(), style);//ecriture des valeures ici
        createCell(row, 1,traceData.getMethod(), style);
        createCell(row, 2, traceData.getDate().toLocalDate().toString(), style);
        createCell(row, 3, traceData.getArgs(), style);
	}

}
