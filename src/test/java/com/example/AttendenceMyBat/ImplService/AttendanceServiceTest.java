package com.example.AttendenceMyBat.ImplService;
import com.example.AttendenceMyBat.mapper.AttendanceMapper;
import com.example.AttendenceMyBat.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


public class AttendanceServiceTest {

    @Mock
    private AttendanceMapper attendanceMapper;

    @InjectMocks
    private AttendanceService attendanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckIn() throws Exception {
        int emp_id = 1;
        when(attendanceMapper.findEmployeeByEmpId(emp_id)).thenReturn(Collections.singletonList(new Employee()));
        attendanceService.checkIn(emp_id);
        verify(attendanceMapper).CheckIn(argThat(attendance ->
                attendance.getEmp_id() == emp_id &&
                        attendance.getCheck_in_time() != null &&
                        attendance.getCheck_in_date().equals(LocalDate.now())
        ));
    }

    @Test
    void testCheckOut() throws Exception {
        int empId = 1;

        when(attendanceMapper.findEmployeeByEmpId(empId)).thenReturn(Collections.singletonList(new Employee()));

        attendanceService.checkOut(empId);

        verify(attendanceMapper).checkout(argThat(attendance ->
                attendance.getEmp_id() == empId &&
                        attendance.getCheck_out_time() != null
        ));
    }


    @Test
    void testShowAttendance() {
        int pageNum = 1;
        int pageSize = 10;
        List<Employee> employees = Collections.singletonList(new Employee());
        when(attendanceMapper.findAll(pageSize, 0)).thenReturn(employees);
        List<Employee> result = attendanceService.ShowAttendance(pageNum, pageSize);
        assertEquals(employees.size(), result.size());
        assertEquals(employees, result);
    }

    @Test
    void testPresentEmployee() {
        List<Employee> employees = Collections.singletonList(new Employee());
        when(attendanceMapper.presentEmployee(LocalDate.now())).thenReturn(employees);

        List<Employee> result = attendanceService.presentEmployee();

        assertEquals(employees.size(), result.size());
        assertEquals(employees, result);
    }

    @Test
    void testAbsentEmployee() {
        List<Employee> employees = Collections.singletonList(new Employee());
        when(attendanceMapper.findAbsentEmployee(LocalDate.now())).thenReturn(employees);

        List<Employee> result = attendanceService.absentEmployee();

        assertEquals(employees.size(), result.size());
        assertEquals(employees, result);
    }

    @Test
    void testFindEmployeeByEmpId() {
        int empId = 1;
        List<Employee> employees = Collections.singletonList(new Employee());
        when(attendanceMapper.findEmployeeByEmpId(empId)).thenReturn(employees);

        List<Employee> result = attendanceService.findEmployeeByEmpId(empId);

        assertEquals(employees.size(), result.size());
        assertEquals(employees, result);
    }
}
