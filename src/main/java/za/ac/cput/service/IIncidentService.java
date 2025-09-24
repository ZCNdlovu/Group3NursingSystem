package za.ac.cput.service;

import za.ac.cput.domain.Facility;
import za.ac.cput.domain.Incident;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Student;

import java.util.List;
import java.util.Optional;

public interface IIncidentService extends IService<Incident, String> {
    Optional<Incident> findByStudent(Student student);

    List<Incident> findByReportedByStaff(Staff staff);


    List<Incident> findByFacility(Facility facility);
}
