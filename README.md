
# Project Title

Rest assured E2E Framework.


# Prerequiste
JDK, IDE




## Features

- Logging
- Extent Report
- API Chaining
- E2E execution using pom.xml/testng.xml
- Moduler framework structure
- Dynamic Json body





## Running Tests

To run tests, run the following command

```bash
  mvn clean test
```


## Points to be noted:

API used: https://favqs.com/api/

Documentation: https://favqs.com/api


## Challenges faced:

Their are some issues with the api's  and the api documentation provided.

1)Few of the API's recieveing 500 from the favq server itself. Its not an issue of the framework.

For example Update user api--recieving 500.

2)On execution of e2e framework, Flow works like :--
A new user created,then login and token stored and passed in further api's but it is causing issue. Every time a new user is created hence new user's activity is 0 that's why "get activity" and "delete activity" will fail as activity id will be 0 every time for new user and there is no api to create new activity.So I had to provide user-token from properties file specifically for these 2 scenarios.


## Tips
Please comment logResponseInReport(response) method in case of single tc execution.
