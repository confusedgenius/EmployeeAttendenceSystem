package com.example.AttendenceMyBat.RestController;

import com.example.AttendenceMyBat.ImplService.AttendanceService;
import com.example.AttendenceMyBat.model.Attendance;
import com.example.AttendenceMyBat.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RestControllerAdvice
@RequestMapping("/api/attendance")
public class AttendanceController {
    private static Logger logger = LoggerFactory.getLogger(AttendanceController.class);
    @Autowired
    private AttendanceService attendanceService;


    @GetMapping("/presentEmployee")
    public ResponseEntity<List<User>> pres() {
        //TODO: Add logging at controller response level.
        final List<User> userList = attendanceService.presentt();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    @GetMapping("/absentEmployee")
    public ResponseEntity<List<User>> absent() {
        //
        if (attendanceService.absent().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(attendanceService.absent(), HttpStatus.OK);
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
    public ResponseEntity<List<User>> finduser(@PathVariable("emp_id") int emp_id) {
        //
        if (!attendanceService.findbyempID(emp_id).isEmpty())
            return new ResponseEntity<>(attendanceService.findbyempID(emp_id), HttpStatus.OK);
        logger.error("Employee not Found !! Check your employeeId : {} ",emp_id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/show")
    public ResponseEntity<List<Attendance>> find() {
        //
        //caching bhi implement krna ispe.AOP bhi dekhna
        if (attendanceService.ShowAttendance().isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(attendanceService.ShowAttendance(), HttpStatus.OK);
    }



}