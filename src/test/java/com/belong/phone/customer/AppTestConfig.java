package com.belong.phone.customer;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.belong.phone.customer.entity.Customer;
import com.belong.phone.customer.entity.Phone;
import com.belong.phone.customer.repository.PhoneRepository;

@TestConfiguration
public class AppTestConfig {

    @Bean
    public PhoneRepository phoneRepository() {
        return Mockito.mock(PhoneRepository.class);
    }

    public static List<Phone> getAllPhones() {
        List<Phone> list = new ArrayList<Phone>();
        list.add(getPhone1());
        list.add(getPhone2());
        return list;
    }

    public static Phone getPhone1() {
        Phone phone1 = new Phone();
        phone1.setActive(false);
        phone1.setId(1234567890L);
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("Sami");
        customer1.setEmail("sami@gmail.com");
        phone1.setCustomer(customer1);
        return phone1;
    }

    public static Phone activatePhone(Phone phone) {
        phone.setActive(true);
        return phone;
    }

    public static Phone getPhone2() {
        Phone phone2 = new Phone();
        phone2.setActive(false);
        phone2.setId(1234567891L);
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Sohpie");
        customer2.setEmail("Sohpie@gmail.com");
        phone2.setCustomer(customer2);
        return phone2;
    }
}