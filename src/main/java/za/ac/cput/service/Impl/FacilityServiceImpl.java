package za.ac.cput.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Facility;
import za.ac.cput.repository.FacilityRepository;
import za.ac.cput.service.IFacilityService;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityServiceImpl implements IFacilityService {
    private final FacilityRepository facilityRepository;

@Autowired
    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Facility create(Facility facility) { return facilityRepository.save(facility); }

    @Override
    public Facility read(Integer facilityId) { return facilityRepository.findById(facilityId).orElse(null); }

    @Override
    public Facility update(Facility facility) {return facilityRepository.save(facility); }

    @Override
    public List<Facility> getAll() {return facilityRepository.findAll(); }

    @Override
    public Optional<Facility> findByName(String name) {return facilityRepository.findByName(name);}

    @Override
    public List<Facility> findByNameAndAddress(String name, String address) {
    return facilityRepository.findByNameAndAddress(name, address);}
    }

