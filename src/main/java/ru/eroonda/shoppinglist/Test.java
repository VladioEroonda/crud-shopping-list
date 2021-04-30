package ru.eroonda.shoppinglist;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
            document.open();

//            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Font font = FontFactory.getFont("Arial Unicode", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Paragraph title = new Paragraph("Shopping List");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Chunk chunk = new Chunk("", font);
            document.add(chunk);

            String[] headerCellNames = {"№", "Название товара", "Кол-во", "Цена за 1 шт", "Сумма"};

            PdfPTable table = new PdfPTable(headerCellNames.length);

            for (int i = 0; i < headerCellNames.length; i++) {

                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                Phrase phrase = new Phrase(headerCellNames[i]);
                phrase.setFont(font);
                header.setPhrase(phrase);
                table.addCell(header);
            }


            table.addCell("row 1, col 1");
            table.addCell("row 1, col 2");
            table.addCell("row 1, col 3");


            document.add(table);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
