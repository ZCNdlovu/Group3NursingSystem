package za.ac.cput.service.Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Facility;
import za.ac.cput.domain.Incident;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.IncidentRepository;
import za.ac.cput.service.IIncidentService;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentServiceImpl implements IIncidentService {


    private final IncidentRepository repository;

    @Autowired
    public IncidentServiceImpl(IncidentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Incident create(Incident incident) {
        return repository.save(incident);
    }

    @Override
    public Incident read(String incidentId) {
        try {
            Integer id = Integer.parseInt(incidentId);
            return repository.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Incident update(Incident incident) {
        if (incident.getIncidentId() == null) {
            throw new IllegalArgumentException("Cannot update incident without an ID");
        }
        if (repository.existsById(incident.getIncidentId())) {
            return repository.save(incident);
        }
        return null;
    }

    @Override
    public List<Incident> getAll() {
        return repository.findAll();
    }



    @Override
    public Optional<Incident> findByStudent(Student student) {
        List<Incident> incidents = repository.findByStudent(student);
        if (incidents != null && !incidents.isEmpty()) {
            return Optional.of(incidents.get(0));
        }
        return Optional.empty();
    }

    @Override
    public List<Incident> findByReportedByStaff(Staff staff) {
        return repository.findByReportedByStaff(staff);
    }

    @Override
    public List<Incident> findByFacility(Facility facility) {
        return repository.findByFacility(facility);
    }
}//
