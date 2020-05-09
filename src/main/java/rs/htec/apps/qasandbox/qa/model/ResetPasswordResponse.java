package rs.htec.apps.qasandbox.qa.model;

import com.google.gson.annotations.SerializedName;

public class ResetPasswordResponse {

  @SerializedName("success")
  private String success;

  public ResetPasswordResponse(String success) {
    this.success = success;
  }

  public String getSuccess() {
    return success;
  }

  public void setSuccess(String success) {
    this.success = success;
  }
}
