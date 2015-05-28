package weixin.weixin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 读取Excel文件操作工具(基于poi,支持office 2003及以上的版本)
 * 
 * @author lian
 * 
 */
public class PoiReadExcelUtil {
	static Logger logger = LoggerFactory.getLogger(PoiReadExcelUtil.class);
	/** 
	 * 根据文件的路径创建Workbook对象 
	 * @param filePath 
	 * @throws Exception 
	 */
	public static Map<String, List<Map<String, Object>>> readExcelMap(String filePath){
		InputStream ins = null;
		try {
			ins = new FileInputStream(new File(filePath));
			return readExcelMap(ins);
		} catch (Exception e) {
			logger.error("解析excel异常！",e);
		}finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					logger.error("解析excel异常！",e);
				}
			}
		}
		return null;
	}


	/**
	 * 以Map的格式存储数据 
	 * 读取Excel文件的数据 
	 * @param inputStream
	 * @return Map<String, List<Map<String, Object>>>
	 * @throws Exception
	 */
	public static Map<String, List<Map<String, Object>>> readExcelMap(InputStream inputStream) throws Exception {
		//获取workbook对象  
		Workbook workbook = WorkbookFactory.create(inputStream);
		//获取sheet页数  
		int sheetNum = workbook.getNumberOfSheets();
		//存储excel相关的数据  
		Map<String, List<Map<String, Object>>> excelData = Maps.newHashMap();
		//遍历相关sheet页面获取相关的数据 
		Sheet tmp;
		for (int index = 0; index < sheetNum; index++){
			tmp = workbook.getSheetAt(index);
			if(tmp !=null)
				excelData.put(tmp.getSheetName(), getExcelMapData(tmp));
		}
		return excelData;
	}

	/** 
	 * 获取sheet表中的数据,注：表是无标题的！
	 * @param sheet 
	 * @return headTitle 格式为1.2....列标做为key 
	 */
	private static List<Map<String, Object>> getExcelMapData(Sheet sheet) {
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String,Object> columnNames = Maps.newHashMap();
		Map<String,Object> columnData;
		boolean keycol = false;
		Row row;
		int lastRow = sheet.getLastRowNum();
		for(int i=0;i<=lastRow;i++){
			row = sheet.getRow(i);
			if(null == row || row.getFirstCellNum() == -1)
				continue;
			if(!keycol){
				int lastCol = row.getLastCellNum();
				for(int col=0;col<lastCol;col++){
					columnNames.put(col+"", getCellValue(row.getCell(col)));
				}
				keycol = true;
			}else{
				int lastCol = row.getLastCellNum();
				columnData = Maps.newHashMap();
				for(int col=0;col<lastCol;col++){
					Object key = columnNames.get(col+"");
					if(key!=null)
						columnData.put(key.toString(), getCellValue(row.getCell(col)));
				}
				data.add(columnData);
			}
		}
		return data;
	}

	/** 
	 * 获取列的数据信息  
	 * @param cell 
	 * @return 
	 */
	private static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getRichStringCellValue().getString();
		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		case Cell.CELL_TYPE_BOOLEAN:
			return Boolean.toString(cell.getBooleanCellValue());
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula().toString();
		case Cell.CELL_TYPE_BLANK:
			return  null;
		case Cell.CELL_TYPE_ERROR:
			return Byte.toString(cell.getErrorCellValue());
		default:
			return null;
		}
	}
	
}
