package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rs.htec.apps.qasandbox.qa.model.AllUseCasesResponse;
import rs.htec.apps.qasandbox.qa.model.LoginResponse;
import rs.htec.apps.qasandbox.qa.model.AddUseCaseRequest;
import utils.FilePaths;
import utils.FileReader;
import utils.Methods;
import utils.User;

public class AddUseCase {

  private String token;
  private AddUseCaseRequest addUseCaseRequest;
  private String title;
  private String description;
  private String expectedResult;
  private List<String> testStepsList;
  private boolean automated;

  //method for verifying that use case is indeed added:
  private void verifyThatUseCaseIsAdded(String token, String title, String description, String expectedResult, boolean automated) throws Exception {
    List<AllUseCasesResponse> allUseCases = Methods.retrieveAllUseCases(token);
    for (AllUseCasesResponse aUC : allUseCases) {
      if(aUC.getTitle().equals(title)) {
        AssertJUnit.assertEquals("Description is not correct", description, aUC.getDescription());
        AssertJUnit.assertEquals("Expected result is not correct", expectedResult, aUC.getExpectedResult());
        AssertJUnit.assertEquals("Automated value is not correct", automated, aUC.isAutomated());
      }
    }
  }

  @BeforeClass
  public void init() throws Exception {
    //get token:
    String username = User.USER_ZELJKO.getUsername();
    String password = User.USER_ZELJKO.getPassword();
    LoginResponse loginResponse = Methods.login(username, password);
    token = loginResponse.getToken();
    //initialize body of request for adding use case:
    addUseCaseRequest = FileReader.readFileFromPathAndDeserialize(FilePaths.ADD_USE_CASE, AddUseCaseRequest.class);
    //initialize 'automated' value:
    automated = true;
  }

  @Test
  public void logInWithCorrectUsernameAndPassword() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Login with correct username and password";
    description = "Verify that user is able to login to QA Sandbox application by using correct username and password combination. After logging in, 'Dashboard' page is displayed.";
    expectedResult = "User is able to login with correct credentials.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter correct email in 'email' field.",
        "Enter correct password in 'Password' field - password that matches entered email.",
        "Click on 'Submit' button."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void logInWithoutPassword() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Login without a password";
    description = "Verify that user is not able to login with correct username, but without password.";
    expectedResult = "Error 'Password is required' is displayed for 'Password' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter correct username (e.g. 'jovanovic.zeljko@outlook.com') in 'email' field.",
        "Click on 'Submit' button without entering any password."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void logInWithoutUsername() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Login without an username";
    description = "Verify that user is not able to login without entering email address. Password, that matches an existing password, is entered.";
    expectedResult = "User is not able to login. Error 'Email field is required' is displayed for 'Email Address' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter a password that belongs to a registered user in 'Password' field.",
        "Click on 'Submit' button without entering an email address."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void logInWithoutUsernameAndPassword() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Login without username and password";
    description = "Verify that user is not able to login without entering email address and password.";
    expectedResult = "Error message 'Email field is required' for 'Email Address' field. Error message 'Password is required' for 'Password' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Click on 'Submit' button without entering email address or password."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void logInWithPasswordThatDoesNotHaveSixCharacters() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Login with password that does not have six characters";
    description = "Verify that user is not able to login with invalid password - password that does not have 6 characters.";
    expectedResult = "Error message 'Password must be at least 6 characters long' is displayed for 'Password' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter correct username (e.g. 'jovanovic.zeljko@outlook.com') in 'email' field.",
        "Enter invalid password that does not have 6 characters in 'Password' field (e.g. 'abc12').",
        "Click on 'Submit' button."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void logInWithInvalidPasswordThatHasAtLeastSixCharacters() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Login with invalid password that has at least six characters";
    description = "Verify that user is not able to login with correct username, but with password that does not match entered username. Invalid password has at least 6 characters.";
    expectedResult = "User is not able to login. Error 'Password incorrect' is displayed for 'Password' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter correct username (e.g. 'jovanovic.zeljko@outlook.com') in 'email' field.",
        "Enter invalid password that has at least 6 characters in 'Password' field (e.g. 'Test1234').",
        "Click on 'Submit' button."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void logInWithNonExistingUsername() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Login with not existing username";
    description = "Verify that user cannot login with non existing username.";
    expectedResult = "Error message 'User not found' is displayed for 'email' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter non existing username (e.g. 'jovanovic.zeljko@outlook1.com') in 'email' field.",
        "Enter a string that has at least 6 characters in 'Password' field (e.g. 'Test1234').",
        "Click on 'Submit' button."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void logInWithUsernameInInvalidEmailFormat() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Login with username in invalid email format";
    description = "Verify that user cannot login with username that is not in email format.";
    expectedResult = "Error message 'Email is invalid' is displayed for 'email' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter an username in 'email' field that is in invalid email format (e.g. 'sss@sss').",
        "Enter a string that has at least 6 characters in 'Password' field (e.g. 'Test1234').",
        "Click on 'Submit' button."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void lockAccountAfterFiveUnsuccessfulAttempts() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Lock account after five unsuccessful attempts";
    description = "Verify that account is locked after five unsuccessful attempts.";
    expectedResult = "After each unsuccessful attempt, application displays how many attempts are left. "
        + "Account is locked after five unsuccessful attempts and user cannot login even with correct password";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Login' button.",
        "Enter valid username in 'email' field (e.g. 'jovanovic.zeljko@outlook.com').",
        "Enter a string that has at least 6 characters in 'Password' field (e.g. 'Test1234').",
        "Click on 'Submit' button five times."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void resetPassword() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Reset password";
    description = "Verify that user is able to reset password with by entering existing email address.";
    expectedResult = "User successfully reset password.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Forget password' button.",
        "Enter existing username in 'Email Address' field (e.g. 'jovanovic.zeljko@outlook.com').",
        "Click on 'Submit' button.",
        "Open 'QA Sandbox Password Reset' email.",
        "Click on 'Change Password' button.",
        "Enter same values in 'password' and 'change password' fields (values are at least six characters long).",
        "Click on 'Submit' button."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void resetPasswordWithoutAnEmail() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Reset password without an email";
    description = "Verify that user cannot reset password without entering an email address.";
    expectedResult = "Error 'Email field is required' is displayed for 'email' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Forget password' button.",
        "Click on 'Submit' button without entering an email address."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void resetPasswordForNonExistingUser() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Reset password for non existing user";
    description = "Verify that password cannot be reset for non existing user";
    expectedResult = "Error is displayed stating that user does not exist";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Forget password' button.",
        "Enter non existing username in 'email' field (e.g. 'jovanovic.zeljko@outlook1.com').",
        "Click on 'Submit' button."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void resetPasswordForUsernameInInvalidEmailFormat() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Reset password for username that is in invalid email format";
    description = "Verify that password cannot be reset for username that is in invalid email format.";
    expectedResult = "Error message 'Email is invalid' is displayed for 'email' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Forget password' button.",
        "Enter an username that is not in valid email format (e.g. 'aaa@aaa').",
        "Click on 'Submit' button."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void resetPasswordWithEmptyPasswordFields() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Reset password with empty 'password' and 'confirm password' fields";
    description = "Verify that user is not able to reset password without entering and confirming password";
    expectedResult = "Error message 'Password is required' is displayed for 'password' field. "
        + "Error message 'Confirm password is required' is displayed for 'confirm password' field";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Forget password' button.",
        "Enter existing username in 'Email Address' field (e.g. 'jovanovic.zeljko@outlook.com').",
        "Click on 'Submit' button.",
        "Open 'QA Sandbox Password Reset' email.",
        "Click on 'Change Password' button in the email.",
        "Click on 'Submit' button without entering passwords."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void resetPasswordWithEmptyPasswordField() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Reset password with empty 'password' field";
    description = "Verify that user is not able to reset password without entering value in 'password' field";
    expectedResult = "Error message 'Password is required' is displayed for 'password' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Forget password' button.",
        "Enter existing username in 'Email Address' field (e.g. 'jovanovic.zeljko@outlook.com').",
        "Click on 'Submit' button.",
        "Open 'QA Sandbox Password Reset' email.",
        "Click on 'Change Password' button in the email.",
        "Enter a value in 'confirm password' field",
        "Click on 'Submit' button without entering value in 'password' field."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void resetPasswordWithEmptyConfirmPasswordField() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Reset password with empty 'confirm password' field";
    description = "Verify that user is not able to reset password without entering value in 'confirm password' field";
    expectedResult = "Error message 'Confirm password is required' is displayed for 'confrim password' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Forget password' button.",
        "Enter existing username in 'Email Address' field (e.g. 'jovanovic.zeljko@outlook.com').",
        "Click on 'Submit' button.",
        "Open 'QA Sandbox Password Reset' email.",
        "Click on 'Change Password' button in the email.",
        "Enter string that has at least six characters in 'password' field (e.g. 'Test1234').",
        "Click on 'Submit' button without entering value in 'Confirm password field'."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void resetPasswordWithPasswordThatDoesNotHaveSixCharacters() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Reset password with password that does not have six characters";
    description = "Verify that user is not able to reset password with password that does not have six characters";
    expectedResult = "Error message 'Password must be at least 6 characters long' is displayed for 'password' field.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Forget password' button.",
        "Enter existing username in 'Email Address' field (e.g. 'jovanovic.zeljko@outlook.com').",
        "Click on 'Submit' button.",
        "Open 'QA Sandbox Password Reset' email.",
        "Click on 'Change Password' button in the email.",
        "Enter string that does not have six characters in 'password' field (e.g. 'abc12').",
        "Click on 'Submit' button."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

  @Test
  public void resetPasswordWithNonMatchingPasswords() throws Exception {
    //initialize title, description, expected result and test steps:
    title = "Reset password with non matching passwords";
    description = "Verify that user is not able to reset password with non matching 'password' and 'confirm password' values.";
    expectedResult = "Error message 'Confirm password must match password' is displayed.";
    testStepsList = new ArrayList<>(Arrays.asList(
        "Go to https://qa-sandbox.apps.htec.rs",
        "Click on 'Forget password' button.",
        "Enter existing username in 'Email Address' field (e.g. 'jovanovic.zeljko@outlook.com').",
        "Click on 'Submit' button.",
        "Open 'QA Sandbox Password Reset' email.",
        "Click on 'Change Password' button in the email.",
        "Enter string that has at least six characters in 'password' field (e.g. 'Test1234').",
        "Enter string in 'confirm password' field that is not the same as value entered in 'password' field (e.g. 'Test123').",
        "Click on 'Submit' button."));
    //set body for adding new use case:
    addUseCaseRequest = new AddUseCaseRequest(title, testStepsList, expectedResult, description, automated);
    //add use case:
    Methods.addUseCase(addUseCaseRequest, token);
    //get newly added use case and verify it has entered values:
    verifyThatUseCaseIsAdded(token, title, description, expectedResult, automated);
  }

}
