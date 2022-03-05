package service.impl;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.Bill;
import models.Product;
import service.interfaces.BillToPdf;


import java.io.FileOutputStream;

import static service.impl.BillCalculations.*;


public class BillToPdfImpl implements BillToPdf {

    private static final int TABLE_WIDTH_PERCENTAGE = 60;

    @Override
    public void createPdf(Bill bill) {
        Document document = new Document();

        try {
            // to war change to ../webapps/ClevertecTestEx-1.0-SNAPSHOT/bill.pdf
            PdfWriter.getInstance(document, new FileOutputStream("bills/bill.pdf"));

            document.open();

            document.add(billTop(bill));
            document.add(line());
            document.add(billBody(bill));
            document.add(line());
            document.add(billBot(bill));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Paragraph billTop(Bill bill) {

        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);

        paragraph.add(new Chunk("CASH RECEIPT\n", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD)));
        paragraph.add(new Chunk(bill.getShopName() + "\n"));
        paragraph.add(new Chunk(bill.getAddress() + "\n"));
        paragraph.add(new Chunk("Tel: " + bill.getTel() + "\n"));
        paragraph.add(new Chunk(String.format("CASHIER: #%-10d DATE: %tD\n", bill.getId(), bill.getBillDate())));
        paragraph.add(new Chunk(String.format("%-30s TIME:  %tT\n", "", bill.getBillDate())));

        return paragraph;
    }

    private static PdfPTable billBody(Bill bill) {

        Font headFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK);


        PdfPTable table = new PdfPTable(4);
        PdfPCell discount = new PdfPCell();
        PdfPCell qty = new PdfPCell(new Phrase("QTY", headFont));
        PdfPCell description = new PdfPCell(new Phrase("DESCRIPTION", headFont));
        PdfPCell price = new PdfPCell(new Phrase("PRICE", headFont));
        PdfPCell total = new PdfPCell(new Phrase("TOTAL", headFont));
        PdfPCell totalPrice = new PdfPCell(new Phrase("TOTAL", headFont));


        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        discount.setBorder(Rectangle.NO_BORDER);
        qty.setBorder(Rectangle.NO_BORDER);
        description.setBorder(Rectangle.NO_BORDER);
        price.setBorder(Rectangle.NO_BORDER);
        total.setBorder(Rectangle.NO_BORDER);
        total.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalPrice.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalPrice.setBorder(Rectangle.NO_BORDER);


        try {
            table.setWidthPercentage(TABLE_WIDTH_PERCENTAGE);
            table.setWidths(new float[]{1f, 4f, 2f, 2f});
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        discount.setColspan(4);

        table.addCell(qty);
        table.addCell(description);
        table.addCell(price);
        table.addCell(total);

        for (Product p : bill.getProducts()) {
            table.addCell(String.valueOf(p.getQuantity()));
            table.addCell(p.getName());
            table.addCell("$" + p.getPrice());
            totalPrice.setPhrase(new Phrase(String.format("$%.2f",totalPricePerProduct(p))));
            table.addCell(totalPrice);

            if (p.isDiscount() && p.getQuantity() >= 5) {
                discount.setPhrase(new Phrase(
                        String.format(" %-57s $%.2f\n", "discount: ", p.getPrice() * p.getQuantity() * 10 / 100)));
                table.addCell(discount);
            }
        }


        return table;
    }


    private static PdfPTable billBot(Bill bill) {

        PdfPTable table = new PdfPTable(2);
        PdfPCell name = new PdfPCell();
        PdfPCell value = new PdfPCell();
        Font totalFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);

        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.setWidthPercentage(TABLE_WIDTH_PERCENTAGE);
        name.setBorder(Rectangle.NO_BORDER);
        value.setBorder(Rectangle.NO_BORDER);
        value.setHorizontalAlignment(Element.ALIGN_RIGHT);


        table.addCell("TOTAL WITHOUT DISCOUNT: ");
        value.setPhrase(new Phrase(String.format("$%.2f", totalWithOutDiscount(bill))));
        table.addCell(value);

        name.setPhrase(new Phrase("TOTAL",totalFont));
        table.addCell(name);
        value.setPhrase(new Phrase(String.format("$%.2f", totalWithDiscount(bill)),totalFont));
        table.addCell(value);


        return table;
    }

    private static Paragraph line() {
        Paragraph paragraph = new Paragraph(new Chunk(String.format("%-100s", "")).setUnderline(1f, 6f));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }


}
