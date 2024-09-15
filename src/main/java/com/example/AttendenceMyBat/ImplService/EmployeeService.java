package com.example.AttendenceMyBat.ImplService;
import com.example.AttendenceMyBat.mapper.EmployeeMapper;
import com.example.AttendenceMyBat.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public void registerEmployee(List<Employee> employee)  {
             for(Employee emp:employee) {
                 try {
                     employeeMapper.registerEmployee(emp);
                     log.info("Successfully registered user with ID: {}", emp.getEmp_id());

                 } catch (Exception e) {
                     log.error("Failed to register user with ID: {}. Error: {}", emp.getEmp_id(), e.getMessage());
                 }
             }
    }

    public Employee getEmpById(int empId) {
        Employee employee = employeeMapper.findById(empId);
        if (employee != null) {
            log.info("Successfully fetched user with ID: {}", empId);
        } else {
            log.warn("No user found with ID: {}", empId);
        }
        return employee;
    }

    public boolean removeEmployee(int empId) {

        try {
            employeeMapper.softDelete(empId);
            log.info("Successfully removed employee with ID: {}", empId);
            return true;
        } catch (Exception e) {
            log.error("Failed to remove employee with ID: {}. Error: {}", empId, e.getMessage());
        }
        return false;
    }
    public List<Employee> showActiveEmployee() {
        List<Employee> activeEmployees = employeeMapper.showActiveEmployees();
        log.info("Successfully fetched {} active employees.", activeEmployees.size());
        return activeEmployees;
    }
}