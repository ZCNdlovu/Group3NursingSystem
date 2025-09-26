package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DocumentFactoryTest {


    @Test
    void createDocument() {
        Student student = new Student("student123");
        Staff staff = new Staff("staff123");
        Placement placement = new Placement(1);
        Staff approver = new Staff("staff123");

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
        Document doc2 = DocumentFactory.createBasicDocument(new Student("student123"),
                "/Document/docs/file2.pdf",
                "file2.pdf");

        assertNotNull(doc2);
        System.out.println(doc2.toString());
    }
}

