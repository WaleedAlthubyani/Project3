package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(19) not null unique")
    private String accountNumber;
    @Column(columnDefinition = "double not null")
    private Double balance;
    @Column(columnDefinition = "boolean not null")
    private Boolean isActive=false;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
