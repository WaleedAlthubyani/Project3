package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @MapsId
    @JsonIgnore
    private MyUser user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private Set<Account> accounts;

}
