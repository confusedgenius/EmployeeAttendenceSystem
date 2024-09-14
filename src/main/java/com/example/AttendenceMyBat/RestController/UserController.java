package com.example.AttendenceMyBat.RestController;

import com.example.AttendenceMyBat.ImplService.UserService;
import com.example.AttendenceMyBat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        User user1 = userService.getEmpById(user.getEmp_id());

        if (user1 != null) return ResponseEntity.ok("Check your EmployeeId !! (Duplicates not allowed)");
        //17-23 in service level

        userService.registerUser(user);
        return ResponseEntity.ok("Registration Successful!!");
    }

    @GetMapping("/{emp_id}")
    public ResponseEntity<User> getUser(@PathVariable int emp_id) {
            return new ResponseEntity<>(userService.getEmpById(emp_id), HttpStatus.OK);
    }

    //feature added
    @DeleteMapping("/deactivate/{emp_id}")
    public ResponseEntity<String> deactivateEmp(@PathVariable("emp_id") int emp_id) {
        userService.removeEmployee(emp_id);
        return ResponseEntity.ok("Employee flag Disabled(Employee Deactived) !!");
    }

    @GetMapping("/allUsers")
    public List<User> showactive() {

        //page initiation support
        return userService.showActiveEmployee();
    }

}
