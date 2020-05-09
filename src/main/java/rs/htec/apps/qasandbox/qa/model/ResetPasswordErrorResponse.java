package rs.htec.apps.qasandbox.qa.model;

import com.google.gson.annotations.SerializedName;

public class ResetPasswordErrorResponse {

  @SerializedName("email")
  private String email;

  public ResetPasswordErrorResponse(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
