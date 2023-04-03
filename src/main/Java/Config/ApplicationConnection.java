package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class ApplicationConnection {

	private Connection getConnection() {
		final String password = "123";
		final String user = "myUser";
		final String url = "jdbc:postgresql://localhost:5432/postgres";
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public PreparedStatement getPreparedStatement(String sqlQuery) throws SQLException {
		return Objects.requireNonNull(getConnection()).prepareStatement(sqlQuery);
	}
}
