package com.jumia.PhoneNumberValidator.Controllers;

import com.jumia.PhoneNumberValidator.DTOs.CustomerDTO;
import com.jumia.PhoneNumberValidator.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/numbers")
public class PhoneNumberController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/get-all-customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

}
