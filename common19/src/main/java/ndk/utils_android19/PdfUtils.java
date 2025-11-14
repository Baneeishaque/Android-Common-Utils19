package ndk.utils_android19;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ndk.utils_android1.ToastUtils1;

public class PdfUtils {

    boolean createPdf(String TAG, Context context) {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        boolean isPresent = true;
        if (!docsFolder.exists()) {
            isPresent = docsFolder.mkdir();
        }
        if (isPresent) {
            File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), "pdfdemo");
            if (!pdfFolder.exists()) {
                pdfFolder.mkdir();
                Log.i(TAG, "Pdf Directory created");
            }

            //Create time stamp
            Date date = new Date();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

            File sample_pdf = new File(pdfFolder + "/" + timeStamp + ".pdf");


            try {
                FileOutputStream output = new FileOutputStream(sample_pdf);

                //Step 1 - Create PdfWriter
                PdfWriter writer = new PdfWriter(output);

                //Step 2 - Create PdfDocument
                PdfDocument pdfDoc = new PdfDocument(writer);

                //Step 3 - Create Document with page size
                Document document = new Document(pdfDoc, PageSize.A4);
                document.setMargins(50, 50, 50, 50);

                //Step 4 - Add content

                // Create fonts
                PdfFont courierFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.COURIER);
                PdfFont helveticaFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA);
                PdfFont helveticaBoldFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);

                // Create link for "BackToTop" anchor
                String backToTopDestination = "BackToTop";
                Paragraph anchorParagraph = new Paragraph("First page of the document.")
                        .setDestination(backToTopDestination)
                        .setMarginTop(50);
                document.add(anchorParagraph);

                // Add paragraph with custom font and color
                Paragraph paragraph2 = new Paragraph("Some more text on the first page with different color and font type.")
                        .setFont(courierFont)
                        .setFontSize(14)
                        .setBold()
                        .setFontColor(new DeviceCmyk(0f, 1f, 0f, 0f));
                document.add(paragraph2);

                // Chapter 1 title
                Paragraph title1 = new Paragraph("Chapter 1")
                        .setFont(helveticaBoldFont)
                        .setFontSize(18)
                        .setBold()
                        .setItalic()
                        .setFontColor(new DeviceCmyk(0f, 1f, 1f, 0.067f))
                        .setMarginTop(20);
                document.add(title1);

                // Section 1 title
                Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1")
                        .setFont(helveticaBoldFont)
                        .setFontSize(16)
                        .setBold()
                        .setFontColor(new DeviceCmyk(0f, 1f, 1f, 0.067f))
                        .setMarginTop(10);
                document.add(title11);

                Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");
                document.add(someSectionText);

                someSectionText = new Paragraph("Following is a 3 X 2 table.");
                document.add(someSectionText);

                // Create table with 3 columns
                float[] columnWidths = {1, 1, 1};
                Table t = new Table(UnitValue.createPercentArray(columnWidths));
                t.setMarginTop(25);
                t.setMarginBottom(25);

                // Add header cells
                Cell c1 = new Cell().add(new Paragraph("Header1"));
                t.addCell(c1);

                Cell c2 = new Cell().add(new Paragraph("Header2"));
                t.addCell(c2);

                Cell c3 = new Cell().add(new Paragraph("Header3"));
                t.addCell(c3);

                // Add data cells
                t.addCell(new Cell().add(new Paragraph("1.1")));
                t.addCell(new Cell().add(new Paragraph("1.2")));
                t.addCell(new Cell().add(new Paragraph("1.3")));

                document.add(t);

                // Create list
                List l = new List();
                l.add(new ListItem("First item of list"));
                l.add(new ListItem("Second item of list"));
                document.add(l);

//                Image image2 = null;
//                try {
//                    image2 = Image.getInstance("IBMLogo.bmp");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                image2.scaleAbsolute(120f, 120f);
//
//                section1.add(image2);

                Paragraph title2 = new Paragraph("Using Anchor")
                        .setFont(helveticaBoldFont)
                        .setFontSize(16)
                        .setBold()
                        .setFontColor(new DeviceCmyk(0f, 1f, 0f, 0f))
                        .setMarginTop(5000);
                document.add(title2);

                // Create link back to top
                Link backToTopLink = new Link("Back To Top", PdfAction.createGoTo(backToTopDestination));
                Paragraph linkParagraph = new Paragraph().add(backToTopLink);
                document.add(linkParagraph);

                document.add(new Paragraph("mSubjectEditText.getText().toString()"));
                document.add(new Paragraph("mBodyEditText.getText().toString()"));

                //Step 5: Close the document
                document.close();
                return true;

            } catch (IOException | FileNotFoundException e) {
                e.printStackTrace();
                Log.i(TAG, "Pdf Creation failure " + e.getLocalizedMessage());
                ToastUtils1.longToast(context, "Pdf fail");
            }
        } else {
            Log.i(TAG, "Folder Creation failure ");
            ToastUtils1.longToast(context, "Folder fail");
        }
        return false;
    }
}
