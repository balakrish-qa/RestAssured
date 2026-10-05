package com.RestAssured;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = ".\\src\\test\\resources\\com\\RestAssured\\features\\githubRepo.feature",
					glue = "com.RestAssured.stepDef" , dryRun = false,publish = true)
public class Runner {

}
