package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Staff;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

    Optional<Staff> findByEmail(String email);
    Optional<Staff> findByStaffNumber(String staffNumber);
    List<Staff> findByEmailAndPassword(String email, String password);
}
