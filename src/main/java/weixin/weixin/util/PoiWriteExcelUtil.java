package weixin.weixin.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


/**
 * 创建Excel文件操作工具(基于poi,支持office 2003及以上的版本)
 * 
 * @author lian
 * 
 */
public class PoiWriteExcelUtil {
	static Logger logger = LoggerFactory.getLogger(PoiWriteExcelUtil.class);
	
	public static void exportExcel(HttpServletResponse response,String fileName,
			String[][] fieldTitles, List<Map<String, String>> values) {
		try {
			fileName = new String((fileName).getBytes("UTF-8"), "ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename="+ fileName + ".xlsx");
			response.setContentType("application/vnd.ms-excel;");
			Workbook workbook = writeData(fieldTitles,values);
			workbook.write(response.getOutputStream());
		} catch (IOException e1) {
			logger.error("导出excel出错",e1);
		} finally {
			try {
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				logger.error("导出excel关闭输出流出错",e);
			}
		}
	}

	private static final Workbook writeData(String[][] fieldTitles,
			List<Map<String, String>> values) {
		 XSSFWorkbook workbook = new XSSFWorkbook();
		 XSSFSheet worksheet = workbook.createSheet("sheet1");
		 int row = 0;
		 XSSFRow bodyRow = worksheet.createRow(row++);
		 String[] fields = new String[fieldTitles.length];
		 int index = 0;
		 for(String[] field : fieldTitles){
			 bodyRow.createCell(index).setCellValue(field[1]);
			 fields[index++] = field[0];
		 }
		 for(Map<String, String> value : values){
			 bodyRow = worksheet.createRow(row++);
			 index = 0;
			 for(String field : fields){
				 bodyRow.createCell(index++).setCellValue(value.get(field));
			 }
		 }
		 return workbook;
	}
}
