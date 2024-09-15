package com.example.AttendenceMyBat.ImplService;
import com.example.AttendenceMyBat.mapper.AttendanceMapper;
import com.example.AttendenceMyBat.model.Attendance;
import com.example.AttendenceMyBat.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Service
@Slf4j
public class AttendanceService {
    @Autowired
    AttendanceMapper attendanceMapper;

    public void checkIn(int empId) throws Exception {
        checkValidEmployee(empId);
        Attendance attendance = new Attendance();
        attendance.setEmp_id(empId);
        attendance.setCheck_in_time(LocalTime.now());
        attendance.setCheck_in_date(LocalDate.now());
        attendanceMapper.CheckIn(attendance);
        log.info("Successfully checked in employee with ID: {}", empId);
    }
//    public List<User> ShowAttendance(int pageNum, int pageSize)
//    {
//
//        List<User> attendanceList = attendanceMapper.findAll(pageNum,pageSize);
//        log.info("Retrieved {} attendance records.", pageSize);
//        return attendanceList;
//        //TODO:add page support
//
//    }

    public List<Employee> ShowAttendance(int pageNum, int pageSize) {
        if (pageNum < 1)
            pageNum = 1;
        if (pageSize <= 0)
            pageSize = 10;
        int offset = (pageNum - 1) * pageSize;
        List<Employee> attendanceList = attendanceMapper.findAll(pageSize, offset);
        log.info("Retrieved {} attendance records for page {}.", attendanceList.size(), pageNum);
        return attendanceList;
    }

    public void checkValidEmployee(int empId) throws Exception {
        List<Employee> emp=attendanceMapper.findEmployeeByEmpId(empId);
        if (emp.isEmpty()) {
            log.error("Employee ID {} not found in employee_detail table.", empId);
            throw new Exception("Employee Id not found in employee table");
        }
        log.info("Employee ID {} found in employee_detail table.", empId);
    }

    public List<Employee> checkEmpty(List<Employee> employees) {
        if (!employees.isEmpty()) {
            log.info("Found {} employees.", employees.size());
            return employees;
        } else {
            log.error("Employee not found.");
            return null;
        }
    }
    public void checkOut(int empId) throws Exception {
        log.info("Attempting to check out employee with ID: {}", empId);
        checkValidEmployee(empId);
        Attendance attendance = new Attendance();
        attendance.setEmp_id(empId);
        attendance.setCheck_out_time(LocalTime.now());
        attendanceMapper.checkout(attendance);
        log.info("Successfully checked out employee with ID: {}", empId);
    }
    public List<Employee> presentEmployee() {
        log.info("Fetching employees present today.");
        List<Employee> presentEmployees = checkEmpty(attendanceMapper.presentEmployee(LocalDate.now()));
        log.info("Retrieved : {} employees are  present today.", presentEmployees.size());
        return presentEmployees;
    }
    public List<Employee> absentEmployee() {
        log.info("Fetching employees absent today.");
        List<Employee> absentEmployees = checkEmpty(attendanceMapper.findAbsentEmployee(LocalDate.now()));
        log.info("Retrieved : {} employees are absent today.",  absentEmployees.size());
        return absentEmployees;
    }
    public List<Employee> findEmployeeByEmpId(int empId) {
        log.info("Fetching employee with ID: {}", empId);
        List<Employee> employees = attendanceMapper.findEmployeeByEmpId(empId);
        if (employees.isEmpty()) {
            log.error("Employee with ID : {} not found.", empId);
        } else {
            log.info("Found {} employees ", employees.size());
        }
        return employees;
    }
}


