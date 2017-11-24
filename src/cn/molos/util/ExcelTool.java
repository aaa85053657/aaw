package cn.molos.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTool {
	/**
	 * 1:XLS,2:XLSX
	 */
	private int type = 1;

	public ExcelTool(int type) {
		super();
		this.type = type;
	}

	/**
	 * 创建WorkBook
	 * 
	 * @param data
	 * @param title
	 * @return
	 */

	public Workbook creatWorkBook(List<List<Object>> data, int t) {
		Workbook wb = type == 1 ? new HSSFWorkbook() : new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		// Row row = sheet.createRow(0);
		if (t == 1) {
			String[] title = { "加盟商编码", "加盟商名字", "描述", "地址", "联系人", "联系电话",
					"邮箱", "状态", "等级", "类别", "父节点", "座机", "账号", "密码",
					"子加盟商创建权限", "子加盟商修改权限", "子加盟商删除权限", "订单创建权限" };
			createTitle(wb, sheet, title);
			createContent(wb, sheet, data);
		}
		if (t == 2) {
			String[] title = { "日期", "出品码", "出品名称", "单位", "数量", "总价", "打折价",
					"门店" };
			createTitle(wb, sheet, title);
			createContent(wb, sheet, data);
		}
		if (t == 3) {
			String[] title = { "codeID", "属性值", "属性" };
			createTitle(wb, sheet, title);
			createContentele(wb, sheet, data);
		}
		return wb;
	}

	/**
	 * 标题
	 * 
	 * @param wb
	 * @param sheet
	 * @param title
	 */
	private void createTitle(Workbook wb, Sheet sheet, String[] title) {
		Row row = sheet.createRow(0);
		Cell cell;
		// CellRangeAddress rang;

		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		Font font = wb.createFont();
		font.setFontName("宋体");
		font.setBoldweight((short) 2);
		font.setItalic(false);

		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
		}
	}

	/**
	 * 创建内容
	 * 
	 * @param wb
	 * @param sheet
	 * @param data
	 */
	private void createContent(Workbook wb, Sheet sheet, List<List<Object>> data) {
		int mx = data.size();
		for (int j = 1; j < mx + 1; j++) {
			Row row = sheet.createRow(j);
			int len = data.get((j - 1)).size();
			String status = data.get((j - 1)).get(7).toString();
			CellStyle style = wb.createCellStyle();
			if (status.equals("1")) {
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			if (status.equals("0")) {
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			if (status.equals("9")) {
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}

			for (int i = 0; i < len; i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(style);
				if (data.get((j - 1)).get(i) != null) {
					cell.setCellValue(data.get((j - 1)).get(i).toString());
				} else {
					cell.setCellValue("");
				}
			}
		}
	}

	private void createContentele(Workbook wb, Sheet sheet,
			List<List<Object>> data) {
		int mx = data.size();
		for (int j = 1; j < mx + 1; j++) {
			Row row = sheet.createRow(j);
			int len = data.get((j - 1)).size();
			CellStyle style = wb.createCellStyle();
			for (int i = 0; i < len; i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(style);
				if (data.get((j - 1)).get(i) != null) {
					cell.setCellValue(data.get((j - 1)).get(i).toString());
				} else {
					cell.setCellValue("");
				}
			}
		}

	}

}
