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
    }

