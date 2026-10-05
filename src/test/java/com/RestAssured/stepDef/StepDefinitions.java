package com.RestAssured.stepDef;

import org.json.simple.parser.ParseException;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class StepDefinitions {
	private Response response;
	RequestMethodsForGitHub r = new RequestMethodsForGitHub();
    


	@When("I create a new repository")
	public void i_create_a_new_repository() {
	   response = r.createRepo();
	    
	}


	@When("I read all the list of repositories")
	public void i_read_all_the_list_of_repositories() {
	    response = r.readListOfRepo();
	}

	
	@Then("I verify status code response {int}")
	public void i_verify_status_code_response(Integer int1) {
		Assert.assertTrue(response.getStatusCode() == int1);
	}

	@Then("I verify the title of the repository from response body")
	public void i_verify_the_title_of_the_repository_from_response_body() throws ParseException {
//	    Assert.assertTrue(r.verifyTitleOfCreatedRepo(response));
//		r.verifyTitleOfCreatedRepo(response,"New_Repo_Apr");
		Assert.assertEquals(r.verifyTitleOfCreatedRepo(response,"New_Repo_Apr"), "New_Repo_Apr");
	}

	@When("I update new topics to the repository")
	public void i_update_new_topics_to_the_repository() {
	   response = r.replaceAllTopicsOfRepo();
	}

	@Then("I verify the topic name from the response body")
	public void i_verify_the_topic_name_from_the_response_body() {
	   
	}

	@When("I delete the repository")
	public void i_delete_the_repository() {
	    response = r.deleteRepository();
	}


}
