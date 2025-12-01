package za.ac.cput.service;

import za.ac.cput.domain.*;

import java.util.Optional;

public interface IAdminService extends IService<Admin, String> {
    void delete(String staffId);
    Optional<Admin> findByEmail(String email);
}
