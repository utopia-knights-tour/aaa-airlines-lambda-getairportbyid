
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import entity.Airport;
import proxy.ApiGatewayProxyResponse;
import proxy.ApiGatewayRequest;
import util.ConnectUtil;

public class GetAirportById implements RequestHandler<ApiGatewayRequest, Object> {

	public Object handleRequest(ApiGatewayRequest request, Context context) {
		Airport airport = new Airport();
		Connection connection = null;
		LambdaLogger logger = context.getLogger();
		try {
			connection = ConnectUtil.getInstance().getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM Airport WHERE Airport.airportCode = ?");
			pstmt.setString(1, request.getPathParameters().get("airportId"));
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				return new ApiGatewayProxyResponse(404, null, null);
			}
			airport.setCode(rs.getString("airportCode"));
			airport.setName(rs.getString("airportName"));
			airport.setLocation(rs.getString("airportLocation"));
		} catch (ClassNotFoundException | NullPointerException | SQLException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(400, null, null);
		}
		return new ApiGatewayProxyResponse(200, null, new Gson().toJson(airport));
	}
}