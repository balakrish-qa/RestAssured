Feature: Github repository using curd operations

Scenario: Verify create new repository with authenticated user

When I create a new repository
Then I verify status code response 201

Scenario: Verify the the list of repositories of an authenticated user
When I read all the list of repositories
Then I verify status code response 200
Then I verify the title of the repository from response body

Scenario: Update the list of topics for a repository
When I update new topics to the repository
Then I verify status code response 200
Then I verify the topic name from the response body

Scenario: Delete the repository using token
When I delete the repository
Then I verify status code response 204 