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
public class Employee {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    private String position;
    @Column(columnDefinition = "double not null")
    private Double salary;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @MapsId
    @JsonIgnore
    private MyUser user;
}
