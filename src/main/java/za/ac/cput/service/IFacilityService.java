package za.ac.cput.service;

import za.ac.cput.domain.Facility;

import java.util.List;

public interface IFacilityService extends IService<Facility, Integer>{
    List<Facility> getAll();
}
