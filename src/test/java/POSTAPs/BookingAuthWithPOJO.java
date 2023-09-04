package POSTAPs;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pojo.Credentials;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BookingAuthWithPOJO {
	
	@Test
	public void getBookingAuthTokenTest_JSONString() {

		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		Credentials creds = new Credentials("admin", "password123");

		String tokenID = given()
				.contentType(ContentType.JSON)
				.body(creds)
				.when()
				.post("/auth")
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.path("token");

		System.out.println(tokenID);
		Assert.assertNotNull(tokenID);
	}
}
