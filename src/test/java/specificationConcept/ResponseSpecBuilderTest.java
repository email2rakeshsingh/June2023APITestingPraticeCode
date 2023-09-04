package specificationConcept;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.omg.CORBA.PRIVATE_MEMBER;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {

    private static final String BASE_URI = "https://gorest.co.in";
    private static final String SUCCESS_AUTH_TOKEN = "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6";
    private static final String FAIL_AUTH_TOKEN = "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6999";

    public static ResponseSpecification getResponseSpec200OK() {
        ResponseSpecification responseSpec200OK = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectHeader("Server", "cloudflare")
                .build();

        return responseSpec200OK;
    }

    public static ResponseSpecification getResponseSpec200OKWithBody() {
        ResponseSpecification responseSpec200OKWithBody = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectHeader("Server", "cloudflare")
                .expectBody("$.size()", equalTo(10))
                .expectBody("id", hasSize(10))
                .build();

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
    public void getUserRec200SpecTest() {
        RestAssured.baseURI = BASE_URI;
        given()
                .header("Authorization", SUCCESS_AUTH_TOKEN)
                .when()
                .get("/public/v2/users")
                .then()
                .assertThat()
                .spec(getResponseSpec200OK());
    }

    @Test
    public void getUserRec401AuthFailSpecTest() {
        RestAssured.baseURI = BASE_URI;
        given()
                .header("Authorization", FAIL_AUTH_TOKEN)
                .when()
                .get("/public/v2/users")
                .then()
                .assertThat()
                .spec(getResponseSpec401AuthFail());
    }

    @Test
    public void getUserRec200SpecTestWithBody() {
        RestAssured.baseURI = BASE_URI;
        given()
                .header("Authorization", SUCCESS_AUTH_TOKEN)
                .when()
                .get("/public/v2/users")
                .then()
                .assertThat()
                .spec(getResponseSpec200OKWithBody());
    }
}
