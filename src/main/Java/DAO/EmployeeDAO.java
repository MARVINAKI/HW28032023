package DAO;

import Model.Employee;

import java.util.Map;

public interface EmployeeDAO {

	boolean addEmployee(Employee employee);

	Employee findById(int id);

	Map<Integer, Employee> getAll();

	boolean updateById(int id, Employee employee);

	boolean deleteById(int id);
}
