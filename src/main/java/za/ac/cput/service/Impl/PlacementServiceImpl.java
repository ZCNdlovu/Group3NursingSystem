package za.ac.cput.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Placement;
import za.ac.cput.repository.PlacementRepository;
import za.ac.cput.service.IPlacementService;

import java.util.List;

@Service
public class PlacementServiceImpl implements IPlacementService {
    private final PlacementRepository placementRepository;

@Autowired
    public PlacementServiceImpl(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    @Override
    public Placement create(Placement placement) {return placementRepository.save(placement);}

    @Override
    public Placement read(Integer placementId) {return placementRepository.findById(placementId).orElse(null);}
    
    @Override
    public Placement update(Placement placement) {return placementRepository.save(placement);}
    
    @Override
    public List<Placement> getAll() {return placementRepository.findAll();}

    @Override
    // 💡 FIXED: Delete method now returns a boolean and uses the ID correctly
    public boolean delete(Integer id) {
        // 1. Check if the entity exists
        if (placementRepository.existsById(id)) {
            try {
                // 2. Perform the deletion using deleteById(ID)
                placementRepository.deleteById(id);

                // 3. Verify the deletion (check if it no longer exists)
                return !placementRepository.existsById(id);
            } catch (Exception e) {
                // Handle potential database/deletion errors
                System.err.println("Error deleting Placement with ID " + id + ": " + e.getMessage());
                return false;
            }
        }
        // Return false if the entity with the given ID was not found
        return false;
    }
}

