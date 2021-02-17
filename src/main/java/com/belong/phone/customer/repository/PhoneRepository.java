package com.belong.phone.customer.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.belong.phone.customer.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long>{
    
    List<Phone> findByCustomerId(Integer customerId );
}
