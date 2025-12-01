package za.ac.cput.service;

import za.ac.cput.domain.*;

import java.util.Optional;

public interface IStaffService extends IService<Staff, String> {
    void delete(String staffId);

    Optional<Staff> findByEmail(String email); // Should return Optional<Staff>
    Optional<Staff> findByStaffNumber(String staffNumber);
    //    void delete(String staffId);
//    Staff findByEmail(String email);
//    Optional<Staff> findByStaffNumber(String staffNumber);


}
