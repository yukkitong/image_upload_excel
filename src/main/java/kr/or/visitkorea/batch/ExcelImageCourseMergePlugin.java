package kr.or.visitkorea.batch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.visitkorea.model.Image;
import kr.or.visitkorea.repository.ContentMasterTableUtils;
import kr.or.visitkorea.repository.Executer;
import kr.or.visitkorea.repository.ImageTableUtils;

public class ExcelImageCourseMergePlugin implements Plugin {

    private static final Logger logger = LoggerFactory.getLogger(ExcelImageCourseMergePlugin.class);

    public void run(String[] args) {
        // Print out the USAGE.
        if (args.length == 0) {
            System.out.println("Args is empty!!");
            System.out.println("[ Usage ]");
            System.out.println("args[0] = fullpath of input excel file [required]");
            System.out.println("args[1] = start column index of `contentId` cell [option, default = 0]");
            System.out.println("args[2] = start row index of `contentId` cell [option, default = 8]");
            System.out.println(
                    "ex) <execute-command> ~/user/test.xsl 1 : input file path `~/user/test.xsl`, start column number: 1");
            return;
        }

        // Parse arguments.
        final String filepath = args[0];
        final int startColumnIndex = (args.length > 1 && args[1] != null) ? Integer.parseInt(args[1]) : 0;
        final int startRowIndex = (args.length > 2 && args[2] != null) ? Integer.parseInt(args[2]) : 8;

        System.out.println("input file: " + filepath);
        System.out.println("startColumnIndex: " + startColumnIndex);
        System.out.println("startRowIndex: " + startRowIndex);

        // Parse excel document.
        Map<String, List<ExcelImage>> imageMap = null;
        try {
            imageMap = loadExcelFile(new File(filepath), startColumnIndex, startRowIndex);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        // Final Goal: Manipulate DATABASE!
        Executer executer = null;
        try {
            executer = new Executer();
            for (String contentId : imageMap.keySet()) {
                String cotId = ContentMasterTableUtils.getCotId(executer, contentId);
                if (cotId != null) {
                    for (ExcelImage image : imageMap.get(contentId)) {
                        if (!ImageTableUtils.hasItem(executer, cotId, image.url)) {
                            Image newImage = new Image(cotId, image.url, image.title);
                            int count = ImageTableUtils.insertItem(executer, newImage);
                            logger.info("[INSERT] COT_ID={}, URL={}, COUNT={}", cotId, image.url,
                                    String.valueOf(count));
                        } else {
                            logger.info("[ SKIP ] COT_ID={}, URL={}", cotId, image.url);
                        }
                    }
                } else {
                    logger.info("[ SKIP ] COT_ID is NULL");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            if (executer != null) {
                try {
                    executer.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    logger.error(e1.getMessage());
                }
            }
        }
    }

    private static Map<String, List<ExcelImage>> loadExcelFile(File file, int startColumnIndex, int startRowIndex)
            throws IOException {
        HashMap<String, List<ExcelImage>> result = new HashMap<String, List<ExcelImage>>();
        Workbook wb = null;
        try {
            wb = new HSSFWorkbook(new FileInputStream(file));
            final int numberOfSheets = wb.getNumberOfSheets();
            for (int sheetIndex = 0; sheetIndex < numberOfSheets; sheetIndex++) {
                Sheet sheet = wb.getSheetAt(sheetIndex);
                logger.info("Sheet Name :: {}", sheet.getSheetName());

                int currentRowIndex = 0;
                for (Row row : sheet) {
                    currentRowIndex++;
                    if (currentRowIndex < startRowIndex)
                        continue; // skip row

                    String cid = getCellValue(row.getCell(startColumnIndex));
                    String title = getCellValue(row.getCell(startColumnIndex + 2));
                    String url = getCellValue(row.getCell(startColumnIndex + 16));
                    String main = getCellValue(row.getCell(startColumnIndex + 17));

                    if (url == null || url.trim().length() == 0)
                        continue; // skip row

                    if (result.get(cid) == null) {
                        result.put(cid, new ArrayList<ExcelImage>());
                    }
                    result.get(cid).add(new ExcelImage(cid, title, url, "O".equals(main)));
                }
            }
        } finally {
            if (wb != null) {
                wb.close();
            }
        }
        return result;
    }

    private static String getCellValue(Cell cell) {
        String valueString = "";
        if (cell != null) {
            CellType cellType = cell.getCellTypeEnum();
            if (cellType.equals(CellType.STRING)) {
                valueString = cell.getStringCellValue();
            } else if (cellType.equals(CellType.BLANK)) {
                valueString = "";
            } else if (cellType.equals(CellType.BOOLEAN)) {
                valueString = String.valueOf(cell.getBooleanCellValue());
            } else if (cellType.equals(CellType.ERROR)) {
                valueString = "ERROR";
            } else if (cellType.equals(CellType.FORMULA)) {
                valueString = cell.getCellFormula();
            } else if (cellType.equals(CellType.NUMERIC)) {
                valueString = String.valueOf(cell.getNumericCellValue());
            }
        }
        return valueString;
    }
}