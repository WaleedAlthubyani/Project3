package com.example.bankmanagementsystem.Config;

import com.example.bankmanagementsystem.Service.MyUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final MyUserDetailService myUserDetailService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/employee/register","/api/v1/customer/register").permitAll()
                .requestMatchers("/api/v1/employee/update","/api/v1/employee/delete","/api/v1/account/activate/").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/customer/update","/api/v1/customer/delete","/api/v1/account/add","/api/v1/account/update/","/api/v1/account/delete/","/api/v1/account/view-account-details/","/api/v1/account/get-my-accounts","/api/v1/account/deposit/","/api/v1/account/withdraw/","/api/v1/account/transfer/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/employee/get-all","/api/v1/customer/get-all","/api/v1/account/activate/","/api/v1/account/block-bank-account/").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return http.build();
    }
}
