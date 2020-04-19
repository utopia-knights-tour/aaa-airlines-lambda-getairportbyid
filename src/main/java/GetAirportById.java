
import java.sql.SQLException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import entity.Airport;
import proxy.ApiGatewayProxyResponse;
import proxy.ApiGatewayRequest;
import service.AgentService;

public class GetAirportById implements RequestHandler<ApiGatewayRequest, ApiGatewayProxyResponse> {

	private AgentService agentService = new AgentService();

	public ApiGatewayProxyResponse handleRequest(ApiGatewayRequest request, Context context) {
		LambdaLogger logger = context.getLogger();
		try {
			if (request.getPathParameters() == null || request.getPathParameters().get("airportId") == null) {
				return new ApiGatewayProxyResponse(400, null, null);
			}
			Airport airport = agentService.getAirportById(request.getPathParameters().get("airportId"));
			if (airport == null) {
				return new ApiGatewayProxyResponse(404, null, null);
			}
			return new ApiGatewayProxyResponse(200, null, new Gson().toJson(airport));
		} catch (SQLException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(400, null, null);
		} catch (ClassNotFoundException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(500, null, null);
		}
	}
}