package com.product.api;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fake.api.com.Product;
import fake.api.com.ProductLombok;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductAPITest {

	@Test
	public void getProductTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().log().all().when().log().all().get("/products");

		ObjectMapper mapper = new ObjectMapper();
		try {
			Product product[] = mapper.readValue(response.getBody().asString(), Product[].class);
			for (Product p : product) {

				System.out.println("id :" + p.getId());
				System.out.println("title :" + p.getTitle());
				System.out.println("price :" + p.getPrice());
				System.out.println("description :" + p.getDescription());
				System.out.println("category :" + p.getCategory());
				System.out.println("image :" + p.getImage());
				System.out.println("Rate :" + p.getRating().getRate());
				System.out.println("Rate :" + p.getRating().getCount());
				System.out.println("-------------------------");

			}

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getProductTest_WITH_POJO_LOMBOK() {

		{
			RestAssured.baseURI = "https://fakestoreapi.com";

			Response response = given().log().all().when().log().all().get("/products");

			ObjectMapper mapper = new ObjectMapper();
			try {
				Product product[] = mapper.readValue(response.getBody().asString(), Product[].class);
				for (Product p : product) {

					System.out.println("id :" + p.getId());
					System.out.println("title :" + p.getTitle());
					System.out.println("price :" + p.getPrice());
					System.out.println("description :" + p.getDescription());
					System.out.println("category :" + p.getCategory());
					System.out.println("image :" + p.getImage());
					System.out.println("Rate :" + p.getRating().getRate());
					System.out.println("Rate :" + p.getRating().getCount());
					System.out.println("-------------------------");

				}

			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}
	}
	
	@Test
	public void getProductTest_WITH_POJO_LOMBOK_BuilderPatter() {

		{
			RestAssured.baseURI = "https://fakestoreapi.com";

			Response response = given().log().all().when().log().all().get("/products");

			ObjectMapper mapper = new ObjectMapper();
			try {
				Product product[] = mapper.readValue(response.getBody().asString(), Product[].class);
				for (Product p : product) {

					System.out.println("id :" + p.getId());
					System.out.println("title :" + p.getTitle());
					System.out.println("price :" + p.getPrice());
					System.out.println("description :" + p.getDescription());
					System.out.println("category :" + p.getCategory());
					System.out.println("image :" + p.getImage());
					System.out.println("Rate :" + p.getRating().getRate());
					System.out.println("Rate :" + p.getRating().getCount());
					System.out.println("-------------------------");

				}

			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}
	}
}
