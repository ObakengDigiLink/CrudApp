package com.bakeng.crudapp.controller;

import com.bakeng.crudapp.model.Employee;
import com.bakeng.crudapp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);
    }
    @PostMapping("/addEmployees")
    public ResponseEntity<List<Employee>> addEmployees(@RequestBody List<Employee> employeeList){
        return new ResponseEntity<>(employeeService.createEmployees(employeeList), HttpStatus.OK);
    }
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        if (employeeService.getEmployeeById(id) != null){
            return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getEmployees")
    public ResponseEntity<List<Employee>> getAllEmplyees(){
        if(employeeService.getEmployees().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
        }
    }
    @PostMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable long id, @RequestBody Employee employee){
        if(employeeService.updateEmployee(id, employee) != null){
            return new ResponseEntity<>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<HttpStatus> deleteEmpyId(@PathVariable long id){
       return new ResponseEntity<>(employeeService.deleteEmployeeById(id));
    }
}
