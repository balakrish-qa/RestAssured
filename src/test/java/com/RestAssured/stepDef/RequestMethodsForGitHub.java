package com.RestAssured.stepDef;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestMethodsForGitHub {

	String bearer_token = System.getenv("GITHUB_TOKEN");

	public Response createRepo() {
		File file = new File(".\\src\\main\\resources\\Create_Repository.json");

		Object obj;
		try {
			obj = new JSONParser().parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			RestAssured.baseURI = "https://api.github.com";

			Response response = RestAssured.given().contentType("application/json")
					.headers("Authorization", "Bearer " + bearer_token).body(jsonObject).when().post("/user/repos")
					.then().log().all().extract().response();
			
			Assert.assertEquals(response.getStatusCode(), 201);

			
			return response;
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public Response readListOfRepo() {

		try {
			Response response = RestAssured.given().headers("Authorization", "Bearer " + bearer_token).when()
					.get("/user/repos").then().log().all().extract().response();
			return response;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String verifyTitleOfCreatedRepo(Response response, String repoName) throws ParseException {
//		JSONParser jsonParser = new JSONParser();
//		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.asString());
		System.out.println("*****************************************");
		org.json.JSONArray s = new org.json.JSONArray(response.getBody().asString());
		
	//	org.json.JSONObject Data =(org.json.JSONObject)(s.get(0));
		
		
		for (int i = 0; i < s.length(); i++) {
			org.json.JSONObject jsonObject = s.getJSONObject(i);

            // Extract the value using key
            String value = jsonObject.getString("name");
            if(value.equals(repoName)) {
            	return value;
            }

           
        }
		
		
		
		
//		String status = jsonObject.get("status").toString();

//		JSONObject dataJsonObject = (JSONObject) jsonObject.get("data");

		
		return null;
	}
	
	public Response replaceAllTopicsOfRepo() {
		File file = new File(".\\src\\main\\resources\\Update_Repo_Topics.json");

		Object obj;
		try {
			obj = new JSONParser().parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			RestAssured.baseURI = "https://api.github.com";

			Response response = RestAssured.given().headers("Authorization","Bearer "+bearer_token)
					.body(jsonObject)
					.when().put("/repos/NavinkumarVaradharaj/New_Repo_Apr/topics")
					.then().log().all().extract().response();
					
			
			Assert.assertEquals(response.getStatusCode(), 200);

			
			return response;
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public Response deleteRepository() {
		RestAssured.baseURI = "https://api.github.com";

		Response response = RestAssured.given().headers("Authorization", "Bearer " + bearer_token).when()
				.delete("/repos/NavinkumarVaradharaj/New_Repo_Apr").then().log().all().extract().response();
		return response;
		
	}

}
