package za.ac.cput.security;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.function.Consumer;
@Component
public class PdfGenerationUtility {

    // Use an explicit import to avoid conflict with your domain.Document
    // If your domain Document is in za.ac.cput.domain.Document,
    // this com.itextpdf.text.Document is a different class.

    public void createPdf(String filePath) {

        // 1. Instantiate the iText Document class
        // NOTE: We use the fully qualified name (com.itextpdf.text.Document)
        // to clearly distinguish it from your project's 'Document' domain class.
        com.itextpdf.text.Document iTextDocument = new com.itextpdf.text.Document();

        try {
            // Define the output stream to the file
            OutputStream outputStream = new FileOutputStream(filePath);

            // 2. Link the iText Document to a physical output stream via PdfWriter
            // This is the core step that initializes the PDF file.
            PdfWriter.getInstance(iTextDocument, outputStream);

            // 3. Call the core methods

            // Open the document for writing content
            iTextDocument.open();

            // Add content to the document
            iTextDocument.add(new Paragraph("Document Title: Sample PDF"));
            iTextDocument.add(new Paragraph("-------------------------------------"));
            iTextDocument.add(new Paragraph("Some Content: This PDF was generated using the iText library."));

        } catch (FileNotFoundException e) {
            System.err.println("Error: File path is incorrect or file cannot be created. " + e.getMessage());
        } catch (DocumentException e) {
            System.err.println("Error creating the PDF document. " + e.getMessage());
        } finally {
            // 4. Close the document in a finally block to ensure it's always closed
            iTextDocument.close();
            System.out.println("PDF creation attempt finished. File: " + filePath);
        }
    }
    public byte[] generatePdfBytes(PdfDocumentCallback contentWriter) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Execute the lambda (your report writing logic) here, passing the iText Document
            contentWriter.writeContent(document);

        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
        return baos.toByteArray();
    }
    public static void main(String[] args) {
        new PdfGenerationUtility().createPdf("StudentReport.pdf");
    }
}
