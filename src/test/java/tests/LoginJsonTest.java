package tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import rs.htec.apps.qasandbox.qa.model.LoginErrorResponse;
import rs.htec.apps.qasandbox.qa.model.LoginResponse;
import utils.Methods;

public class LoginJsonTest {

  private String username = "jovanovic.zeljko@outlook.com";
  private String password = "Joel2020";

  @Test
  public void loginWithCorrectUsernameAndPassword() throws Exception {
    //login with correct username and password:
    LoginResponse loginResponse = Methods.login(username, password);
    //verify that 'success' field has 'true' value:
    AssertJUnit.assertTrue("'success' field does not have 'true' value", loginResponse.getSuccess());
    //verify that 'token' field has some String value
    AssertJUnit.assertFalse("'token' field is empty", loginResponse.getToken().isEmpty());
    //verify that 'refreshToken' field has some String value
    AssertJUnit.assertFalse("'refreshToken' field is empty", loginResponse.getRefreshToken().isEmpty());
  }

  @Test
  public void loginWithoutPassword() throws Exception {
    //login with correct username, but without password:
    LoginErrorResponse loginErrorResponse = Methods.loginError(username,"",400);
    //verify that error 'Password is required' is displayed for 'password' field:
    AssertJUnit.assertEquals("Error message is not correct for 'password' field","Password is required", loginErrorResponse.getPassword());
    //verify that there is no error for username field:
    AssertJUnit.assertNull("Error message is displayed for 'email' field", loginErrorResponse.getEmail());
  }

  @Test
  public void loginWithoutUsername() throws Exception {
    //login with password that matches an existing password, but without username:
    LoginErrorResponse loginErrorResponse = Methods.loginError("", password,400);
    //verify that error 'Email field is required' is displayed for 'email' field:
    AssertJUnit.assertEquals("Error message is not correct for 'email' field","Email field is required", loginErrorResponse.getEmail());
    //verify that there is no error for 'password' field:
    AssertJUnit.assertNull("Error message is displayed for 'password' field", loginErrorResponse.getPassword());
  }

  @Test
  public void loginWithoutUsernameAndPassword() throws Exception {
    //login without username and password:
    LoginErrorResponse loginErrorResponse = Methods.loginError("", "",400);
    //verify that error 'Email field is required' is displayed for 'email' field:
    AssertJUnit.assertEquals("Error message is not correct for 'email' field","Email field is required", loginErrorResponse.getEmail());
    //verify that error 'Password is required' is displayed for 'password' field:
    AssertJUnit.assertEquals("Error message is not correct for 'password' field","Password is required", loginErrorResponse.getPassword());
  }

  @Test
  public void loginWithPasswordThatDoesNotHaveSixCharacters() throws Exception {
    //login with correct username, but with password that does not have 6 characters:
    LoginErrorResponse loginErrorResponse = Methods.loginError(username,"abc12",400);
    //verify that error 'Password must be at least 6 characters long' is displayed for 'password' field:
    AssertJUnit.assertEquals("Error message is not correct for 'password' field","Password must be at least 6 characters long", loginErrorResponse.getPassword());
    //verify that there is no error for username field:
    AssertJUnit.assertNull("Error message is displayed for 'email' field", loginErrorResponse.getEmail());
  }

  @Test
  public void loginWithInvalidPasswordThatHasAtLeastSixCharacters() throws Exception {
    //login with correct username, but with invalid password that has at least 6 characters:
    LoginErrorResponse loginErrorResponse = Methods.loginError(username,"Test1234",400);
    //verify that error 'Password incorrect' is displayed for 'password' field:
    AssertJUnit.assertEquals("Error message is not correct for 'password' field","Password incorrect", loginErrorResponse.getPassword());
    //verify that there is no error for username field:
    AssertJUnit.assertNull("Error message is displayed for 'email' field", loginErrorResponse.getEmail());
  }

  @Test
  public void loginWithNonExistingUsername() throws Exception {
    //login with non existing username:
    LoginErrorResponse loginErrorResponse = Methods.loginError("jovanovic.zeljko1@outlook.com","Test1234",404);
    //verify that error 'User not found' is displayed for 'email' field:
    AssertJUnit.assertEquals("Error message is not correct for 'email' field","User not found", loginErrorResponse.getEmail());
    //verify that there is no error for 'password' field:
    AssertJUnit.assertNull("Error message is displayed for 'password' field", loginErrorResponse.getPassword());
  }

  @Test
  public void loginWithUsernameInInvalidEmailFormat() throws Exception {
    //login with username in invalid email format:
    LoginErrorResponse loginErrorResponse = Methods.loginError("aaa@aaa","Test1234",400);
    //verify that error 'Email is invalid' is displayed for 'email' field:
    AssertJUnit.assertEquals("Error message is not correct for 'email' field","Email is invalid", loginErrorResponse.getEmail());
    //verify that there is no error for 'password' field:
    AssertJUnit.assertNull("Error message is displayed for 'password' field", loginErrorResponse.getPassword());
  }
}
