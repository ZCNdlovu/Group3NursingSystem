package za.ac.cput.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.RoleType;
import za.ac.cput.domain.Staff;
import za.ac.cput.repository.StaffRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import za.ac.cput.service.IStaffService;
import za.ac.cput.util.IdGenerator;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements IStaffService {

    private final StaffRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final IdGenerator idGenerator;

    @Autowired
    public StaffServiceImpl(StaffRepository repository, PasswordEncoder passwordEncoder,  IdGenerator idGenerator) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.idGenerator = idGenerator;
    }

    @Override
    public Staff create(Staff staff) {
        if (staff.getStaffId() == null || staff.getStaffId().isEmpty()) {
            // 2. Generate the custom ID
            String newId = idGenerator.generateNextId("SF");
            staff  = new Staff.Builder().copy(staff).setStaffId(newId).build();
        }
        staff.setRole(RoleType.STAFF);
        staff.setPassword(passwordEncoder.encode(staff.getPassword())); // Encode password
        return repository.save(staff);
    }

    @Override
    public Staff read(String staffId) {
        return repository.findById(staffId).orElse(null);
    }

    @Override
    public Staff update(Staff staff) {
        if (staff.getStaffId()== null) {
            throw new IllegalArgumentException("Cannot update staff without an ID");
        }
        if (repository.existsById(staff.getStaffId())) {
            return repository.save(staff); // ✅ this is UPDATE
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


}
