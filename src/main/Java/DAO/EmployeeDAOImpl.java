package DAO;

import Config.ApplicationConnection;
import Model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmployeeDAOImpl implements EmployeeDAO {

	private final ApplicationConnection appConnection = new ApplicationConnection();

	@Override
	public boolean addEmployee(Employee employee) {
		try (PreparedStatement preparedStatement = appConnection.getPreparedStatement("INSERT INTO employee(name, last_name, age, city_id) VALUES (?, ?, ?, ?);")) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getLast_name());
			preparedStatement.setInt(3, employee.getAge());
			if (employee.getCity_id() == null || String.valueOf(employee.getCity_id()).trim().isEmpty()) {
				preparedStatement.setNull(4, Types.INTEGER);
			} else {
				preparedStatement.setInt(4, employee.getCity_id()); // если не выходит за рамки city id!!!
			}
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Employee findById(int id) {
		Employee employee = null;
		try (PreparedStatement preparedStatement = appConnection.getPreparedStatement("SELECT * FROM employee WHERE id = ?;")) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			employee = new Employee(
					resultSet.getString(2),
					resultSet.getString(3),
					resultSet.getInt(4),
					resultSet.getInt(5));
			employee.setId(resultSet.getInt(1));
			return employee;
		} catch (SQLException e) {
			e.printStackTrace();
			return employee;
		}
	}

	@Override
	public Map<Integer, Employee> getAll() {
		Employee employee;
		Map<Integer, Employee> employees = new LinkedHashMap<>();
		try (PreparedStatement preparedStatement = appConnection.getPreparedStatement("SELECT * FROM employee;")) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee = new Employee(
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getInt(4),
						resultSet.getInt(5));
				employee.setId(resultSet.getInt("id"));
				employees.put(resultSet.getInt("id"), employee);
			}
			return employees;
		} catch (SQLException e) {
			e.printStackTrace();
			return employees;
		}
	}

	@Override
	public boolean updateById(int id, Employee employee) {
		try (PreparedStatement preparedStatement = appConnection.getPreparedStatement("UPDATE employee SET name = ?, last_name = ?, age = ?, city_id = ? WHERE id = ?")) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getLast_name());
			preparedStatement.setInt(3, employee.getAge());
			if (employee.getCity_id() == null || String.valueOf(employee.getCity_id()).trim().isEmpty()) {
				preparedStatement.setNull(4, Types.INTEGER);
			} else {
				preparedStatement.setInt(4, employee.getCity_id()); // если не выходит за рамки city id!!!
			}
			preparedStatement.setInt(5, id);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try (PreparedStatement preparedStatement = appConnection.getPreparedStatement("DELETE FROM employee WHERE id = ?;")) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
