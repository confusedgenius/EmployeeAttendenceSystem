package com.example.AttendenceMyBat.ImplService;
import com.example.AttendenceMyBat.mapper.UserMapper;
import com.example.AttendenceMyBat.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void registerUser(User user) {
        try {
            userMapper.registerUser(user);
            log.info("Successfully registered user with ID: {}", user.getEmp_id());
        } catch (Exception e) {
            log.error("Failed to register user with ID: {}. Error: {}", user.getEmp_id(), e.getMessage());
        }
    }

    public User getEmpById(int empId) {
        User user = userMapper.findById(empId);
        if (user != null) {
            log.info("Successfully fetched user with ID: {}", empId);
        } else {
            log.warn("No user found with ID: {}", empId);
        }
        return user;
    }

    public boolean removeEmployee(int empId) {

        try {
            userMapper.softDelete(empId);
            log.info("Successfully removed employee with ID: {}", empId);
            return true;
        } catch (Exception e) {
            log.error("Failed to remove employee with ID: {}. Error: {}", empId, e.getMessage());
        }
        return false;
    }
    public List<User> showActiveEmployee() {
        List<User> activeUsers = userMapper.showactive();
        log.info("Successfully fetched {} active employees.", activeUsers.size());
        return activeUsers;
    }
}