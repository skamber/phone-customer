package com.belong.phone.customer.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.belong.phone.customer.AppTestConfig;
import com.belong.phone.customer.entity.Phone;
import com.belong.phone.customer.repository.PhoneRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerPhoneServiceTest {
    
    CustomerPhoneService customerPhoneService;
    
    @Mock
    PhoneRepository phoneRepository;
    
    @BeforeEach
    void init() {
        customerPhoneService = new CustomerPhoneService(phoneRepository);
    }
    
    @Test
    public void getPhonesByCustomerIdTest(){
        
        Mockito.when(phoneRepository.findByCustomerId(1))
        .thenReturn(Arrays.asList(AppTestConfig.getPhone1()));
        List<Phone> result = customerPhoneService.getPhonesByCustomerId(1);
        assertTrue(result.equals(Arrays.asList(AppTestConfig.getPhone1())));
    }
    
    
    //etc... unit testing
}
