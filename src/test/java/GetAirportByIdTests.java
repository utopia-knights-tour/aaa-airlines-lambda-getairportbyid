import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.Context;

import proxy.ApiGatewayProxyResponse;
import proxy.ApiGatewayRequest;

public class GetAirportByIdTests {

	@Test
	public void getAirportById() {
		ApiGatewayRequest request = new ApiGatewayRequest();
		Map<String, String> pathParameters = new HashMap<String, String>();
		pathParameters.put("airportId", "ATL");
		request.setPathParameters(pathParameters);
		Context context = new MockContext();
		ApiGatewayProxyResponse response = (ApiGatewayProxyResponse) new GetAirportById().handleRequest(request,
				context);
		assertEquals(200, response.getStatusCode());
	}

	@Test
	public void getAirportById400() {
		ApiGatewayRequest request = new ApiGatewayRequest();
		Map<String, String> pathParameters = new HashMap<String, String>();
		pathParameters.put("airportId", "0");
		request.setPathParameters(pathParameters);
		Context context = new MockContext();
		ApiGatewayProxyResponse response = (ApiGatewayProxyResponse) new GetAirportById().handleRequest(request,
				context);
		assertEquals(404, response.getStatusCode());
	}

}
