package com.marktek.service;

import com.marktek.entity.Customers;
import com.marktek.model.RegistrationForm;

import java.util.List;

public interface UserService {

    Customers save(Customers user);
    List<Customers> findAll();
    void delete(long id);

    Boolean sendOtp(RegistrationForm user);
}
