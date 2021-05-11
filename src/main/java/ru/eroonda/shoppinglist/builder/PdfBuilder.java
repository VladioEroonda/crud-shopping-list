package ru.eroonda.shoppinglist.builder;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;
import ru.eroonda.shoppinglist.entity.ShoppingList;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Генерирует .pdf файл списка покупок с именем из первых 6-ти символов сессии и возвращает пользователю на скачку
 */

public class PdfBuilder {

    private static final String[] headerCellNames = {"№", "Название товара", "Кол-во", "Цена за 1 шт", "Сумма"};

    public static void build(ShoppingListDao dao, String sessionId, HttpServletResponse resp) {

        List<ShoppingList> allPurchases = dao.getAllPurchases(sessionId);

        Document document = new Document();

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {

            PdfWriter.getInstance(document, byteArrayOutputStream);

            document.open();

            Font font = FontFactory.getFont("pdffont/forpdf.ttf", "cp1251", BaseFont.EMBEDDED, 10);

            Paragraph title = new Paragraph("Shopping List");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Chunk chunk = new Chunk("", font); // отступ
            document.add(chunk);

            PdfPTable table = new PdfPTable(headerCellNames.length);

            for (int i = 0; i < headerCellNames.length; i++) { // header generator

                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                Phrase phrase = new Phrase(headerCellNames[i], font);
                header.setPhrase(phrase);
                table.addCell(header);
            }

            int count = 0;
            double totalListSum = 0;

            for (ShoppingList purchase : allPurchases) { // content generator

                count++;

                double totalPurchaseSum;

                PdfPCell сellCount = new PdfPCell();
                Phrase phraseCount = new Phrase(((Integer) count).toString(), font);
                сellCount.setPhrase(phraseCount);
                table.addCell(сellCount);

                PdfPCell cellName = new PdfPCell();
                Phrase phraseName = new Phrase(purchase.getName(), font);
                cellName.setPhrase(phraseName);
                table.addCell(cellName);

                PdfPCell cellPurchaseCount = new PdfPCell();
                Phrase phrasePurchaseCount = new Phrase(((Integer) purchase.getCount()).toString(), font);
                cellPurchaseCount.setPhrase(phrasePurchaseCount);
                table.addCell(cellPurchaseCount);

                PdfPCell cellPrice = new PdfPCell();
                Phrase phrasePrice = new Phrase(((Double) purchase.getPrice()).toString(), font);
                cellPrice.setPhrase(phrasePrice);
                table.addCell(cellPrice);

                totalPurchaseSum = purchase.getCount() * purchase.getPrice();
                totalListSum += totalPurchaseSum;

                PdfPCell cellSum = new PdfPCell();
                Phrase phraseSum = new Phrase(((Double) (totalPurchaseSum)).toString(), font);
                cellSum.setPhrase(phraseSum);
                table.addCell(cellSum);

            }

            cellsWithTotalValues("Сумма: ", ((Double) totalListSum).toString(), table, font);
            cellsWithTotalValues("Кол-во позиций: ", ((Integer) count).toString(), table, font);


            document.add(table);
            document.close();

            String genereatedFileName = nameGenerator(sessionId);

            sendFileToUser(resp, byteArrayOutputStream, genereatedFileName);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendFileToUser(HttpServletResponse response, ByteArrayOutputStream byteArrayOutputStream, String genereatedFileName) {

        try {
            byte[] outArray = byteArrayOutputStream.toByteArray();
            response.setContentType("application/pdf");
            response.setContentLength(outArray.length);
            response.setHeader("Content-Disposition", ("attachment; filename=" + genereatedFileName + ".pdf"));
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String nameGenerator(String sessionId) {

        String genereatedFileName = "Shoppinglist";

        if (sessionId == null && sessionId.length() < 5) {
            genereatedFileName += ((int) (Math.random() * 100));
        } else {
            genereatedFileName += sessionId.substring(0, 6);
        }

        return genereatedFileName;
    }

    //Нижние стобцы "Сумма" и "Кол-во позиций"
    private static void cellsWithTotalValues(String cellName, String value, PdfPTable table, Font font) {

        for (int i = 0; i < 1; i++) {

            PdfPCell sumCellName = new PdfPCell();
            sumCellName.setBackgroundColor(BaseColor.LIGHT_GRAY);
            Phrase phrase = new Phrase(cellName, font);
            sumCellName.setPhrase(phrase);
            table.addCell(sumCellName);

            PdfPCell sumCellSum = new PdfPCell();
            sumCellName.setBackgroundColor(BaseColor.LIGHT_GRAY);
            Phrase phraseSum = new Phrase(value, font);
            sumCellSum.setPhrase(phraseSum);
            table.addCell(sumCellSum);

            for (int j = 0; j < 3; j++) {
                PdfPCell emptyCell = new PdfPCell();
                Phrase emptyPhrase = new Phrase("", font);
                emptyCell.setPhrase(emptyPhrase);
                table.addCell(emptyCell);
            }
        }
    }

}
