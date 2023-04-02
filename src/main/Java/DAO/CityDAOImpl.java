package DAO;

import Config.ApplicationConnection;
import Model.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CityDAOImpl implements CityDAO {

	public static final ApplicationConnection appConnect = new ApplicationConnection();

	@Override
	public boolean addCity(City city) {
		try (PreparedStatement preparedStatement = appConnect.getPreparedStatement("INSERT INTO city(name) VALUES (?);")) {
			preparedStatement.setString(1, city.getName());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public City findByID(int id) {
		try (PreparedStatement preparedStatement = appConnect.getPreparedStatement("SELECT * FROM city WHERE id = ?;")) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return new City(resultSet.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<Integer, String> getAll() {
		Map<Integer, String> cities = new LinkedHashMap<>();
		try (PreparedStatement preparedStatement = appConnect.getPreparedStatement("SELECT * FROM city;")) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cities.put(resultSet.getInt(1), resultSet.getString(2));
			}
			return cities;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateByID(int id, City city) {
		try (PreparedStatement preparedStatement = appConnect.getPreparedStatement("UPDATE city SET name = ? WHERE id = ?;")) {
			preparedStatement.setString(1, city.getName());
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteByID(int id) {
		try (PreparedStatement preparedStatement = appConnect.getPreparedStatement("DELETE FROM city WHERE id = ?;")) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
