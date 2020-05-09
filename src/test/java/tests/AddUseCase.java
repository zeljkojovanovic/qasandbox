package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rs.htec.apps.qasandbox.qa.model.AllUseCasesResponse;
import rs.htec.apps.qasandbox.qa.model.LoginResponse;
import rs.htec.apps.qasandbox.qa.model.UseCaseRequest;
import utils.FilePaths;
import utils.FileReader;
import utils.Methods;

public class AddUseCase {

  private String token;
  private String title;
  private String description;
  private String expectedResult;
  private List<String> testStepsList;
  private boolean automated;

  @BeforeClass
  public void init() throws Exception {
    String username = "jovanovic.zeljko@outlook.com";
    String password = "Joel2020";
    LoginResponse loginResponse = Methods.login(username, password);
    token = loginResponse.getToken();
    automated = true;
  }

  @Test
  public void addUseCaseForLoggingInWithCorrectUsernameAndPassword() throws Exception {
    //initialize title, description, expected result and test steps:
    UseCaseRequest useCaseRequest = FileReader.readFileFromPathAndDeserialize(FilePaths.USE_CASE, UseCaseRequest.class);
    title = "Login with correct username and password";
    description = "Verify that user is able to login to QA Sandbox application by using correct username and password combination. After logging in, 'Dashboard' page is displayed.";
    expectedResult = "User is able to login with correct credentials.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter correct email in 'email' field.",
        "Enter correct password in 'Password' field - password that matches entered email.",
        "Click on 'Submit' button."));
    //set initialized values into body of the request for adding new use case:
    useCaseRequest.setTitle(title);
    useCaseRequest.setDescription(description);
    useCaseRequest.setExpectedResult(expectedResult);
    useCaseRequest.setTeststeps(testStepsList);
    useCaseRequest.setAutomated(automated);
    //add use case:
    Methods.addUseCase(useCaseRequest, token);
    //get newly added use case and verify it has entered values:
    List<AllUseCasesResponse> allUseCases = Methods.retrieveAllUseCases(token);
    for (AllUseCasesResponse aUC : allUseCases) {
      if(aUC.getTitle().equals(title)) {
        AssertJUnit.assertEquals("Description is not correct", description, aUC.getDescription());
        AssertJUnit.assertEquals("Expected result is not correct", expectedResult, aUC.getExpectedResult());
        AssertJUnit.assertEquals("Automated value is not correct", automated, aUC.isAutomated());
      }
    }
  }

  @Test
  public void addUseCaseForLoggingInWithoutPassword() throws Exception {
    //initialize title, description, expected result and test steps:
    UseCaseRequest useCaseRequest = FileReader.readFileFromPathAndDeserialize(FilePaths.USE_CASE, UseCaseRequest.class);
    title = "Login without a password";
    description = "Verify that user is not able to login with correct username, but without password.";
    expectedResult = "Error 'Password is required' is displayed for 'Password' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter correct username (e.g. 'jovanovic.zeljko@outlook.com') in 'email' field.",
        "Click on 'Submit' button without entering any password."));
    //set initialized values into body of the request for adding new use case:
    useCaseRequest.setTitle(title);
    useCaseRequest.setDescription(description);
    useCaseRequest.setExpectedResult(expectedResult);
    useCaseRequest.setTeststeps(testStepsList);
    useCaseRequest.setAutomated(automated);
    //add use case:
    Methods.addUseCase(useCaseRequest, token);
    //get newly added use case and verify it has entered values:
    List<AllUseCasesResponse> allUseCases = Methods.retrieveAllUseCases(token);
    for (AllUseCasesResponse aUC : allUseCases) {
      if(aUC.getTitle().equals(title)) {
        AssertJUnit.assertEquals("Description is not correct", description, aUC.getDescription());
        AssertJUnit.assertEquals("Expected result is not correct", expectedResult, aUC.getExpectedResult());
        AssertJUnit.assertEquals("Automated value is not correct", automated, aUC.isAutomated());
      }
    }
  }

  @Test
  public void addUseCaseForLoggingInWithoutUsername() throws Exception {
    //initialize title, description, expected result and test steps:
    UseCaseRequest useCaseRequest = FileReader.readFileFromPathAndDeserialize(FilePaths.USE_CASE, UseCaseRequest.class);
    title = "Login without an username";
    description = "Verify that user is not able to login without entering email address. Password, that matches an existing password, is entered.";
    expectedResult = "User is not able to login. Error 'Email field is required' is displayed for 'Email Address' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter a password that belongs to a registered user in 'Password' field.",
        "Click on 'Submit' button without entering an email address."));
    //set initialized values into body of the request for adding new use case:
    useCaseRequest.setTitle(title);
    useCaseRequest.setDescription(description);
    useCaseRequest.setExpectedResult(expectedResult);
    useCaseRequest.setTeststeps(testStepsList);
    useCaseRequest.setAutomated(automated);
    //add use case:
    Methods.addUseCase(useCaseRequest, token);
    //get newly added use case and verify it has entered values:
    List<AllUseCasesResponse> allUseCases = Methods.retrieveAllUseCases(token);
    for (AllUseCasesResponse aUC : allUseCases) {
      if(aUC.getTitle().equals(title)) {
        AssertJUnit.assertEquals("Description is not correct", description, aUC.getDescription());
        AssertJUnit.assertEquals("Expected result is not correct", expectedResult, aUC.getExpectedResult());
        AssertJUnit.assertEquals("Automated value is not correct", automated, aUC.isAutomated());
      }
    }
  }

  @Test
  public void addUseCaseForLoggingInWithoutUsernameAndPassword() throws Exception {
    //initialize title, description, expected result and test steps:
    UseCaseRequest useCaseRequest = FileReader.readFileFromPathAndDeserialize(FilePaths.USE_CASE, UseCaseRequest.class);
    title = "Login without username and password";
    description = "Verify that user is not able to login without entering email address and password.";
    expectedResult = "Error message 'Email field is required' for 'Email Address' field. Error message 'Password is required' for 'Password' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Click on 'Submit' button without entering email address or password."));
    //set initialized values into body of the request for adding new use case:
    useCaseRequest.setTitle(title);
    useCaseRequest.setDescription(description);
    useCaseRequest.setExpectedResult(expectedResult);
    useCaseRequest.setTeststeps(testStepsList);
    useCaseRequest.setAutomated(automated);
    //add use case:
    Methods.addUseCase(useCaseRequest, token);
    //get newly added use case and verify it has entered values:
    List<AllUseCasesResponse> allUseCases = Methods.retrieveAllUseCases(token);
    for (AllUseCasesResponse aUC : allUseCases) {
      if(aUC.getTitle().equals(title)) {
        AssertJUnit.assertEquals("Description is not correct", description, aUC.getDescription());
        AssertJUnit.assertEquals("Expected result is not correct", expectedResult, aUC.getExpectedResult());
        AssertJUnit.assertEquals("Automated value is not correct", automated, aUC.isAutomated());
      }
    }
  }
}
