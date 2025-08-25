package org.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class ExcelWriter {
    private Workbook workbook;
    private Sheet sheet;
    private String filePath;

    public ExcelWriter(String filePath, String sheetName) {
        this.filePath = filePath;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Product");
        header.createCell(1).setCellValue("ID");
        header.createCell(2).setCellValue("Original Price");
        header.createCell(3).setCellValue("Sale Price");
        header.createCell(4).setCellValue("Discount");
    }

    public void writeRow(int rowNum, List<String> values) {
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < values.size(); i++) {
            row.createCell(i).setCellValue(values.get(i));
        }
    }

    public void save() throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }
}
