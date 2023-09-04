package POSTAPs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.UUID;

import org.testng.annotations.Test;

import Pojo.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserWithPojoTest {

    public static String getRandomEmailID() {
      return "apiautomation" + System.currentTimeMillis() + "@gmail.com";
       // return "apiautomation" +UUID.randomUUID()+"@gmail.com";
    }

    @Test
    public void addUserTest() {
        RestAssured.baseURI = "https://gorest.co.in";
        User add = new User("Rakesh", getRandomEmailID(), "male", "active");

        // 1. add user post call

        int userId = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(add)
                .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
                .when()
                .log().all()
                .post("/public/v2/users/")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .and()
                .body("name", equalTo(add.getName()))
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
                .body("id", equalTo(userId))
                .and()
                .body("name", equalTo(add.getName()))
                .and()
                .body("status", equalTo(add.getStatus()));
    }
}
