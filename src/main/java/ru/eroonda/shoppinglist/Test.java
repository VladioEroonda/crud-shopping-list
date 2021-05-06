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

            Font font = FontFactory.getFont("pdffont/forpdf.ttf", "cp1251", BaseFont.EMBEDDED, 10);

            Paragraph title = new Paragraph("Shopping List");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Chunk chunk = new Chunk("", font);
            document.add(chunk);

            String[] headerCellNames = {"№", "Название товара", "Кол-во", "Цена за 1 шт", "Сумма"};

            PdfPTable table = new PdfPTable(headerCellNames.length);

            for (int i = 0; i < headerCellNames.length; i++) { // header generator

                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                Phrase phrase = new Phrase(headerCellNames[i], font);
                header.setPhrase(phrase);
                table.addCell(header);
            }

//            table.addCell(new PdfPCell(new Phrase("row 2, col 1",font)));
//            table.addCell(new PdfPCell(new Phrase("row 2, col 2",font)));
//            table.addCell(new PdfPCell(new Phrase("row 2, col 3",font)));
//            table.addCell(new PdfPCell(new Phrase("row 2, col 4",font)));
//            table.addCell(new PdfPCell(new Phrase("row 2, col 5",font)));
//            table.addCell(new PdfPCell(new Phrase("row 2, col 6",font)));
//            table.addCell(new PdfPCell(new Phrase("row 2, col 7",font)));

//            for (int i = 0; i < headerCellNames.length; i++) {
//                for (int j = 0; j < 2; j++) {
//                    PdfPCell contentCell = new PdfPCell();
//                    Phrase phrase = new Phrase("инфо",font);
//                    Phrase phrase2 = new Phrase("инфо2",font);
//                    Phrase phrase3 = new Phrase("инфо3",font);
//                    contentCell.setPhrase(phrase);
//                    contentCell.setPhrase(phrase2);
//                    contentCell.setPhrase(phrase3);
//                    table.addCell(contentCell);
//                    table.addCell(contentCell);
//                    table.addCell(contentCell);
//                }
//            }

            for (int i = 0; i < 2; i++) {
                PdfPCell Cell = new PdfPCell();
                Phrase phrase = new Phrase("инфо", font);
                Cell.setPhrase(phrase);
                table.addCell(Cell);

                PdfPCell Cell2 = new PdfPCell();
                Phrase phrase2 = new Phrase("инфо2", font);
                Cell2.setPhrase(phrase2);
                table.addCell(Cell2);

                PdfPCell Cell3 = new PdfPCell();
                Phrase phrase3 = new Phrase("инфо3", font);
                Cell3.setPhrase(phrase3);
                table.addCell(Cell3);

                PdfPCell Cell4 = new PdfPCell();
                Phrase phrase4 = new Phrase("инфо4", font);
                Cell4.setPhrase(phrase4);
                table.addCell(Cell4);

                PdfPCell Cell5 = new PdfPCell();
                Phrase phrase5 = new Phrase("инфо5", font);
                Cell5.setPhrase(phrase5);
                table.addCell(Cell5);
            }


            document.add(table);
            document.close();

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
