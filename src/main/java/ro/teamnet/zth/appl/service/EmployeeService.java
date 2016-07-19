package ro.teamnet.zth.appl.service;


import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findOneEmployee(Long id);

    List<Employee> findAllEmployees();

    void deleteOneEmployee(Long id);

}
