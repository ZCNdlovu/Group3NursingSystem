package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.ac.cput.domain.Placement;


public interface PlacementRepository extends JpaRepository<Placement, Integer> {
}
