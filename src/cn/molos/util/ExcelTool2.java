package cn.molos.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Panwx
 * @version 2015年1月6日上午11:03:34
 */

public class ExcelTool2 {
	/**
	 * 1:XLS,2:XLSX
	 */
	private int type = 1;

	public ExcelTool2(int type) {
		super();
		this.type = type;
	}

	/**
	 * 根据二进制流读取加盟商模板
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public List<Object[]> read(InputStream in) throws IOException,
			InvalidFormatException {
		Workbook wb = type == 1 ? new HSSFWorkbook(in) : new XSSFWorkbook(in);
		List<Object[]> list = new ArrayList<Object[]>();
		for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
			Sheet sheet = wb.getSheetAt(numSheet);
			if (sheet != null) {
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row != null) {
						list.add(assmData(row));
					}
				}
			}
		}
		// if (wb != null) {
		// wb.close();
		// }
		if (in != null) {
			in.close();
		}
		return list;
	}

	private Object[] assmData(Row row) {
		Object[] objs = new Object[18];
		for (int i = 0; i < 18; i++) {
			Cell cell = row.getCell(i);
			if (cell != null) {
				objs[i] = val(cell);
			} else {
				objs[i] = "";
			}
		}
		return objs;
	}

	private String val(Cell cell) {
		String str = "";
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			str = String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			DecimalFormat df = new DecimalFormat("0");
			str = df.format(cell.getNumericCellValue());
		} else {
			str = String.valueOf(cell.getStringCellValue());
		}
		return str.trim();
	}
}
