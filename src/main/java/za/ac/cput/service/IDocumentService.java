package za.ac.cput.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Document;
import za.ac.cput.domain.Placement;
import za.ac.cput.domain.Report;
import za.ac.cput.domain.Student;

import java.util.*;

@Service
public interface IDocumentService extends IService<Document, String>{

    void deleteById(String documentId);
    List<Document> getAll();

    Document processFileUpload(MultipartFile file, Student studentId, Placement placementId, String uploadedBy, String fileType);

    Optional<Document> findById(String documentId);

    Document processFileUpload(MultipartFile file, String studentId, Integer placementId, String uploadedBy, String fileType);
}
