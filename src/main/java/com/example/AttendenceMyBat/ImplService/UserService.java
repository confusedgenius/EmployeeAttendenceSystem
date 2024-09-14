package com.example.AttendenceMyBat.ImplService;

import com.example.AttendenceMyBat.mapper.UserMapper;
import com.example.AttendenceMyBat.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    public User registerUser(User user) {

        try {
            userMapper.insertUser(user);//error
        } catch (Exception e) {
            logger.error("error", e);
            logger.warn("error");
            logger.info("error");
            logger.debug("error");//not enabled by default
            logger.trace("error");//not enable by default

        }


        return user;


    }

    public User getUserById(int empId) {
        return userMapper.findById(empId);

    }

    //feature added 31/07/2024
    public boolean removeEmployee(int empId) {
        try {
            User user = new User();
            user.setEmp_status(false);
            userMapper.softDelete(empId);
            return true;
        } catch (Exception e) {
            logger.error("error found!!", e.getMessage());
        }
        return false;
    }

    public List<User> showactiveEmp() {
        return userMapper.showactive();
    }


}