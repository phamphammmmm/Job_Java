package org.datdev.job.services.Excel;

import org.datdev.job.entities.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class UserExcelService implements IExcelService<User> {
    @Override
    public void exportExcel(List<User> users, OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Role");

        for (User user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getEmail());
            row.createCell(3).setCellValue(user.getRole());
        }

        workbook.write(outputStream);
        workbook.close();
    }

    @Override
    public List<User> importFromExcel(InputStream inputStream) throws IOException {
        List<User> users = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();

        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (currentRow.getRowNum() == 0) {
                // Bỏ qua dòng header
                continue;
            }

            User user = new User();
            user.setId((int) currentRow.getCell(0).getNumericCellValue());
            user.setName(currentRow.getCell(1).getStringCellValue());
            user.setEmail(currentRow.getCell(2).getStringCellValue());
            user.setRole(currentRow.getCell(3).getStringCellValue());

            users.add(user);
        }

        workbook.close();
        return users;
    }
}
