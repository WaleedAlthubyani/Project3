package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=authRepository.findMyUserByUsername(username);
        if (myUser==null) throw new ApiException("username or password is incorrect");
        return myUser;
    }


}
