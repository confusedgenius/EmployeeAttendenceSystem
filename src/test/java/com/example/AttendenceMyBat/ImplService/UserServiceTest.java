package com.example.AttendenceMyBat.ImplService;
import com.example.AttendenceMyBat.mapper.UserMapper;
import com.example.AttendenceMyBat.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test_Registered_the_employee_Successfully() {
        User user = new User(1, "Sudhanshu", "sudhanshu@example.com", "SE");
        doNothing().when(userMapper).registerUser(user);
        //do nothing void methods ko call krne ke liye hota hai
        userService.registerUser(user);
        verify(userMapper, times(1)).registerUser(user);
        //verify is used to verify the no of time the specified funcn is called.
    }

    @Test
    void testRegisterUser_Failure() {
        User user = new User(2, "Sudhanshu", "sudhanshu11@gmail.com", "HR");
        doThrow(new RuntimeException("Database error")).when(userMapper).registerUser(user);
        assertDoesNotThrow(() -> userService.registerUser(user));
        verify(userMapper, times(1)).registerUser(user);
    }

    @Test
    void test_getUser_By_empId() {
        User user = new User(3, "abc", "abc@gmail.com", "SDE");
        when(userMapper.findById(3)).thenReturn(user);
        User result = userService.getEmpById(3);
        Assertions.assertEquals(3, result.getEmp_id());
        verify(userMapper, times(1)).findById(3);
    }

    @Test
    void test_GetUserById_NotFound() {
        when(userMapper.findById(4)).thenReturn(null);
        User result = userService.getEmpById(4);
        assertNull(result);
        verify(userMapper, times(1)).findById(4);
    }

    @Test
    void test_RemoveEmployee_Success() {
        int empId = 5;
        doNothing().when(userMapper).softDelete(empId);
        boolean result = userService.removeEmployee(empId);
        assertTrue(result);
        verify(userMapper, times(1)).softDelete(empId);
    }

    @Test
    void test_RemoveEmployee_Failure() {
        int empId = 6;
        doThrow(new RuntimeException("Database error")).when(userMapper).softDelete(empId);
        boolean result = userService.removeEmployee(empId);
        assertFalse(result);
        verify(userMapper, times(1)).softDelete(empId);
    }

    @Test
    void test_ShowActiveEmployee() {
        List<User> activeUsers = new ArrayList<>();
        activeUsers.add(new User(7, "Sudhanshu", "Sudhanshu1@gmail.com", "SDE"));
        when(userMapper.showactive()).thenReturn(activeUsers);
        List<User> result = userService.showActiveEmployee();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(7, result.get(0).getEmp_id());
        verify(userMapper, times(1)).showactive();
    }
}
