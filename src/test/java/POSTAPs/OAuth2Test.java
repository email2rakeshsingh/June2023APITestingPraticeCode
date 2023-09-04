package POSTAPs; // Replace with the appropriate package name

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OAuth2Test {

    private String accessTokenString; // Access token class member

    @BeforeMethod
    public void getAccessToken() {
        // 1st. get the access token part
        RestAssured.baseURI = "https://test.api.amadeus.com";

        accessTokenString = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", "jnulx1e075sdqV8bP17GaMSvcF6SuQS4")
                .formParam("client_secret", "Y7qJanyTAK2tE9AA")
            .when()
                .post("/v1/security/oauth2/token")
            .then()
                .assertThat().statusCode(200)
                .extract().path("access_token");

        System.out.println(accessTokenString);
    }

    @Test
    public void GetFlightInfoTest() {
        // 2. get flight token: get
        Response Flightdata = given().log().all()
                .header("Authorization", "Bearer " + accessTokenString)
                .queryParam("origin", "PAR")
                .queryParam("maxPrice", "200")
            .when().log().all()
                .get("/v1/shopping/flight-destinations")
            .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        System.out.println(Flightdata.prettyPrint());

        // You can extract and print other data as needed
        // ...
    }
}
