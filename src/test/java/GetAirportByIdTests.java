import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import proxy.ApiGatewayProxyResponse;
import proxy.ApiGatewayRequest;

public class GetAirportByIdTests {

	private static Context context = null;
	private static LambdaLogger logger = null;

	@BeforeAll
	public static void beforeAll() {
		context = Mockito.mock(Context.class);
		logger = Mockito.mock(LambdaLogger.class);
		Mockito.when(context.getLogger()).thenReturn(logger);
	}

	@Test
	public void getAirportById() {
		ApiGatewayRequest request = Mockito.mock(ApiGatewayRequest.class);
		Map<String, String> pathParameters = new HashMap<String, String>();
		pathParameters.put("airportId", "ATL");
		Mockito.when(request.getPathParameters()).thenReturn(pathParameters);
		ApiGatewayProxyResponse response = new GetAirportById().handleRequest(request, context);
		assertEquals(200, response.getStatusCode());
	}

	@Test
	public void getAirportById400() {
		ApiGatewayRequest request = Mockito.mock(ApiGatewayRequest.class);
		Map<String, String> pathParameters = new HashMap<String, String>();
		pathParameters.put("airportId", "0");
		Mockito.when(request.getPathParameters()).thenReturn(pathParameters);
		ApiGatewayProxyResponse response = new GetAirportById().handleRequest(request, context);
		assertEquals(404, response.getStatusCode());
	}

}
