package za.ac.cput.service;

import za.ac.cput.domain.Document;
import java.util.*;

public interface IDocumentService extends IService<Document, String>{

    void deleteById(String documentId);
    List<Document> getAll();
    Optional<Document> findById(String documentId);
}
