package com.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestMethods {

	String bearer_token = System.getenv("GITHUB_TOKEN");

	

	@Test(priority = 4)
	public void deleteRepo() {
		RestAssured.baseURI = "https://api.github.com";

		Response response = RestAssured
				.given().headers("Authorization", "Bearer " + bearer_token)
				.when().delete("/repos/Navin-2794/IPT_ENG_Dec")
				.then().log().all().extract().response();

		Assert.assertEquals(response.getStatusCode(), 204);

		System.out.println("Status code : " + response.getStatusCode());

	}
	
	@Test(priority = 3)
	public void updateAllTopicsOfRepo() {
		RestAssured.baseURI = "https://api.github.com";
		
		Response response = RestAssured.given().headers("Authorization","Bearer "+bearer_token)
		.body("{\r\n"
				+ "\"names\" : [ \"java\",\"selenium\",\"api\"]\r\n"
				+ "}")
		.when().put("/repos/Navin-2794/IPT_ENG_Dec2/topics")
		.then().log().all().extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Status code : " + response.getStatusCode());
		
	}
	
	@Test
	public void createRepo() {
		
		RestAssured.baseURI = "https://api.github.com";
		Response response = RestAssured
		.given().headers("Authorization", "Bearer "+bearer_token)
		.body("{\r\n"
				+ "    \"name\" : \"IPT_ENG_Dec5\"\r\n"
				+ "}")
		.when().post("/user/repos")
		.then().log().all().extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 201);
		System.out.println(response.getBody().asPrettyString());
		
	}
	@Test
	public void getAllRepo() {
		RestAssured.baseURI = "https://api.github.com";
		RestAssured
		.given().headers("Authorization", "Bearer "+bearer_token)
		.when().get("/users/Navin-2794/repos")
		.then().log().all().extract().response();
		
	}
	
	
	
	
	

}
