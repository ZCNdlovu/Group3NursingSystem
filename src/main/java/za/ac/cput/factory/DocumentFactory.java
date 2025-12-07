package za.ac.cput.factory;

import za.ac.cput.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DocumentFactory {

    public static Document createDocument(Integer documentId,
                                          Student student,
                                          Staff staff,
                                          Placement placement,
                                          String filePath,
                                          String fileName,
                                          Long sizeBytes,
                                          LocalDate uploadedAt,
                                          StatusType status,
                                          Staff approvedByStaff){
        return new Document.Builder()
                .setDocumentId(documentId)
                .setStudent(student)
                .setStaff(staff)
                .setPlacement(placement)
                .setFilePath(filePath)
                .setFileName(fileName)
                .setSizeBytes(sizeBytes)
                .setUploadedAt(LocalDateTime.now())
                .setStatus(status != null ? status: StatusType.PENDING)
                .setApprovedByStaff(approvedByStaff)
                .build();
    }

    //optional version(overloaded).
    public static Document createBasicDocument(Student student,
                                               String filePath,
                                               String fileName) {

        return new Document.Builder()
                .setStudent(student)
                .setFilePath(filePath)
                .setFileName(fileName)
                .setUploadedAt(LocalDateTime.now())
                .setStatus(StatusType.APPROVED)
                .build();
    }
}
