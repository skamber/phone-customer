package com.belong.phone.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belong.phone.customer.entity.Phone;
import com.belong.phone.customer.exception.NoDataFoundException;
import com.belong.phone.customer.repository.PhoneRepository;

@Service
public class CustomerPhoneService {

    private PhoneRepository phoneRepository;

    @Autowired
    public CustomerPhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    // get All phones
    public List<Phone> getAllPhone() {
        return phoneRepository.findAll();
    }

    // get Phones By Customer Id
    public List<Phone> getPhonesByCustomerId(Integer customerId) {
        List<Phone> customerPhones = phoneRepository.findByCustomerId(customerId);
        if (customerPhones.isEmpty()) {
            throw new NoDataFoundException("Not found phones with customerId= " + customerId);
        } else
            return customerPhones;
    }

    // activate existing phone.
    public Phone activatePhoneNumber(Long phoneId) {
        Optional<Phone> optionalPhone = phoneRepository.findById(phoneId);
        if (!optionalPhone.isPresent()) {
            throw new NoDataFoundException("Not phone found = " + phoneId);
        }
        Phone phone = optionalPhone.get();
        phone.setActive(true);
        phoneRepository.save(phone);
        return phone;
    }
}
