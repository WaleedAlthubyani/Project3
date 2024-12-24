package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.CustomerDTO;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public void Register(CustomerDTO customerDTO) {
        MyUser myUser=new MyUser();
        ConvertCustomerDTOtoMyUserAndCustomer(customerDTO, myUser);
        Customer customer=new Customer(null, customerDTO.getPhoneNumber(), myUser,null);
        authRepository.save(myUser);
        customerRepository.save(customer);
    }

    public List<MyUser> getAllCustomers(){
        return authRepository.findMyUsersByRole("CUSTOMER");
    }

    public void update(Integer id,CustomerDTO customerDTO){
        Customer customer=customerRepository.findCustomerById(id);
        MyUser myUser=authRepository.findMyUserById(id);
        if (customer==null||myUser==null) throw new ApiException("Customer not found");

        ConvertCustomerDTOtoMyUserAndCustomer(customerDTO, myUser);
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        authRepository.save(myUser);
        customerRepository.save(customer);

    }

    public void delete(Integer id){
        Customer customer=customerRepository.findCustomerById(id);
        MyUser myUser=authRepository.findMyUserById(id);
        if (customer==null||myUser==null) throw new ApiException("Customer not found");

        customer.setUser(null);
        myUser.setCustomer(null);

        customerRepository.delete(customer);
        authRepository.delete(myUser);
    }

    private void ConvertCustomerDTOtoMyUserAndCustomer(CustomerDTO customerDTO, MyUser myUser) {
        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        myUser.setUsername(customerDTO.getUsername());
        myUser.setName(customerDTO.getName());
        myUser.setRole("CUSTOMER");
        myUser.setPassword(hash);
        myUser.setEmail(customerDTO.getEmail());
    }

}
