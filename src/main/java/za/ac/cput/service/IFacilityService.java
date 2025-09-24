package za.ac.cput.service;

import za.ac.cput.domain.Facility;

import java.util.List;
import java.util.Optional;


public interface IFacilityService extends IService<Facility, Integer>{
   Optional<Facility> findByName(String name);
   List<Facility>findByNameAndAddress(String name, String address);

}
