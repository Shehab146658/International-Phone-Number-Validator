package com.jumia.PhoneNumberValidator.Services;

import com.jumia.PhoneNumberValidator.DTOs.CustomerDTO;
import com.jumia.PhoneNumberValidator.Entities.Customer;
import com.jumia.PhoneNumberValidator.Models.CountriesENUM;
import com.jumia.PhoneNumberValidator.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customersList = customerRepository.getAllCustomers();
        List<CustomerDTO> customersDTOsList= new ArrayList<CustomerDTO>();
        String phoneNumber;
        for( Customer customer: customersList) {
            if(!validateNum(customer.getPhone())){
                continue;
            }
            phoneNumber=customer.getPhone().replaceAll("\\p{P}","");

            if(isNumberValid(phoneNumber)) {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setName(customer.getName());
                customerDTO.setId(customer.getId());
                customerDTO.setPhone(customer.getPhone().substring(5));
                customerDTO.setCountryCode(customer.getPhone().substring(1, 4));
                customerDTO.setCountry(this.getCountryByPhoneNumber(customer.getPhone()));
                customerDTO.setState("Not Valid");
                customersDTOsList.add(customerDTO);
            }
        }
        return  customersDTOsList;
    }

    public boolean validateNum(String phoneNumber)
    {
        if(Pattern.matches("\\(237\\)\\ ?[2368]\\d{9,10}$", phoneNumber)){
            return false;
        }
        else if(Pattern.matches("\\(251\\)\\ ?[1-59]\\d{9,10}$", phoneNumber)) {
            return false;
        }
        else if(Pattern.matches("\\(212\\)\\ ?[5-9]\\d{9}$", phoneNumber)) {
            return false;
        }
        else if(Pattern.matches("\\(258\\)\\ ?[28]\\d{9,10}$", phoneNumber)) {
            return false;
        }
        else if(Pattern.matches("\\(258\\)\\ ?[0]\\d{7,8}$", phoneNumber)) {
            return false;
        }
        else if(Pattern.matches("\\(256\\)\\ ?\\d{10}$", phoneNumber)) {
            return false;
        }
        return true;
    }

    public boolean isNumberValid(String strNum) {
        BigInteger intValue;
        if (strNum == null) {
            return false;
        }
        try {
            strNum=strNum.replaceAll("\\s+","");
            intValue = new BigInteger(strNum);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
            return false;
        }
    }
    private String getCountryByPhoneNumber(String phoneNumber){
        String country="";
        String result;
        if(Pattern.matches("\\(237\\)\\ ?[2368]\\d{7,8}$", phoneNumber)){
            country= CountriesENUM.CAMEROON.getCountryName();
        }
        else if(Pattern.matches("\\(251\\)\\ ?[1-59]\\d{8}$", phoneNumber)) {
            country= CountriesENUM.ETHIOPIA.getCountryName();
        }
        else if(Pattern.matches("\\(212\\)\\ ?[5-9]\\d{8}$", phoneNumber)) {
            country= CountriesENUM.MOROCCO.getCountryName();
        }
        else if(Pattern.matches("\\(258\\)\\ ?[28]\\d{7,8}$", phoneNumber)) {
            country= CountriesENUM.MOZAMBIQUE.getCountryName();
        }
        else if(Pattern.matches("\\(256\\)\\ ?\\d{9}$", phoneNumber)) {
            country= CountriesENUM.UGANDA.getCountryName();
        }
        switch (country) {
            case "Cameroon":
                result = "Cameroon";
                break;
            case "Ethiopia":
                result = "Ethiopia";
                break;
            case "Mozambique":
                result = "Mozambique";
                break;
            case "Uganda":
                result = "Uganda";
                break;
            case "Morocco":
                result = "Morocco";
                break;
            default:
                result = "Not Valid Code";
                break;
        }
        return result;
    }
}
