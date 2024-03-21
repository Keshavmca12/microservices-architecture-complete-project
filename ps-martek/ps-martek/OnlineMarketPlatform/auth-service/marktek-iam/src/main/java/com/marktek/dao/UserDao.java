package com.marktek.dao;

import com.marktek.entity.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<Customers, Long> {
    Customers findByMobileNumber(String mobileNo);
}
