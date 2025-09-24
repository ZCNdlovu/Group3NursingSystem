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

    // 🔹 Find all incidents reported for a specific student
    List<Incident> findByStudent(Student student);

    // 🔹 Find all incidents reported by a specific staff member
    List<Incident> findByReportedByStaff(Staff staff);

    // 🔹 Find all incidents that occurred in a specific facility
    List<Incident> findByFacility(Facility facility);
}
