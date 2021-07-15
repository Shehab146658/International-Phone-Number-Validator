package com.jumia.PhoneNumberValidator.Repository;

import com.jumia.PhoneNumberValidator.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT customer FROM Customer customer")
    List<Customer> getAllCustomers();
}
