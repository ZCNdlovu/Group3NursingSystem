package za.ac.cput.factory;

import za.ac.cput.domain.Facility;
import za.ac.cput.domain.Incident;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.StatusType;
import za.ac.cput.domain.Student;

import java.time.LocalDate;

public class IncidentFactory {

    public static Incident createIncident(
            Student student,
            Staff reportedByStaff,
            Facility facility,
            LocalDate incidentDate,
            String description,
            StatusType status) {

        // Validate mandatory fields
        if (student == null) {
            throw new IllegalArgumentException("Student is required");
        }
        if (reportedByStaff == null) {
            throw new IllegalArgumentException("Staff reporter is required");
        }
        if (facility == null) {
            throw new IllegalArgumentException("Facility is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        // Apply defaults if needed
        if (incidentDate == null) {
            incidentDate = LocalDate.now();
        }

        return new Incident.Builder()
                .setStudent(student)
                .setReportedByStaff(reportedByStaff)
                .setFacility(facility)
                .setIncidentDate(incidentDate)
                .setDescription(description)
                .setStatus(status)
                .build();
    }
}
