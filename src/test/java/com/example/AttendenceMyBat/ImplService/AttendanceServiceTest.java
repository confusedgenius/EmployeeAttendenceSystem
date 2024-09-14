package com.example.AttendenceMyBat.ImplService;
import com.example.AttendenceMyBat.mapper.AttendanceMapper;
import com.example.AttendenceMyBat.model.Attendance;
import com.example.AttendenceMyBat.model.User;
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
        int empId = 1;
        when(attendanceMapper.findEmployeeByEmpId(empId)).thenReturn(Collections.singletonList(new User()));
        attendanceService.checkIn(empId);
        verify(attendanceMapper).CheckIn(argThat(attendance ->
                attendance.getEmp_id() == empId &&
                        attendance.getCheck_in_time() != null &&
                        attendance.getCheck_in_date().equals(LocalDate.now())
        ));
    }

    @Test
    void testCheckOut() throws Exception {
        int empId = 1;

        when(attendanceMapper.findEmployeeByEmpId(empId)).thenReturn(Collections.singletonList(new User()));

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
        List<User> users = Collections.singletonList(new User());
        when(attendanceMapper.findAll(pageSize, (pageNum - 1) * pageSize)).thenReturn(users);
        List<User> result = attendanceService.ShowAttendance(pageNum, pageSize);
        assertEquals(users.size(), result.size());
        assertEquals(users, result);
    }

    @Test
    void testPresentEmployee() {
        List<User> users = Collections.singletonList(new User());
        when(attendanceMapper.presentEmployee(LocalDate.now())).thenReturn(users);

        List<User> result = attendanceService.presentEmployee();

        assertEquals(users.size(), result.size());
        assertEquals(users, result);
    }

    @Test
    void testAbsentEmployee() {
        List<User> users = Collections.singletonList(new User());
        when(attendanceMapper.findAbsentEmployee(LocalDate.now())).thenReturn(users);

        List<User> result = attendanceService.absentEmployee();

        assertEquals(users.size(), result.size());
        assertEquals(users, result);
    }

    @Test
    void testFindEmployeeByEmpId() {
        int empId = 1;
        List<User> users = Collections.singletonList(new User());
        when(attendanceMapper.findEmployeeByEmpId(empId)).thenReturn(users);

        List<User> result = attendanceService.findEmployeeByEmpId(empId);

        assertEquals(users.size(), result.size());
        assertEquals(users, result);
    }
}
