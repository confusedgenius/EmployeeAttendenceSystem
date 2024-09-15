package com.example.AttendenceMyBat.RestController;

import com.example.AttendenceMyBat.ImplService.AttendanceService;
import com.example.AttendenceMyBat.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RestControllerAdvice
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Employee>> getEmployeeStatus(@PathVariable("status") String status) {
      List<Employee> employeeList;
        if ("present".equalsIgnoreCase(status))
      employeeList = attendanceService.presentEmployee();
        else if ("absent".equalsIgnoreCase(status))
            employeeList =attendanceService.absentEmployee();
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/absentEmployee")
    public ResponseEntity<List<Employee>> getAbsentEmployees() {
            return new ResponseEntity<>(attendanceService.absentEmployee(), HttpStatus.OK);
        }

    @PostMapping("/checkin/{emp_id}")
    public ResponseEntity<String> checkIn(@PathVariable("emp_id") int emp_id) throws Exception {
        attendanceService.checkIn(emp_id);
        return ResponseEntity.ok("Checked in successfully!");
    }

    @PostMapping("/checkout/{emp_id}")
    public ResponseEntity<String> checkOut(@PathVariable("emp_id") int emp_id) throws Exception {
        attendanceService.checkOut(emp_id);
        return ResponseEntity.ok("Checked out successfully!");
    }

    @GetMapping("/find/{emp_id}")
    public ResponseEntity<List<Employee>> findEmployeeAttendance(@PathVariable("emp_id") int emp_id) {
            return new ResponseEntity<>(attendanceService.findEmployeeByEmpId(emp_id), HttpStatus.OK);
    }

//    @GetMapping("/show")
//    public ResponseEntity<List<User>> showAllAttendance(
//            @RequestParam(defaultValue = "1") int pageNumber,
//            @RequestParam(defaultValue = "1") int pageSize) {
//        final List<User> attendanceList = attendanceService.ShowAttendance(pageNumber,pageSize);
//        //caching bhi implement krna ispe.AOP bhi dekhna
////        if (!attendanceList.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(attendanceList, HttpStatus.OK);
//    }
@GetMapping("/show")
@Cacheable(value = "attendanceCache", key = "#pageNumber + '-' + #pageSize")
public ResponseEntity<List<Employee>> showAllAttendance(
        @RequestParam(defaultValue = "1") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize) {

    List<Employee> attendanceList = attendanceService.ShowAttendance(pageNumber, pageSize);
    return new ResponseEntity<>(attendanceList, HttpStatus.OK);
}

}