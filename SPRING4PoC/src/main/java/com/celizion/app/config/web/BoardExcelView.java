package com.celizion.app.config.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.celizion.app.model.board.Board;
import com.celizion.app.util.CommonUtilities;

/**
 * <pre>
 * com.celizion.app.config.web
 * PosRecordExcelView.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 1. 10.
 */
public class BoardExcelView extends AbstractXlsView {

	private final String fontName = "맑은 고딕";
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		/*res.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode("측위이력", "UTF-8") + "_" + CommonUtilities.getCurrentDateString("yyyyMMdd", Locale.KOREA) + ".xls\"");
		
		List<?> posResultData = (List<?>) model.get("posResult");
		
		Sheet sheet = workbook.createSheet(messageSource.getMessage("label.page.pos.record.title", null, Locale.getDefault()));
		
		Font titleFont = setFont(workbook, true);
		Font contFont = setFont(workbook, false);
		
		CellStyle titleCellStyleLeftTopBorder = setTitleCellStyleLeftTopBorder(workbook, titleFont);
		CellStyle titleCellStyleTopBorder = setTitleCellStyleTopBorder(workbook, titleFont);
		CellStyle titleCellStyleRightTopBorder = setTitleCellStyleRightTopBorder(workbook, titleFont);
		
		Row rowHeader = sheet.createRow(0);
		
		setCell(rowHeader, 0, titleCellStyleLeftTopBorder, messageSource.getMessage("label.table.mdn", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 1, titleCellStyleTopBorder, messageSource.getMessage("label.table.posResTime", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 2, titleCellStyleTopBorder, messageSource.getMessage("label.table.postype", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 3, titleCellStyleTopBorder, messageSource.getMessage("label.table.posResult", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 4, titleCellStyleTopBorder, messageSource.getMessage("label.table.true.posLati", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 5, titleCellStyleTopBorder, messageSource.getMessage("label.table.true.posLongi", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 6, titleCellStyleTopBorder, messageSource.getMessage("label.table.posLati", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 7, titleCellStyleTopBorder, messageSource.getMessage("label.table.posLongi", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 8, titleCellStyleTopBorder, messageSource.getMessage("label.table.bld", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 9, titleCellStyleTopBorder, messageSource.getMessage("label.table.bldF", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 10, titleCellStyleTopBorder, messageSource.getMessage("label.table.poi", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 11, titleCellStyleTopBorder, messageSource.getMessage("label.table.posDistErr", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 12, titleCellStyleTopBorder, messageSource.getMessage("label.table.nbrCellCnt", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 13, titleCellStyleTopBorder, messageSource.getMessage("label.table.wifiApCnt", null, Locale.getDefault()), CellType.STRING);
		setCell(rowHeader, 14, titleCellStyleRightTopBorder, messageSource.getMessage("label.table.btApCnt", null, Locale.getDefault()), CellType.STRING);
		
		int rowCount = 1;
		
		CellStyle contStyleLeft = setContentsCellStyleLeftBorder(workbook, contFont);
		CellStyle contStyle = setContentsCellStyleThinBorder(workbook, contFont);
		CellStyle contStyleRight = setContentsCellStyleRightBorder(workbook, contFont);
		
		for(int i = 0; i < posResultData.size(); i++) {
			
			Object obj = posResultData.get(i);
			
			if(obj instanceof PosResult) {
				
				if(i == (posResultData.size() - 1)) {
					
					contStyleLeft = setContentsCellStyleLeftBottomBorder(workbook, contFont);
					contStyle = setContentsCellStyleBottomBorder(workbook, contFont);
					contStyleRight = setContentsCellStyleRightBottomBorder(workbook, contFont);
				
				}
				
				PosResult pr = (PosResult) obj;
				
				Row row = sheet.createRow(rowCount++);
				
				setCell(row, 0, contStyleLeft, String.valueOf(pr.getMsisdn()).replaceFirst("(\\d{3})(\\d{4})(\\d+)", "$1-$2-$3"), CellType.STRING);
				setCell(row, 1, contStyle, pr.getRegDate(), CellType.STRING);
				setCell(row, 2, contStyle, pr.getFixtypeCdNm(), CellType.STRING);
				setCell(row, 3, contStyle, pr.getReasonCdNm(), CellType.STRING);
				setCell(row, 4, contStyle, pr.getTrueLatitude(), CellType.NUMERIC);
				setCell(row, 5, contStyle, pr.getTrueLongitude(), CellType.NUMERIC);
				setCell(row, 6, contStyle, pr.getFixlatitude(), CellType.NUMERIC);
				setCell(row, 7, contStyle, pr.getFixlongitude(), CellType.NUMERIC);
				setCell(row, 8, contStyle, pr.getBldname(), CellType.STRING);
				setCell(row, 9, contStyle, pr.getBldfloor(), CellType.NUMERIC);
				setCell(row, 10, contStyle, pr.getPoiname(), CellType.STRING);
				setCell(row, 11, contStyle, pr.getDistancegap(), CellType.NUMERIC);
				setCell(row, 12, contStyle, pr.getNbrcellcnt(), CellType.NUMERIC);
				setCell(row, 13, contStyle, pr.getWifiapinfocount(), CellType.NUMERIC);
				setCell(row, 14, contStyleRight, pr.getBtapinfocount(), CellType.NUMERIC);
			
			}
		
		}
		
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);
		sheet.autoSizeColumn(8);
		sheet.autoSizeColumn(9);
		sheet.autoSizeColumn(10);
		sheet.autoSizeColumn(11);
		sheet.autoSizeColumn(12);
		sheet.autoSizeColumn(13);
		sheet.autoSizeColumn(14);*/
	
	}
	
	private CellStyle setContentsCellStyleLeftBorder(Workbook workbook, Font font) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.MEDIUM);
		return cellStyle;
	}
	
	private CellStyle setContentsCellStyleThinBorder(Workbook workbook, Font font) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		return cellStyle;
	}
	
	private CellStyle setContentsCellStyleRightBorder(Workbook workbook, Font font) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.MEDIUM);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		return cellStyle;
	}
	
	private CellStyle setContentsCellStyleLeftBottomBorder(Workbook workbook, Font font) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.MEDIUM);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.MEDIUM);
		return cellStyle;
	}
	
	private CellStyle setContentsCellStyleBottomBorder(Workbook workbook, Font font) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.MEDIUM);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		return cellStyle;
	}
	
	private CellStyle setContentsCellStyleRightBottomBorder(Workbook workbook, Font font) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.MEDIUM);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.MEDIUM);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		return cellStyle;
	}
	
	private void setCell(Row row, int cellIdx, CellStyle cellStyle, String cellValue, CellType cellType) {
		
		Cell cell = row.createCell(cellIdx);
		cell.setCellStyle(cellStyle);
		
		if(cellType == CellType.NUMERIC) {
			
			cell.setCellValue(Double.valueOf(cellValue));
		
		} else if(cellType == CellType.STRING) {
			
			cell.setCellValue((String) cellValue);
		
		}
		
		cell.setCellType(cellType);
	
	}
	
	private CellStyle setTitleCellStyleLeftTopBorder(Workbook workbook, Font font) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.MEDIUM);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.MEDIUM);
		return cellStyle;
	}
	
	private CellStyle setTitleCellStyleTopBorder(Workbook workbook, Font font) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.MEDIUM);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		return cellStyle;
	}
	
	private CellStyle setTitleCellStyleRightTopBorder(Workbook workbook, Font font) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.MEDIUM);
		cellStyle.setBorderRight(BorderStyle.MEDIUM);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		return cellStyle;
	}
	
	private Font setFont(Workbook workbook, boolean isBold) {
		Font font = workbook.createFont();
		font.setFontName(fontName);
		font.setFontHeightInPoints((short) 10);
		font.setBold(isBold);
		return font;
	}

}
