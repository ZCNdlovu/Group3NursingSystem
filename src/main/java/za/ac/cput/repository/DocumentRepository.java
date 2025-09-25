package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.*;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {

    List<Document> findByStudent(Student student);
    List<Document> findByPlacement(Placement placement);
    List<Document> findByStatus(StatusType statusType);

    List<Document> findByStaff(Staff staff); //optional
}

