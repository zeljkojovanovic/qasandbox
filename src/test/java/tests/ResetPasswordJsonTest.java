package tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import rs.htec.apps.qasandbox.qa.model.ResetPasswordErrorResponse;
import rs.htec.apps.qasandbox.qa.model.ResetPasswordResponse;
import utils.Methods;

public class ResetPasswordJsonTest {

  private String username = "jovanovic.zeljko@outlook.com";

  @Test
  public void resetPassword() throws Exception {
    //reset password by entering existing email address:
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

}
