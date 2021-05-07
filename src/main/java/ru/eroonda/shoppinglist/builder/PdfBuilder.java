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

public class PdfBuilder {

    private static final String[] headerCellNames = {"№", "Название товара", "Кол-во", "Цена за 1 шт", "Сумма"};

    public static void build(ShoppingListDao dao, String sessionId, HttpServletResponse resp) {

        List<ShoppingList> allPurchases = dao.getAllPurchases(sessionId);

//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();){

            PdfWriter.getInstance(document, byteArrayOutputStream);
//            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
//            PdfDocument pdfDocument = new PdfDocument(writer);
//            Document document = new Document(pdfDocument);

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
            double totalSum = 0;

            for (ShoppingList purchase : allPurchases) { // content generator

                count++;

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

                totalSum = purchase.getCount() * purchase.getPrice();

                PdfPCell cellSum = new PdfPCell();
                Phrase phraseSum = new Phrase(((Double) (totalSum)).toString(), font);
                cellSum.setPhrase(phraseSum);
                table.addCell(cellSum);

            }

            for (int i = 0; i < 1; i++) { // sum generator ТУТ сделай рефактор в отдельный метод, и др метод с ячейкой с кол-вом потом ещё

                PdfPCell sumCellName = new PdfPCell();
                sumCellName.setBackgroundColor(BaseColor.LIGHT_GRAY);
                Phrase phrase = new Phrase("Сумма:", font);
                sumCellName.setPhrase(phrase);
                table.addCell(sumCellName);

                PdfPCell sumCellSum = new PdfPCell();
                sumCellName.setBackgroundColor(BaseColor.LIGHT_GRAY);
                Phrase phraseSum = new Phrase(((Double) totalSum).toString(), font);
                sumCellSum.setPhrase(phraseSum);
                table.addCell(sumCellSum);

                for (int j = 0; j < 3; j++) {
                    PdfPCell emptyCell = new PdfPCell();
                    Phrase emptyPhrase = new Phrase("", font);
                    emptyCell.setPhrase(emptyPhrase);
                    table.addCell(emptyCell);
                }

            }

            document.add(table);
            document.close();

            sendFileToUser(resp, byteArrayOutputStream);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendFileToUser(HttpServletResponse response, ByteArrayOutputStream byteArrayOutputStream) {

        try {
            byte[] outArray = byteArrayOutputStream.toByteArray();
            response.setContentType("application/pdf");
            response.setContentLength(outArray.length);
            //TODO:generating name method
            response.setHeader("Content-Disposition", ("attachment; filename=" + "genereatedFileName" + ".pdf"));
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();//TODO exc
        }
    }
}
