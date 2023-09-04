package specificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpacBulderTest {
	
	
	public static RequestSpecification user_req_spec() {
		
		RequestSpecification requestspec= new RequestSpecBuilder ()
			     .setBaseUri("https://gorest.co.in")
			        .setContentType(ContentType.JSON)
			           .addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			            .build();
		
		return requestspec;
		
	}
	
	
	
	@Test
	public void getUser_With_Request_Spec() {
		RestAssured.given().log().all()
		              .spec(user_req_spec())
		                 .get("/public/v2/users")
		                    .then()
		                       .statusCode(200);

	}
	
	@Test
	public void getUser_With_param_Request_Spec() {
		RestAssured.given().log().all()
		              .queryParam("name", "naveen")
		                .queryParam("status", "active")
		                 .spec(user_req_spec())
		                  .get("/public/v2/users")
		                    .then()
		                       .statusCode(200);

	}

}
