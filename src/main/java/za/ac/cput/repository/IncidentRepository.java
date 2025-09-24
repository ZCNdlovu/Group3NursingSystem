package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Incident;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Facility;
import za.ac.cput.domain.StatusType;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {


    List<Incident> findByStudent(Student student);


    List<Incident> findByReportedByStaff(Staff staff);


    List<Incident> findByFacility(Facility facility);
}
