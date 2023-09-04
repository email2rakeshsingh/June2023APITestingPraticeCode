package specificationConcept;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseSpecificationTest {

	private static final String BASE_URI = "https://gorest.co.in";
	private static final String AUTH_TOKEN = "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6";

	public static RequestSpecification getRequestSpec() {
		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri(BASE_URI)
				.addHeader("Authorization", AUTH_TOKEN).build();

		return requestSpec;
	}

	public static ResponseSpecification getResponseSpec200OK() {
		ResponseSpecification responseSpec200OK = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectHeader("Server", "cloudflare").build();

		return responseSpec200OK;
	}

	public static ResponseSpecification getResponseSpec200OKWithBody() {
		ResponseSpecification responseSpec200OKWithBody = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectHeader("Server", "cloudflare").expectBody("$.size()", equalTo(10))
				.expectBody("id", hasSize(10)).build();

		return responseSpec200OKWithBody;
	}
	
	public static ResponseSpecification getResponseSpec401AuthFail() {
		ResponseSpecification responseSpec401AuthFail = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(401)
				.expectHeader("Server", "cloudflare")
				.build();

		return responseSpec401AuthFail;
	}

	@Test
	public void getUserRecordsTest() {
		given().spec(getRequestSpec()).when().get("/public/v2/users").then().spec(getResponseSpec200OK());
	}

	@Test
	public void getUserRecordsTestWithBody() {
		given().spec(getRequestSpec()).when().get("/public/v2/users").then().spec(getResponseSpec200OKWithBody());
	}
	
	@Test
	public void getResponseSpec401AuthFailTest() {
		given().spec(getRequestSpec()).when().get("/public/v2/users").then().spec(getResponseSpec401AuthFail());
	}

}
