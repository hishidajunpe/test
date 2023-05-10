package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @Column(length = 80)
    private String username;
    
    private Boolean active;
    
    
    @OneToOne
    private User user;
}
