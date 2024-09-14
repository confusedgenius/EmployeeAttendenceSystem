package com.example.AttendenceMyBat.mapper;

import com.example.AttendenceMyBat.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface UserMapper {
    @Select("SELECT * FROM employee_detail WHERE emp_status= 1")
    public List<User> showactive();

    @Select("SELECT * FROM employee_detail WHERE emp_id=#{emp_id}")
    User findById(int emp_id);

    @Insert("INSERT INTO employee_detail(emp_id,name, email, role,emp_status) VALUES (#{emp_id},#{name}, #{email}, #{role},true)")
    void registerUser(User employee_detail);

    @Update("UPDATE User SET emp_status=0 WHERE emp_id=#{emp_id} AND emp_status = 1 ")
    void softDelete(int emp_id);


}