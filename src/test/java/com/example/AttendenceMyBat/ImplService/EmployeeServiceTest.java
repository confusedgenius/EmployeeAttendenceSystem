package com.example.AttendenceMyBat.ImplService;
import com.example.AttendenceMyBat.mapper.EmployeeMapper;
import com.example.AttendenceMyBat.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test_RegisterEmployeeSuccessfully() {
        Employee employee = new Employee(1, "Sudhanshu", "sudhanshu@example.com", "Male", Date.valueOf("2024-09-15"), "Engineering", "123-456-7890", "Software Engineer", BigDecimal.valueOf(75000), "SDE", true, new Timestamp(System.currentTimeMillis()));
        doNothing().when(employeeMapper).registerEmployee(employee);
        employeeService.registerEmployee(employee);
        verify(employeeMapper, times(1)).registerEmployee(employee);
    }

    @Test
    void test_registerUser_Failure() {
        Employee employee = new Employee(9, "Sudhanshu", "sudhanshu11@gmail.com", "Male", Date.valueOf("2024-09-15"), "HR", "123-456-7890", "HR Manager", BigDecimal.valueOf(60000), "HR", true, new Timestamp(System.currentTimeMillis()));
        doThrow(new RuntimeException("Database error")).when(employeeMapper).registerEmployee(employee);
        assertThrows(RuntimeException.class, () -> employeeService.registerEmployee(employee));

        verify(employeeMapper, times(1)).registerEmployee(employee);
    }

//    @Test
//    void test_getUser_By_empId() {
//        Employee employee = new Employee(3, "abc", "abc@gmail.com", "SDE");
//        when(userMapper.findById(3)).thenReturn(employee);
//        Employee result = userService.getEmpById(3);
//        Assertions.assertEquals(3, result.getEmp_id());
//        verify(userMapper, times(1)).findById(3);
//    }

    @Test
    void test_getUserById_NotFound() {
        when(employeeMapper.findById(4)).thenReturn(null);
        Employee result = employeeService.getEmpById(4);
        assertNull(result);
        verify(employeeMapper, times(1)).findById(4);
    }

    @Test
    void test_removeEmployee_Success() {
        int empId = 5;
        doNothing().when(employeeMapper).softDelete(empId);
        boolean result = employeeService.removeEmployee(empId);
        assertTrue(result);
        verify(employeeMapper, times(1)).softDelete(empId);
    }

    @Test
    void test_removeEmployee_Failure() {
        int empId = 6;
        doThrow(new RuntimeException("Database error")).when(employeeMapper).softDelete(empId);
        boolean result = employeeService.removeEmployee(empId);
        assertFalse(result);
        verify(employeeMapper, times(1)).softDelete(empId);
    }

//    @Test
//    void test_showActiveEmployee() {
//        List<Employee> activeEmployees = new ArrayList<>();
//        activeEmployees.add(new Employee(7, "Sudhanshu", "Sudhanshu1@gmail.com", "SDE"));
//        when(userMapper.showactive()).thenReturn(activeEmployees);
//        List<Employee> result = userService.showActiveEmployee();
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(1, result.size());
//        Assertions.assertEquals(7, result.get(0).getEmp_id());
//        verify(userMapper, times(1)).showactive();
//    }
}
