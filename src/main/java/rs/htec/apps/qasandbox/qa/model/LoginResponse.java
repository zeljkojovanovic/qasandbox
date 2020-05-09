package rs.htec.apps.qasandbox.qa.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

  @SerializedName("success")
  private boolean success;
  @SerializedName("token")
  private String token;
  @SerializedName("refreshToken")
  private String refreshToken;

  public LoginResponse(boolean success, String token, String refreshToken) {
    this.success = success;
    this.token = token;
    this.refreshToken = refreshToken;
  }

  public boolean getSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
