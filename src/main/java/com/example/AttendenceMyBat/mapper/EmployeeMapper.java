package com.example.AttendenceMyBat.mapper;

import com.example.AttendenceMyBat.model.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM employee_details WHERE emp_status= 1")
    List<Employee> showActiveEmployees();

    @Select("SELECT * FROM employee_details WHERE emp_id=#{emp_id}")
    Employee findById(int emp_id);

    @Insert("INSERT INTO employee_details (emp_id, name, email, gender, date_of_joining, department, phone_number, position, salary, role, emp_status) " + "VALUES (#{emp_id}, #{name}, #{email}, #{gender}, #{date_of_joining}, #{department}, #{phone_number}, #{position}, #{salary}, #{role}, #{emp_status})")
    void registerEmployee(Employee employee);

    @Update("UPDATE User SET emp_status=0 WHERE emp_id=#{emp_id} AND emp_status = 1 ")
    void softDelete(int emp_id);


}