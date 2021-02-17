package com.belong.phone.customer.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer {
    
    @Id
    private Integer id;
    
    private String name;

    private String email;
}
