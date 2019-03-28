/*
 * Copyright (c) 2019. Hubery
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package pers.hubery.collapsar.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The type Excel util.
 *
 * @author Hubery
 * @version ExcelUtil.java, 2019年03月27日 20:05
 */
@SuppressWarnings("WeakerAccess")
public class ExcelUtil {

    /**
     * Read excel sheet content map.
     *
     * @param excelFile  the excel file
     * @param sheetIndex the sheet index
     * @return the map
     * @throws IOException the io exception
     */
    public static Map<Integer, Map<Integer, Object>> readSheetContent(File excelFile, int sheetIndex) throws IOException {

        Workbook workbook = WorkbookFactory.create(excelFile);

        Sheet sheet = workbook.getSheetAt(sheetIndex);

        Map<Integer, Map<Integer, Object>> sheetMap = new HashMap<>();

        for (Row row : sheet) {

            Map<Integer, Object> rowMap = new HashMap<>(row.getPhysicalNumberOfCells());
            sheetMap.put(row.getRowNum(), rowMap);

            for (Cell cell : row) {

                Object val;
                switch (cell.getCellType()) {
                    case STRING:
                        val = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            val = cell.getDateCellValue();
                        } else {
                            val = cell.getNumericCellValue();
                        }
                        break;
                    case BOOLEAN:
                        val = cell.getBooleanCellValue();
                        break;
                    case FORMULA:
                        val = cell.getCellFormula();
                        break;
                    case BLANK:
                        val = "";
                        break;
                    // case _NONE:break;
                    // case ERROR:break;
                    default:
                        val = null;
                }

                rowMap.put(cell.getColumnIndex(), val);
            }
        }

        return sheetMap;
    }

    /**
     * Write new sheet.
     *
     * @param excelFile  the excel file
     * @param contentMap the content map
     * @throws IOException the io exception
     */
    public static void writeNewSheet(File excelFile, Map<Integer, Map<Integer, Object>> contentMap) throws IOException {

        Workbook workbook;
        if (excelFile.exists()) {
            workbook = WorkbookFactory.create(excelFile);
        } else {
            if (excelFile.getName().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook();
            } else {
                workbook = new HSSFWorkbook();
            }
        }

        Sheet sheet = workbook.createSheet();

        for (Entry<Integer, Map<Integer, Object>> line : contentMap.entrySet()) {

            Row row = sheet.createRow(line.getKey());

            for (Entry<Integer, Object> col : line.getValue().entrySet()) {

                Cell cell = row.createCell(col.getKey());
                cell.setCellValue(String.valueOf(col.getValue()));
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFile)) {

            workbook.write(outputStream);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        String filePath = "/Users/hubery/Desktop/泛金融销户/泛金融销户账户.xlsx";
        String filePath1 = "/Users/hubery/Desktop/泛金融销户/泛金融销户账户_bak.xlsx";
        File excelFile = new File(filePath);

        try {
            Map<Integer, Map<Integer, Object>> content = readSheetContent(excelFile, 0);

            for (Map<Integer, Object> map : content.values()) {

                for (Object obj : map.values()) {
                    System.out.print(obj + "\t");
                }
                System.out.println();
            }

            writeNewSheet(new File(filePath1), content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
