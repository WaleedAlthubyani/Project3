package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.EmployeeDTO;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<MyUser>>> getAllEmployee(){
        return ResponseEntity.status(200).body(new ApiResponse<>(employeeService.getAllEmployees()));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> Register(@RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.Register(employeeDTO);
        return ResponseEntity.status(201).body(new ApiResponse<>("Employee registered successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> update(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid EmployeeDTO employeeD){
        employeeService.update(myUser.getId(),employeeD);
        return ResponseEntity.status(200).body(new ApiResponse<>("Employee updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> delete(@AuthenticationPrincipal MyUser myUser){
        employeeService.delete(myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse<>("Employee deleted successfully"));
    }
}
