package jsonPathValidatorTest;

import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
public class JsonPathTest {

	@Test
	public void getCountryValue() {
		// Specify the base URL and path parameter (year) for the GET call
		String baseUrl = "http://ergast.com/api/f1";
		String year = "2017";
		String url = baseUrl + "/" + year + "/circuits.json";

		// Send the GET request and extract the response
		Response response = given().get(url);

		// Extract the response body as a string
		String jsonResponse = response.getBody().asString();

		// Use JsonPath to extract the country value using the given JSONPath expression
		List<String> countryList = JsonPath.read(jsonResponse,
				"$.MRData.CircuitTable.Circuits[?(@.circuitId == 'shanghai')].Location.country");

		// Print the country value
		System.out.println("Country: " + countryList.get(0)); // Get the first item from the list

	}

	@Test
	public void getCircuitDataAPIWith_YearTest() {
		RestAssured.baseURI = "http://ergast.com";

		Response response = given().log().all().when().log().all().get("/api/f1/2017/circuits.json");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		int totalCircuits = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
		System.out.println(totalCircuits);

		List<String> countryList = JsonPath.read(jsonResponse, "$..Circuits..Location.country"); // Updated JSONPath
		System.out.println(countryList.size());
		System.out.println(countryList);

	}

	@Test
	public void getProductTest() {

		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		List<Float> rateLessThanThree = JsonPath.read(jsonResponse, "$[?(@.rating.rate < 3)].rating.rate");
		System.out.println(rateLessThanThree);

		System.out.println("---------------");

		// with two attributes
		List<Map<String, Object>> jewellryList = JsonPath.read(jsonResponse,
				"$[?(@.category == 'jewelery')].[{title: @.title, price: @.price}]"); // Updated JSONPath
		System.out.println(jewellryList);

		for (Map<String, Object> product : jewellryList) {
			String title = (String) product.get("title");
			Object price = product.get("price");

			System.out.println("title : " + title);
			System.out.println("price : " + price);
			System.out.println("--------");
		}

		System.out.println("---------------");

		// with three attributes
		List<Map<String, Object>> jewellryList2 = JsonPath.read(jsonResponse,
				"$[?(@.category == 'jewelery')].[{title: @.title, price: @.price, id: @.id}]"); // Updated JSONPath
		System.out.println(jewellryList2);

		for (Map<String, Object> product : jewellryList2) {
			String title = (String) product.get("title");
			Object price = product.get("price");
			Integer id = (Integer) product.get("id");

			System.out.println("title : " + title);
			System.out.println("price : " + price);
			System.out.println("id : " + id);
			System.out.println("--------");
		}
	}
}
