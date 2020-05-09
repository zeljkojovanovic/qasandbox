package tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import rs.htec.apps.qasandbox.qa.model.ResetPasswordErrorResponse;
import rs.htec.apps.qasandbox.qa.model.ResetPasswordResponse;
import utils.Methods;

public class ResetPasswordJsonTest {

  @Test
  public void resetPassword() throws Exception {
    //reset password by entering existing email address:
    String username = "jovanovic.zeljko@outlook.com";
    ResetPasswordResponse resetPasswordResponse = Methods.resetPassword(username);
    //verify that 'success' field has 'Email successfully sent' value:
    AssertJUnit.assertEquals("'success' field does not have correct message","Email successfully sent", resetPasswordResponse.getSuccess());
  }

  @Test
  public void resetPasswordWithoutAnEmail() throws Exception {
    //reset password without entering an email address:
    ResetPasswordErrorResponse resetPasswordErrorResponse = Methods.resetPasswordError("");
    //verify that error 'Email field is required' is displayed for 'email' field:
    AssertJUnit.assertEquals("Error message is not correct for 'email' field","Email field is required", resetPasswordErrorResponse.getEmail());
  }

  @Test (enabled =  false) //this test is disabled as there is bug present for this case
  public void resetPasswordForNonExistingUser() throws Exception {
    //reset password without entering an email address:
    ResetPasswordErrorResponse resetPasswordErrorResponse = Methods.resetPasswordError("jovanovic.zeljko1@outlook.com");
    //verify that error is displayed for 'email' field:
    AssertJUnit.assertNotNull("Error message is displayed for 'email' field", resetPasswordErrorResponse.getEmail());
  }

  @Test
  public void resetPasswordForUsernameThatIsInInvalidEmailFormat() throws Exception {
    //reset password for username that is in invalid email format:
    ResetPasswordErrorResponse resetPasswordErrorResponse = Methods.resetPasswordError("aaa@aaa");
    //verify that error 'Email is invalid' is displayed for 'email' field:
    AssertJUnit.assertEquals("Error message is not correct for 'email' field","Email is invalid", resetPasswordErrorResponse.getEmail());
  }

}
