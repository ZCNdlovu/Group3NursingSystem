package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Facility;

import java.util.List;
import java.util.Optional;



public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    Optional<Facility>findByName(String name);
    List<Facility>findByNameAndAddress(String name, String address);

}
