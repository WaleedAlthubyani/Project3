package com.example.bankmanagementsystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;
    @Column(columnDefinition = "varchar(255) not null")
    private String password;
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @Column(columnDefinition = "varchar(8) not null")
    private String role;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
