package com.bakeng.crudapp.service;

import com.bakeng.crudapp.model.Employee;
import com.bakeng.crudapp.repo.EmployeeRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee createEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public List<Employee> createEmployees(List<Employee> employees){
        return employeeRepo.saveAll(employees);
    }

    public Employee getEmployeeById(long id){
        return employeeRepo.findById(id).orElse(null);
    }

    public List<Employee> getEmployees(){
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Long id, Employee employee){
        Optional<Employee> oldEmployee=employeeRepo.findById(id);
        Employee oldEmp;
        if(oldEmployee.isPresent()){
            oldEmp =oldEmployee.get();
            oldEmp.setEmail(employee.getEmail());
            oldEmp.setFullName(employee.getFullName());
            oldEmp.setJobTitle(employee.getJobTitle());
            oldEmp.setPassword(employee.getPassword());
            employeeRepo.save(oldEmp);
        }else{
            return null;
        }
        return oldEmp;
    }

    public HttpStatus deleteEmployeeById(long id){
        if(employeeRepo.findById(id).isPresent()){
            employeeRepo.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

}
