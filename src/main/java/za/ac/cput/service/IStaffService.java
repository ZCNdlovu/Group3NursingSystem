package za.ac.cput.service;

import za.ac.cput.domain.*;

import java.util.List;
import java.util.Optional;

public interface IStaffService extends IService<Staff, String> {
    void delete(String staffId);

    Optional<Staff> findByEmail(String email);

    Optional<Staff> findByStaffNumber(String staffNumber);

    //    void delete(String staffId);
//    Optional<Staff> findByEmail(String email);
//    Optional<Staff> findByStaffNumber(String staffNumber);
    List<Staff> findByEmailAndPassword(String email, String password);

}
