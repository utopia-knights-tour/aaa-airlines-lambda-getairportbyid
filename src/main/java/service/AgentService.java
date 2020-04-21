package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.AirportDao;
import datasource.HikariCPDataSource;
import entity.Airport;

public class AgentService {

	public Airport getAirportById(String airportId) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = HikariCPDataSource.getConnection();
			return new AirportDao(connection).get(airportId);
		} catch (SQLException e) {
			throw e;
		} finally {
			connection.close();
		}
	}
}
