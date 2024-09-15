package com.example.AttendenceMyBat.mapper;
import com.example.AttendenceMyBat.model.Attendance;
import com.example.AttendenceMyBat.model.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.time.LocalDate;
import java.util.List;
@Mapper
public interface AttendanceMapper {
    @Select("SELECT distinct u.emp_id,name  FROM employee_details u JOIN attendance a ON u.emp_id = a.emp_id WHERE a.check_in_date = #{check_in_date}")
    List<Employee> presentEmployee(LocalDate check_in_date);
    @Select("SELECT a.emp_id, a.name " +
            "FROM employee_details AS a " +
            "LEFT JOIN (SELECT emp_id FROM attendance WHERE check_in_date = #{check_in_date}) AS b " +
            "ON a.emp_id = b.emp_id " +
            "WHERE b.emp_id IS NULL")
    List<Employee> findAbsentEmployee(LocalDate check_in_date);
    @Select("SELECT distinct emp_id,name FROM employee_details WHERE emp_id = #{emp_id}")
    List<Employee> findEmployeeByEmpId(int emp_id);
    @Select("SELECT * FROM employee_details order by last_updated LIMIT #{pageNum} OFFSET #{pageSize}")
    List<Employee> findAll(int pageNum, int pageSize);

    @Insert("INSERT INTO attendance (emp_id, check_in_time, check_in_date) " +
            "SELECT #{emp_id}, #{check_in_time}, #{check_in_date} " +
            "FROM employee_details " +
            "WHERE emp_id = #{emp_id} " +
            "AND NOT EXISTS ( " +
            "    SELECT 1 FROM attendance " +
            "    WHERE check_in_date = #{check_in_date})")
    void CheckIn(Attendance attendance);
    @Update("UPDATE attendance SET check_out_time = #{check_out_time} WHERE emp_id = #{emp_id} ")
    void checkout(Attendance attendance);
}
