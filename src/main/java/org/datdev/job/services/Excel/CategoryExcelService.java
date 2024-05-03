package org.datdev.job.services.Excel;

import org.datdev.job.entities.Category;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class CategoryExcelService implements IExcelService<Category> {
    @Override
    public void exportExcel(List<Category> categories, OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Categories");

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Trending");
        headerRow.createCell(3).setCellValue("Created_at");
        headerRow.createCell(4).setCellValue("Updated_at");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Category category : categories) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(category.getId());
            row.createCell(1).setCellValue(category.getName());
            row.createCell(2).setCellValue(category.getTrending());
            row.createCell(3).setCellValue(category.getCreated_at().format(formatter));
            row.createCell(4).setCellValue(category.getUpdated_at().format(formatter));
        }

        workbook.write(outputStream);
        workbook.close();
    }

    @Override
    public List<Category> importFromExcel(InputStream inputStream) throws IOException {
        List<Category> categories = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();

        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (currentRow.getRowNum() == 0) {
                // Bỏ qua dòng header
                continue;
            }

            Category category = new Category();
            category.setId((int) currentRow.getCell(0).getNumericCellValue());
            category.setName(currentRow.getCell(1).getStringCellValue());
            category.setTrending((int) currentRow.getCell(2).getNumericCellValue());

            // Chuyển đổi String thành LocalDateTime
            String createdAtStr = currentRow.getCell(3).getStringCellValue();
            LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            category.setCreatedAt(createdAt);

            // Tương tự cho thuộc tính updatedAt
            String updatedAtStr = currentRow.getCell(4).getStringCellValue();
            LocalDateTime updatedAt = LocalDateTime.parse(updatedAtStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            category.setUpdatedAt(updatedAt);

            categories.add(category);
        }

        workbook.close();
        return categories;
    }

}
