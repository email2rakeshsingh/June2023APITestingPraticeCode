package POSTAPs;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.File;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
public class BookingAuthAPI {

    @Test
    public void getBookingAuthTokenTest_JSONString() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String tokenID = given()
                .contentType(ContentType.JSON)
                .body("{\n" + "  \"username\" : \"admin\",\n" + "  \"password\" : \"password123\"\n" + "}")
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

    @Test
    public void getBookingAuthTokenTest_with_JSONFile() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String tokenID = given()
                .contentType(ContentType.JSON)
                .body(new File("./src/test/resources/Data/BasiAuth.json"))
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

    @Test
    public void addUserTest() {
        RestAssured.baseURI = "https://gorest.co.in";

        // 1. add user post call

        int userId = given().log().all()
                .contentType(ContentType.JSON)
                .body(new File("./src/test/resources/Data/addUser.json"))
                .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
                .when().log().all()
                .post("/public/v2/users/")
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .and()
                .body("name", equalTo("rakesh"))
                .extract()
                .path("id");

        System.out.println("User ID: " + userId);

        // get the same user and verify it: Get

        given()
                .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
                .when()
                .get("/public/v2/users/" + userId)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", equalTo(userId));
    }
}