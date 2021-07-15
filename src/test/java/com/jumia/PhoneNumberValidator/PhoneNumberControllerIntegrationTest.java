package com.jumia.PhoneNumberValidator;

import com.jumia.PhoneNumberValidator.Controllers.PhoneNumberController;
import com.jumia.PhoneNumberValidator.DTOs.CustomerDTO;
import com.jumia.PhoneNumberValidator.Entities.Customer;
import com.jumia.PhoneNumberValidator.Repository.CustomerRepository;
import com.jumia.PhoneNumberValidator.Services.CustomerService;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.nio.cs.Surrogate.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomerService.class)
public class PhoneNumberControllerIntegrationTest {



    @MockBean
    private CustomerService service;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void assertCustomersList() {
        //due to the tight time ( it's a quick example but it is not working )
        Assert.assertEquals(service.getAllCustomers().get(0).getName(), "Yosaf Karrouch");
    }
}
