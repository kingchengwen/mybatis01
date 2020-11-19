package com.mi.dao;

import com.mi.pojo.Employee;

import java.util.List;

public interface EmployeeMapper {
    public Employee getEmployeeById(int id);

    public void addEmp(Employee employee);

    public void updateEmp(Employee employee);

    public void deleteEmp(Integer id);

    public List<Employee> getEmployees();


}
