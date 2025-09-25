package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {
}
