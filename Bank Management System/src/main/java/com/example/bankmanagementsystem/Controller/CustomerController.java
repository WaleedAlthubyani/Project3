package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.CustomerDTO;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<MyUser>>> getAllCustomer() {
        return ResponseEntity.status(200).body(new ApiResponse<>(customerService.getAllCustomers()));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> Register(@RequestBody @Valid CustomerDTO customerDTO) {
        customerService.Register(customerDTO);
        return ResponseEntity.status(201).body(new ApiResponse<>("Customer registered successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> update(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid CustomerDTO customerD) {
        customerService.update(myUser.getId(), customerD);
        return ResponseEntity.status(200).body(new ApiResponse<>("Customer updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> delete(@AuthenticationPrincipal MyUser myUser) {
        customerService.delete(myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse<>("Customer deleted successfully"));
    }
}
