package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Admin;
import java.util.Optional;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    void delete(String adminId);
    Optional<Admin> findByEmail(String email);
    List<Admin> findByEmailAndPassword(String email, String password);
}
