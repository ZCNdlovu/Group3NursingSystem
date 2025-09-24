package za.ac.cput.service;

import za.ac.cput.domain.Placement;

import java.util.List;

public interface IPlacementService extends IService<Placement, Integer>{
    List<Placement>getAll();
}
