package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DocumentFactoryTest {


    @Test
    void createDocument() {
        Student student = new Student();
        Staff staff = new Staff();
        Placement placement = new Placement();
        Staff approver = new Staff();

        Document doc1 = DocumentFactory.createDocument(
                "0102",
                student,
                staff,
                placement,
                "/Document/docs/file1.pdf",
                "file1.pdf",
                12L,
                LocalDate.now(),
                StatusType.PENDING,
                approver);

        assertNotNull(doc1);
        System.out.println(doc1.toString());
    }

    @Test
    void createBasicDocument() {
        Document doc2 = DocumentFactory.createBasicDocument(new Student(),
                "/Document/docs/file2.pdf",
                "file2.pdf");

        assertNotNull(doc2);
        System.out.println(doc2.toString());
    }
}

