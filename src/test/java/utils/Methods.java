package utils;

import static rs.htec.apps.qasandbox.map.Mapper.mapFromJSON;

import rs.htec.apps.qasandbox.qa.model.LoginErrorResponse;
import rs.htec.apps.qasandbox.qa.model.LoginResponse;
import rs.htec.apps.qasandbox.http.HTTPMethod;
import rs.htec.apps.qasandbox.http.HTTPRequest;
import rs.htec.apps.qasandbox.http.HTTPResponse;
import rs.htec.apps.qasandbox.qa.model.ResetPasswordErrorResponse;
import rs.htec.apps.qasandbox.qa.model.ResetPasswordResponse;

public class Methods {

  public static LoginResponse login(String username, String password) throws Exception {
    String url = "https://qa-sandbox.apps.htec.rs/api/users/login";
    String body = "{\"email\": \"" + username + "\", \"password\": \"" + password + "\"}";
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.POST).setBody(body).setContentType("application/json");
    HTTPResponse response = request.sendRequest();
    if (!response.code.equals(200))
      throw new Exception("Login failed. Code: " + response.getCode() + " | Message: " + response.getBody());
    return mapFromJSON(response.body, LoginResponse.class);
  }

  public static LoginErrorResponse loginError(String username, String password, int errorCode) throws Exception {
    String url = "https://qa-sandbox.apps.htec.rs/api/users/login";
    String body = "{\"email\": \"" + username + "\", \"password\": \"" + password + "\"}";
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.POST).setBody(body).setContentType("application/json");
    HTTPResponse response = request.sendRequest();
    if (!response.code.equals(errorCode))
      throw new Exception("Login did not return " + errorCode + " code. Code: " + response.getCode() + " | Message: " + response.getBody());
    return mapFromJSON(response.body, LoginErrorResponse.class);
  }

  public static ResetPasswordResponse resetPassword(String username) throws Exception {
    String url = "https://qa-sandbox.apps.htec.rs/api/mailer/password-reset";
    String body = "{\"email\": \"" + username + "\"}";
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.POST).setBody(body).setContentType("application/json");
    HTTPResponse response = request.sendRequest();
    if (!response.code.equals(200))
      throw new Exception("Reset password failed. Code: " + response.getCode() + " | Message: " + response.getBody());
    return mapFromJSON(response.body, ResetPasswordResponse.class);
  }

  public static ResetPasswordErrorResponse resetPasswordError(String username) throws Exception {
    String url = "https://qa-sandbox.apps.htec.rs/api/mailer/password-reset";
    String body = "{\"email\": \"" + username + "\"}";
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.POST).setBody(body).setContentType("application/json");
    HTTPResponse response = request.sendRequest();
    if (!response.code.equals(400))
      throw new Exception("Reset password did not return '400'. Code: " + response.getCode() + " | Message: " + response.getBody());
    return mapFromJSON(response.body, ResetPasswordErrorResponse.class);
  }

}
