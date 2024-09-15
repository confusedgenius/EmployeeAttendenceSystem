package com.example.AttendenceMyBat.RestController;

import com.example.AttendenceMyBat.ImplService.EmployeeService;
import com.example.AttendenceMyBat.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody List<Employee> employee) {
        employeeService.registerEmployee(employee);
        return ResponseEntity.ok("Registration Successful!!");
    }

    @GetMapping("/{emp_id}")
    public ResponseEntity<Employee> getUser(@PathVariable int emp_id) {
            return new ResponseEntity<>(employeeService.getEmpById(emp_id), HttpStatus.OK);
    }

    //feature added
    @DeleteMapping("/deactivate/{emp_id}")
    public ResponseEntity<String> deactivateEmp(@PathVariable("emp_id") int emp_id) {
        employeeService.removeEmployee(emp_id);
        return ResponseEntity.ok("Employee flag Disabled(Employee Deactived) !!");
    }

    @GetMapping("/allUsers")
    public List<Employee> showactive() {
        return employeeService.showActiveEmployee();
    }

}
