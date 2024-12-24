package com.example.bankmanagementsystem.Repository;

import com.example.bankmanagementsystem.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<MyUser,Integer> {

    MyUser findMyUserByUsername(String username);

    MyUser findMyUserById(Integer id);

    List<MyUser> findMyUsersByRole(String role);
}

