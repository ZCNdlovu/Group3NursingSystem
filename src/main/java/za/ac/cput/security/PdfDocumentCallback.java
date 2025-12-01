package za.ac.cput.security;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.io.IOException;

@FunctionalInterface
public interface PdfDocumentCallback {
    // The lambda expression will implement this method.
    // The type is com.itextpdf.text.Document
    void writeContent(Document iTextDocument) throws DocumentException, IOException;
}
