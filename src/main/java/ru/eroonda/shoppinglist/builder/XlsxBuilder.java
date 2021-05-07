package ru.eroonda.shoppinglist.builder;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;
import ru.eroonda.shoppinglist.entity.ShoppingList;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class XlsxBuilder {

    public static void build(ShoppingListDao dao,String sessionId, HttpServletResponse resp){

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

        sendFileToUse(resp, workbook, genereatedFileName);

    }

    private static void sendFileToUse(HttpServletResponse response, XSSFWorkbook workbook,String genereatedFileName){

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        try {
            workbook.write(outByteStream);
            byte [] outArray = outByteStream.toByteArray();
            response.setContentType("application/ms-excel");
            response.setContentLength(outArray.length);
            response.setHeader("Content-Disposition", ("attachment; filename="+genereatedFileName+".xlsx"));
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();//TODO exc
        }

    }
}
