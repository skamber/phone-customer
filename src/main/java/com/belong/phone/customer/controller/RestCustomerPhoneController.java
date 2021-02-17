package com.belong.phone.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.belong.phone.customer.entity.Phone;
import com.belong.phone.customer.service.CustomerPhoneService;


@RestController
public class RestCustomerPhoneController {
    
    private CustomerPhoneService customerPhoneService;

    @Autowired
    public RestCustomerPhoneController(CustomerPhoneService customerPhoneService) {
        this.customerPhoneService = customerPhoneService;
    }
    
    @RequestMapping(value = "/getAllPhoneIds", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity <List<?>> getAllPhoneIds() {
        return new ResponseEntity <List<?>>(customerPhoneService.getAllPhone(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getPhoneNumbersByCusomterId/{customerId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<?>> getPhoneNumbersByCusomterId(@PathVariable("customerId") Integer customerId) {
        return new ResponseEntity<List<?>>(customerPhoneService.getPhonesByCustomerId(customerId), HttpStatus.OK);
    }
    
    @PutMapping("/activatePhoneNumber/{phoneNumber}")
    public ResponseEntity<Phone> updatePhone(@PathVariable (value = "phoneNumber") Long phoneNumber) {
        return new ResponseEntity<Phone>(customerPhoneService.activatePhoneNumber(phoneNumber), HttpStatus.OK); 
    }
}
