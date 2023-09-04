package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestTest {

	RequestSpecification request;

	@BeforeTest
	public void setup() {
		// Request
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
	}

	@Test
	public void getAllUsersAPITest() {
		Response response = request.get("/public/v2/users/");

		// Status code:
		int statusCode = response.statusCode();
		System.out.println("Status code: " + statusCode);

		// Verification point:
		Assert.assertEquals(statusCode, 200);

		// Status Message:
		String statusLine = response.statusLine();
		System.out.println("Status Line: " + statusLine);

		// Fetch the body:
		response.prettyPrint();

		// Fetch headers:
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type: " + contentType);

		System.out.println("--------------------");

		// Fetch all headers:
		List<Header> headersList = response.headers().asList();
		System.out.println("Total Headers: " + headersList.size());

		for (Header header : headersList) {
			System.out.println(header.getName() + " : " + header.getValue());
		}
	}

	@Test
	public void getAllUserWithQueryParameterAPITest() {

		Response response = request.get("/public/v2/users/?name=naveen&status=active");

		// Status code:
		int statusCode = response.statusCode();
		System.out.println("Status code: " + statusCode);

		// Verification point:
		Assert.assertEquals(statusCode, 200);

		// Status Message:
		String statusLine = response.statusLine();
		System.out.println("Status Line: " + statusLine);

		// Fetch the body:
		response.prettyPrint();
	}

	@Test
	public void getQueryParameterAPITest() {
		request.queryParam("name", "Rakesh");
		request.queryParam("status", "active");
		Response response = request.get("/public/v2/users");

		// Status code:
		int statusCode = response.statusCode();
		System.out.println("Status code: " + statusCode);

		// Verification point:
		Assert.assertEquals(statusCode, 200);

		// Status Message:
		String statusLine = response.statusLine();
		System.out.println("Status Line: " + statusLine);

		// Fetch the body:
		response.prettyPrint();
	}

	@Test
	public void getQueryParameterHashMapWithQueryParamsAPITest() {
		// Create a HashMap for query parameters:
		Map<String, String> queryParamsMap = new HashMap<String, String>();

		queryParamsMap.put("name", "naveen");
		queryParamsMap.put("gender", "male");

		request.queryParams(queryParamsMap);

		Response response = request.get("/public/v2/users");

		// Status code:
		int statusCode = response.statusCode();
		System.out.println("Status code: " + statusCode);

		// Verification point:
		Assert.assertEquals(statusCode, 200);

		// Status Message:
		String statusLine = response.statusLine();
		System.out.println("Status Line: " + statusLine);

		// Fetch the body:
		response.prettyPrint();
	}
}
