package com.akalin.template.utils;

import com.akalin.template.pojo.PageData;
import com.akalin.template.service.util.ExcelService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;


/**
 * @ClassName ExcelUtil
 * @Description Excel通用导入导出excel工具类
 * @Author dusc
 * @Date 2019/7/4 15:58
 * @Param
 * @return
 **/
@Component
public class ExcelUtils {
    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    protected ExcelService excelService;

    /**
     * 从流中读取excel数据然后转化为list集合
     * @param uploadFile
     * @return List<PageData>
     */
    public static List<PageData> readExcel(MultipartFile uploadFile) {
        List<PageData> varList = new ArrayList<PageData>();
        try {
            Workbook wb = null;
            try{
                wb = new XSSFWorkbook(uploadFile.getInputStream());
            }
            catch (Exception ex) {
                wb = new HSSFWorkbook(uploadFile.getInputStream());
            }
            Sheet sheet = wb.getSheetAt(0);
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            int rowNum = sheet.getLastRowNum() + 1;
            for (int i = 0; i < rowNum; i++) {
                PageData varpd = new PageData();
                Row row = sheet.getRow(i);
                int cellNum = row.getLastCellNum();
                for (int j = 0; j < cellNum; j++) {
                    DecimalFormat df = new DecimalFormat("0");
                    Cell cell1 = row.getCell(Short.parseShort(j + ""));
                    CellValue cell = evaluator.evaluate(cell1);
                    String cellValue = null;
                    if (null != cell) {
                        switch (cell.getCellType()) {
                            case 0:
                                cellValue = df.format(cell.getNumberValue());
                                break;
                            case 1:
                                cellValue = cell.getStringValue();
                                break;
                            case 2:
                                cellValue = cell.getNumberValue() + "";
                                break;
                            case 3:
                                cellValue = "";
                                break;
                            case 4:
                                cellValue = String.valueOf(cell.getBooleanValue());
                                break;
                            case 5:
                                cellValue = String.valueOf(cell.getErrorValue());
                                break;
                        }
                    } else {
                        cellValue = "";
                    }
                    varpd.put("var"+j, cellValue);
                }
                varList.add(varpd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("list size = " + varList.size());
        return varList;
    }

    /**
     * 传入完整的sql，注意将用中文取数据库column别名
     * as后面的别名将会作为每列的title导出
     * 例如：select loginid as 工号, password as 密码, lastname as 姓名, sex as 性别 from hrmresource where lastname = '张三'
     * @param sql
     * @param excelName
     * @param response
     */
    public void exportExcel(String sql, String excelName, HttpServletResponse response) {
        try {
            PageData pd = new PageData();
            String countsql = this.getCountSql(sql);
            pd.put("countsql", countsql);
            Integer sum = excelService.getExcelCount(pd);
            /*if (sum > 10000){
                ExecutorService service = Executors.newFixedThreadPool(5);
                pd.put("sql", sql);
                List<LinkedHashMap> resultList = excelService.getExcelList(pd);
                List<List<LinkedHashMap>> averageLists = ListUtils.averageAssign(resultList, 5);
                CountDownLatch latch = new CountDownLatch(sum);
                log.info("result size = " + resultList.size());
                response.setHeader("content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName + ".xls", "utf-8"));
                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("Sheet1");
                for (int i = 0; i < averageLists.size(); i++) {
                    List<LinkedHashMap> linkedHashMaps =  averageLists.get(i);
                    service.submit(new Runnable() {
                        @Override
                        public void run() {
                            writeExcel(wb, sheet, linkedHashMaps);
                        }
                    });
                    for (int j = 0; j < linkedHashMaps.size(); j++) {
                        LinkedHashMap linkedHashMap =  linkedHashMaps.get(j);

                    }
                }
                wb.write(response.getOutputStream());
                return;
            }*/
            System.out.println("总记录数 = " + sum);
            pd.put("sql", sql);
            if (true){
                List<LinkedHashMap> resultList = excelService.getExcelList(pd);
                log.info("result size = " + resultList.size());
                response.setHeader("content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName + ".xls", "utf-8"));
                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("Sheet1");
                writeExcel(wb, sheet, resultList);
                wb.write(response.getOutputStream());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出excel， 传入查询出的结果集
     * @param excelName
     * @param response
     */
    public void exportExcelByList(List<LinkedHashMap> resultList, String excelName, HttpServletResponse response) {
        try {
            log.info("result size = " + resultList.size());
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName + ".xls", "utf-8"));
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Sheet1");
            writeExcel(wb, sheet, resultList);
            wb.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeExcel(XSSFWorkbook wb, Sheet sheet, List<LinkedHashMap> resultList) {
        LinkedHashMap resultPd = null;
        for (int i = 0; i < resultList.size(); i++){//遍历出数据库每个字段的列名
            resultPd = resultList.get(i);
            break;
        }
        writeTitlesToExcel(wb, sheet, resultPd);
        writeRowsToExcel(wb, sheet, resultList);
    }

    /**
     * 设置表头
     * @param wb
     * @param sheet
     * @return
     */
    private static void writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, LinkedHashMap pageData) {
        int colIndex = 0;
        Row titleRow = sheet.createRow(0);
        Set<String> set = pageData.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            if (colIndex == 0){
                Cell cell = titleRow.createCell(colIndex);
                cell.setCellValue("序号");
                colIndex++;
                continue;
            }
            String key = iterator.next();
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(key);
            colIndex++;
        }
    }

    /**
     * 设置内容
     * @param wb
     * @param sheet
     * @return
     */
    private static void writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<LinkedHashMap> resultList) {
        int colIndex;
        for (int i = 0; i < resultList.size(); i++) {
            Row dataRow = sheet.createRow(i+1);
            colIndex = 0;
            LinkedHashMap pd = resultList.get(i);
            Set<String> set = resultList.get(i).keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                if (colIndex == 0){
                    Cell cell = dataRow.createCell(colIndex);
                    cell.setCellValue((i + 1) + "");
                    colIndex++;
                    continue;
                }
                String key = iterator.next();
                String keyValue = String.valueOf(pd.get(key));
                Cell cell = dataRow.createCell(colIndex);
                if (StringUtils.isNotEmpty(keyValue) && ! "null".equals(keyValue)) {
                    cell.setCellValue(keyValue);
                } else {
                    cell.setCellValue("");
                }
                colIndex++;
            }
        }
    }

    /**
     * 自动调整列宽
     * @param sheet
     * @param columnNumber
     */
    private static void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    /**
     * 设置边框
     * @param style
     * @param border
     * @param color
     */
    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, color);
    }

    /**
     * 获取查询总记录的数的sql
     * @param sql
     * @return
     */
    public String getCountSql(String sql){
        int start = sql.indexOf("select");
        int end = sql.indexOf("from");
        String rep = sql.substring(start + 6, end);
        String countSql = sql.replace(rep, " count(1) as sum ");
        System.out.println("替换之后的sql = " + countSql);
        return countSql;
    }
}
