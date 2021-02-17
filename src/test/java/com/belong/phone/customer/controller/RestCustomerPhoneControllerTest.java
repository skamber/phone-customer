package com.belong.phone.customer.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.belong.phone.customer.AppTestConfig;
import com.belong.phone.customer.PhoneCustomerApplication;
import com.belong.phone.customer.repository.PhoneRepository;
// This is an integration testing
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true",
classes = {PhoneCustomerApplication.class, AppTestConfig.class })
@AutoConfigureMockMvc
public class RestCustomerPhoneControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PhoneRepository phoneRepository;
        
    @Test
    public void getAllPhoneIds() throws Exception {
       String url = "/getAllPhoneIds";
       
       String returningResult = "[{\"id\":1234567890,\"active\":false},{\"id\":1234567891,\"active\":false}]";
       when(phoneRepository.findAll()).thenReturn(AppTestConfig.getAllPhones());
       
       MvcResult result = mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk())
       .andExpect(content().contentType("application/json"))
       .andReturn();
       String contentAsString = result.getResponse().getContentAsString();
       assertTrue(contentAsString.equals(returningResult));
    }
    
    @Test
    public void getPhoneNumbersByCusomterId() throws Exception {
       String url = "/getPhoneNumbersByCusomterId/1";
       
       String returningResult = "[{\"id\":1234567890,\"active\":false}]";
       when(phoneRepository.findByCustomerId(1)).thenReturn(Arrays.asList(AppTestConfig.getPhone1()));
       
       MvcResult result = mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk())
       .andExpect(content().contentType("application/json"))
       .andReturn();
       String contentAsString = result.getResponse().getContentAsString();
       assertTrue(contentAsString.equals(returningResult));
    }
    
    @Test
    public void getPhoneNumbersByCusomterIdNotFound() throws Exception {
       String url = "/getPhoneNumbersByCusomterId/1";
       
       String returningResult = "{\"message\":\"Not found phones with customerId= 1\",\"details\":\"uri=/getPhoneNumbersByCusomterId/1\"}";
       when(phoneRepository.findByCustomerId(1)).thenReturn(Arrays.asList());
       
       MvcResult result = mockMvc.perform(get(url)).andDo(print()).andExpect(status().isNotFound())
       .andExpect(content().contentType("application/json"))
       .andReturn();
       String contentAsString = result.getResponse().getContentAsString();
       assertTrue(contentAsString.equals(returningResult));
    }
    
    @Test
    public void updatePhone() throws Exception {
       String url = "/activatePhoneNumber/1234567890";
       
       String returningResult = "{\"id\":1234567890,\"active\":true}";
       when(phoneRepository.findById(1234567890L)).thenReturn(Optional.of(AppTestConfig.getPhone1()));
       when(phoneRepository.save(AppTestConfig.getPhone1())).thenReturn(AppTestConfig.activatePhone(AppTestConfig.getPhone1()));
       
       MvcResult result = mockMvc.perform(put(url)).andDo(print()).andExpect(status().isOk())
       .andExpect(content().contentType("application/json"))
       .andReturn();
       String contentAsString = result.getResponse().getContentAsString();
       assertTrue(contentAsString.equals(returningResult));
    }
    
    @Test
    public void updatePhoneNotFound() throws Exception {
       String url = "/activatePhoneNumber/1234567890";
       
       String returningResult = "{\"message\":\"Not phone found = 1234567890\",\"details\":\"uri=/activatePhoneNumber/1234567890\"}";
       when(phoneRepository.findById(1234567890L)).thenReturn(Optional.empty());
       
       MvcResult result = mockMvc.perform(put(url)).andDo(print()).andExpect(status().isNotFound())
       .andExpect(content().contentType("application/json"))
       .andReturn();
       String contentAsString = result.getResponse().getContentAsString();
       assertTrue(contentAsString.equals(returningResult));
    }
    
}
