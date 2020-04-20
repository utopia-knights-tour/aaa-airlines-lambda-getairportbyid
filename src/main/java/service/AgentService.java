package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.AirportDao;
import entity.Airport;
import util.ConnectUtil;

public class AgentService {

	public Airport getAirportById(String airportId) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = ConnectUtil.getInstance().getConnection();
			return new AirportDao(connection).get(airportId);
		} catch (SQLException e) {
			throw e;
		}
	}
}
