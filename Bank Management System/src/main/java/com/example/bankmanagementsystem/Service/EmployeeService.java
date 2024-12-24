package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.EmployeeDTO;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public void Register(EmployeeDTO employeeDTO){
        MyUser myUser=new MyUser();
        ConvertEmployeeDTOtoMyUser(employeeDTO, myUser);

        Employee employee = new Employee();
        employee.setUser(myUser);
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employeeRepository.save(employee);

    }

    public List<MyUser> getAllEmployees(){
        return authRepository.findMyUsersByRole("EMPLOYEE");
    }

    public void update(Integer id,EmployeeDTO employeeDTO){
        MyUser myUser=authRepository.findMyUserById(id);
        Employee employee=employeeRepository.findEmployeeById(id);
        if (employee==null||myUser==null) throw new ApiException("Employee not found");
        ConvertEmployeeDTOtoMyUser(employeeDTO, myUser);
        employee.setUser(myUser);
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employeeRepository.save(employee);

    }

    public void delete(Integer id){
        Employee employee=employeeRepository.findEmployeeById(id);
        if (employee==null) throw new ApiException("Employee not found");
        MyUser myUser=authRepository.findMyUserById(employee.getUser().getId());
        employee.setUser(null);
        myUser.setEmployee(null);
        employeeRepository.delete(employee);
        authRepository.delete(myUser);
    }

    private void ConvertEmployeeDTOtoMyUser(EmployeeDTO employeeDTO, MyUser myUser) {
        myUser.setUsername(employeeDTO.getUsername());
        String hash=new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        myUser.setPassword(hash);
        myUser.setEmail(employeeDTO.getEmail());
        myUser.setName(employeeDTO.getName());
        myUser.setRole("EMPLOYEE");

        authRepository.save(myUser);
    }
}
