package ru.eroonda.shoppinglist;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;
import ru.eroonda.shoppinglist.dao.ShoppingListDaoImpl;
import ru.eroonda.shoppinglist.entity.ShoppingList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Shopping List");

        String sessionId = "324234";
        List<ShoppingList> allPurchases = new ArrayList<>();
        allPurchases.add(new ShoppingList(1, "Tovar1", 1, 2.0, "12345", false));
        allPurchases.add(new ShoppingList(2, "Tovar2", 2, 4.0, "12345", false));
        allPurchases.add(new ShoppingList(3, "Tovar3", 3, 6.0, "12345", false));
        allPurchases.add(new ShoppingList(4, "Tovar4", 3, 8.0, "12345", false));

        int rowNum = 0;

        XSSFFont generalCellsFont = workbook.createFont();//стиль для выделения важных ячеек
        generalCellsFont.setBold(true);// Потом можно будет отрефакторить в метод
        generalCellsFont.setColor(IndexedColors.RED.index);
        XSSFCellStyle generalCellStyle = workbook.createCellStyle();
        generalCellStyle.setFont(generalCellsFont);
        generalCellStyle.setAlignment(HorizontalAlignment.CENTER);

        Row row = sheet.createRow(rowNum);
        Cell cell = row.createCell(0, CellType.STRING); //Делаем оглавление
        cell.setCellValue("Название товара");
        cell.setCellStyle(generalCellStyle);
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue("Кол-во");
        cell.setCellStyle(generalCellStyle);
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue("Цена за 1 ед");
        cell.setCellStyle(generalCellStyle);
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Сумма");
        cell.setCellStyle(generalCellStyle);

        XSSFCellStyle simpleCellStyle = workbook.createCellStyle();
        simpleCellStyle.setAlignment(HorizontalAlignment.CENTER);

        for (ShoppingList oneListPosition : allPurchases) {
            ++rowNum;

            row = sheet.createRow(rowNum);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(oneListPosition.getName());
            cell.setCellStyle(simpleCellStyle);
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(oneListPosition.getCount());
            cell.setCellStyle(simpleCellStyle);
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(oneListPosition.getPrice());
            cell.setCellStyle(simpleCellStyle);
            cell = row.createCell(3, CellType.FORMULA);
            cell.setCellStyle(simpleCellStyle);
            String oneLineSumFormula = "B" + (rowNum + 1) + "*C" + (rowNum + 1);
            cell.setCellFormula(oneLineSumFormula);
        }

        row = sheet.createRow(rowNum + 2);
        cell=row.createCell(0,CellType.STRING);
        cell.setCellStyle(generalCellStyle);
        cell.setCellValue("Кол-во позиций:");
        cell=row.createCell(1,CellType.FORMULA);
        cell.setCellStyle(generalCellStyle);
        cell.setCellFormula("COUNTA(A2:A" + (rowNum+1) +")");

        row = sheet.createRow(rowNum + 3);
        cell=row.createCell(0,CellType.STRING);
        cell.setCellStyle(generalCellStyle);
        cell.setCellValue("Общая сумма:");
        cell=row.createCell(1,CellType.FORMULA);
        cell.setCellStyle(generalCellStyle);
        cell.setCellFormula("SUM(D2:D" + (rowNum+1) +")");


        String genereatedFileName = "Shoppinglist";

        if (sessionId == null && sessionId.length() < 5) {
            genereatedFileName += ((int) (Math.random() * 100));
        } else {
            genereatedFileName += sessionId.substring(0, 6);
        }

        File file = new File((genereatedFileName + ".xlsx"));

        try {
            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Created file: " + file.getAbsolutePath());
    }
}
