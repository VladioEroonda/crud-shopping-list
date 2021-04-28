package ru.eroonda.shoppinglist.builder;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;
import ru.eroonda.shoppinglist.entity.ShoppingList;

import java.util.List;

//HSSF - XLS
//XSSF - XLSX
public class XlsxBuilder {

    public static void build(ShoppingListDao dao, String sessionId){

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Shopping List");

        List<ShoppingList> allPurchases = dao.getAllPurchases(sessionId);

        int rowNum = 0;

        XSSFFont generalCellsFont = workbook.createFont();//стиль для выделения важных ячеек
        generalCellsFont.setBold(true);// Потом можно будет отрефакторить в метод
        generalCellsFont.setColor(IndexedColors.RED.index);
        XSSFCellStyle generalCellStyle = workbook.createCellStyle();
        generalCellStyle.setFont(generalCellsFont);
        generalCellStyle.setAlignment(HorizontalAlignment.CENTER);

        Row row = sheet.getRow(rowNum);
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

        XSSFFont simpleCellsFont = workbook.createFont();//стиль для выделения важных ячеек
        simpleCellsFont.setBold(true);// Потом можно будет отрефакторить в метод
        simpleCellsFont.setColor(IndexedColors.RED.index);
        XSSFCellStyle simpleCellStyle = workbook.createCellStyle();
        simpleCellStyle.setFont(generalCellsFont);
        simpleCellStyle.setAlignment(HorizontalAlignment.CENTER);

        for (ShoppingList oneListPosition : allPurchases) {
            rowNum++;

            row = sheet.createRow(rowNum);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(oneListPosition.getName());
            cell.setCellStyle(simpleCellStyle);
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(oneListPosition.getName());
            cell.setCellStyle(simpleCellStyle);
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(oneListPosition.getName());
            cell.setCellStyle(simpleCellStyle);
            cell = row.createCell(3, CellType.FORMULA);
            cell.setCellStyle(simpleCellStyle);
            String oneLineSumFormula = "B"+(rowNum+1)+"*C"+(rowNum+1);
            cell.setCellFormula(oneLineSumFormula);


        }

        //TODO:Тут добавить ещё две строчки с формулами COUNTA и SUM. Плюс сохранение в файл и оттестить
    }
}
