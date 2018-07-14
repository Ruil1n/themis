package dangod.themis.core.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HSSF {
    //指定存储文件夹

    private Workbook wb = null;
    private String folderName;
    private String fileName;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String getSuffix(String fileName){
        String[] arr = fileName.split("[.]");
        return arr[arr.length-1].toLowerCase();
    }
    public HSSF(String folderName, String fileName) {
        this.folderName = folderName;
        this.fileName = fileName;
        open(getSuffix(fileName));
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    //Excel文件保存
    private int save() {
        FileOutputStream out = null;
        String folderPath = folderName + File.separator;
        File f = new File(folderPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            out = new FileOutputStream(folderPath + fileName);
            wb.write(out);
            out.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            return 1;
        }
        return 0;
    }

    public List<Sheet> create(String... sheetName) {
        wb = new HSSFWorkbook();
        List<Sheet> listSheet = new ArrayList<Sheet>();
        for (String name : sheetName) {
            Sheet sheet = wb.createSheet(name);
            listSheet.add(sheet);
        }
        save();
        return listSheet;
    }

    private int open(String suffix) {
        FileInputStream in = null;
        try {
            String folderPath = folderName + File.separator;
            in = new FileInputStream(folderPath + fileName);
            if(suffix.equals("xls")) {
                POIFSFileSystem fs = new POIFSFileSystem(in);
                wb = new HSSFWorkbook(fs);
            }
            else if (suffix.equals("xlsx")) {
                wb = new XSSFWorkbook(in);
            }
            in.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            create();
            return 1;
        }
        return 0;
    }

    public void insert(int sheetIndex, int rowIndex, int cellIndex, String[] fieldName, List list) {
        try {
            if (wb == null) throw new Exception("未打开文件");
            int y;
            Row row = null;
            Sheet sheet = wb.getSheetAt(sheetIndex);
            if (fieldName != null) {
                row = sheet.createRow(rowIndex++);
                y = cellIndex;
                for (String name : fieldName) {
                    row.createCell(y++).setCellValue(name);
                }
            }
            for (Object o : list) {
                row = sheet.createRow(rowIndex++);
                Field[] fields = o.getClass().getDeclaredFields();
                y = cellIndex;
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (field.get(o) != null && !"".equals(field.get(o).toString())) {
                            row.createCell(y++).setCellValue(field.get(o).toString());
                        }
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
            save();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void insert(int sheetIndex, int rowIndex, int cellIndex, String value) {
        try {
            if (wb == null) throw new Exception("未打开文件");
            Sheet sheet = wb.getSheetAt(sheetIndex);
            sheet.createRow(rowIndex).createCell(cellIndex).setCellValue(value);
            save();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void set(int sheetIndex, int rowIndex, int cellIndex, String[] fieldName, List list) {
        try {
            if (wb == null) throw new Exception("未打开文件");
            int y;
            Row row = null;
            Cell cell = null;
            Sheet sheet = wb.getSheetAt(sheetIndex);
            if (fieldName != null) {
                row = sheet.getRow(rowIndex);
                if (row == null) {
                    row = sheet.createRow(rowIndex);
                }
                rowIndex++;
                y = cellIndex;
                for (String name : fieldName) {
                    cell = row.getCell(y);
                    if (cell == null) {
                        cell = row.createCell(y);
                    }
                    cell.setCellValue(name);
                    y++;
                }
            }
            for (Object o : list) {
                row = sheet.getRow(rowIndex);
                if (row == null) {
                    row = sheet.createRow(rowIndex);
                }
                rowIndex++;
                Field[] fields = o.getClass().getDeclaredFields();
                y = cellIndex;
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (field.get(o) != null && !"".equals(field.get(o).toString())) {
                            cell = row.getCell(y);
                            if (cell == null) {
                                cell = row.createCell(y);
                            }
                            cell.setCellValue(field.get(o).toString());
                            y++;
                        }
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
            save();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void set(int sheetIndex, int rowIndex, int cellIndex, String value) {
        try {
            if (wb == null) throw new Exception("未打开文件");
            Sheet sheet = wb.getSheetAt(sheetIndex);
            Row row = null;
            Cell cell = null;
            row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            cell = row.getCell(cellIndex);
            if (cell == null) {
                cell = row.createCell(cellIndex);
            }
            cell.setCellValue(value);
            save();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    
    public String get(int sheetIndex, int rowIndex, int cellIndex) {
        String str = "";
        try {
            if (wb == null) throw new Exception("未打开文件");
            Cell cell = wb.getSheetAt(sheetIndex).getRow(rowIndex).getCell(cellIndex);
            cell.setCellType(Cell.CELL_TYPE_STRING);//处理读取xls时 单元格使用各类函数的数据读取问题
            str = cell.getStringCellValue();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return str;
    }

    public List<String> getRowValue(int sheetIndex, int rowIndex) {
        List<String> cellList = new ArrayList<>();
        try {
            int col = this.getRowSize(sheetIndex, rowIndex);
            return getRowValue(sheetIndex, rowIndex, col);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return cellList;
    }

    public List<String> getRowValue(int sheetIndex, int rowIndex, int colSize) {
        List<String> cellList = new ArrayList<>();
        try {
            if (wb == null) throw new Exception("未打开文件");
            int col = this.getRowSize(sheetIndex, rowIndex);
            for(int i = 0;i<col;i++){
                Cell cell = wb.getSheetAt(sheetIndex).getRow(rowIndex).getCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);//处理读取xls时 单元格使用各类函数的数据读取问题
                cellList.add(cell.getStringCellValue().trim());
            }
            for(int i = col-1; i<colSize;i++){
                cellList.add("");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return cellList;
    }

    public int getSheetRowSize(int sheetIndex){
        return wb.getSheetAt(sheetIndex).getLastRowNum();
    }

    public int getRowSize(int sheetIndex, int rowIndex){
        return wb.getSheetAt(sheetIndex).getRow(rowIndex).getLastCellNum();
    }
    
    public int close() throws Exception {
        wb = null;
        return 0;
    }

    public int saveAndClose() throws Exception {
        save();
        wb = null;
        return 0;
    }
}
