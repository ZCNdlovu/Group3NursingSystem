package za.ac.cput.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.RoleType;
import za.ac.cput.domain.Staff;
import za.ac.cput.repository.StaffRepository;
import za.ac.cput.service.IStaffService;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements IStaffService {

    private final StaffRepository repository;

    @Autowired
    public StaffServiceImpl(StaffRepository repository) {
        this.repository = repository;
    }

    @Override
    public Staff create(Staff staff) {
        staff.setRole(RoleType.STAFF);
        return repository.save(staff);
    }

    @Override
    public Staff read(String staffId) {
        return repository.findById(staffId).orElse(null);
    }

    @Override
    public Staff update(Staff staff) {
        if (repository.existsById(staff.getStaffId())) {
            return repository.save(staff);
        }
        return null;
    }

    @Override
    public List<Staff> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String staffId) {
        repository.deleteById(staffId);
    }

    @Override
    public Optional<Staff> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Staff> findByStaffNumber(String staffNumber) {
        return repository.findByStaffNumber(staffNumber);
    }

    @Override
    public List<Staff> findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }
}
