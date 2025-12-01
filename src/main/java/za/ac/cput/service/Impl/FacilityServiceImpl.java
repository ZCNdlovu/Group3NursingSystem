package za.ac.cput.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Facility;
import za.ac.cput.repository.FacilityRepository;
import za.ac.cput.service.IFacilityService;

import java.util.List;

@Service
public class FacilityServiceImpl implements IFacilityService {
    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Facility create(Facility facility) {return facilityRepository.save(facility);}

    @Override
    public Facility read(Integer facilityId) {return facilityRepository.findById(facilityId).orElse(null);}

    @Override
    public Facility update(Facility facility) {return facilityRepository.save(facility);}

    @Override
    public List<Facility> getAll() {return facilityRepository.findAll();}

    @Override
    // 💡 FIXED: Delete method now returns a boolean and uses the ID correctly
    public boolean delete(Integer id) {
        // 1. Check if the entity exists
        if (facilityRepository.existsById(id)) {
            try {
                // 2. Perform the deletion using deleteById(ID)
                facilityRepository.deleteById(id);

                // 3. Verify the deletion (check if it no longer exists)
                return !facilityRepository.existsById(id);
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

