package utils;

import static rs.htec.apps.qasandbox.map.Mapper.mapFromJSON;
import static rs.htec.apps.qasandbox.map.Mapper.mapFromJsonArray;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import rs.htec.apps.qasandbox.qa.model.AllUseCasesResponse;
import rs.htec.apps.qasandbox.qa.model.DeleteUseCaseResponse;
import rs.htec.apps.qasandbox.qa.model.GetUseCaseResponse;
import rs.htec.apps.qasandbox.qa.model.LoginErrorResponse;
import rs.htec.apps.qasandbox.qa.model.LoginResponse;
import rs.htec.apps.qasandbox.http.HTTPMethod;
import rs.htec.apps.qasandbox.http.HTTPRequest;
import rs.htec.apps.qasandbox.http.HTTPResponse;
import rs.htec.apps.qasandbox.qa.model.ReportIssueRequest;
import rs.htec.apps.qasandbox.qa.model.ResetPasswordErrorResponse;
import rs.htec.apps.qasandbox.qa.model.ResetPasswordResponse;
import rs.htec.apps.qasandbox.qa.model.AddUseCaseRequest;
import rs.htec.apps.qasandbox.qa.model.EditUseCaseResponse;

public class Methods {

  private static Host host = Host.QA;

  public static LoginResponse login(String username, String password) throws Exception {
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.LOGIN);
    //set body of the request:
    String body = "{\"email\": \"" + username + "\", \"password\": \"" + password + "\"}";
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.POST).setBody(body).setContentType("application/json");
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(200))
      throw new Exception("Login failed. Code: " + response.getCode() + " | Message: " + response.getBody());
    //map and return the response:
    return mapFromJSON(response.body, LoginResponse.class);
  }

  public static LoginErrorResponse loginError(String username, String password, int errorCode) throws Exception {
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.LOGIN);
    //set body of the request:
    String body = "{\"email\": \"" + username + "\", \"password\": \"" + password + "\"}";
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.POST).setBody(body).setContentType("application/json");
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(errorCode))
      throw new Exception("Login did not return " + errorCode + " code. Code: " + response.getCode() + " | Message: " + response.getBody());
    //map and return the response:
    return mapFromJSON(response.body, LoginErrorResponse.class);
  }

  public static ResetPasswordResponse resetPassword(String username) throws Exception {
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.RESET_PASSWORD);
    //set body of the request:
    String body = "{\"email\": \"" + username + "\"}";
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.POST).setBody(body).setContentType("application/json");
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(200))
      throw new Exception("Reset password failed. Code: " + response.getCode() + " | Message: " + response.getBody());
    //map and return the response:
    return mapFromJSON(response.body, ResetPasswordResponse.class);
  }

  public static ResetPasswordErrorResponse resetPasswordError(String username) throws Exception {
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.RESET_PASSWORD);
    //set body of the request:
    String body = "{\"email\": \"" + username + "\"}";
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.POST).setBody(body).setContentType("application/json");
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(400))
      throw new Exception("Reset password did not return '400'. Code: " + response.getCode() + " | Message: " + response.getBody());
    //map and return the response:
    return mapFromJSON(response.body, ResetPasswordErrorResponse.class);
  }

  public static EditUseCaseResponse addUseCase(AddUseCaseRequest addUseCaseRequest, String token) throws Exception {
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.EDIT_USE_CASE);
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.POST).setBody(new Gson().toJson(addUseCaseRequest)).setContentType("application/json").setBearerAuthorization(token);
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(200))
      throw new Exception("Creating use case failed. Code: " + response.getCode() + " | Message: " + response.getBody());
    //map and return the response:
    return mapFromJSON(response.body, EditUseCaseResponse.class);
  }

  public static List<AllUseCasesResponse> retrieveAllUseCases(String token) throws Exception {
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.ALL_USE_CASES);
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.GET).setContentType("application/json").setBearerAuthorization(token);
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(200))
      throw new Exception("Retrieving use cases failed. Code: " + response.getCode() + " | Message: " + response.getBody());
    //map and return the response:
    return mapFromJsonArray(response.body,AllUseCasesResponse[].class);
  }

  public static void verifyThatAllUseCasesAreDeleted(String token) throws Exception {
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.ALL_USE_CASES);
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.GET).setContentType("application/json").setBearerAuthorization(token);
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(204))
      throw new Exception("Not all use cases are deleted. Code: " + response.getCode() + " | Message: " + response.getBody());
  }

  public static DeleteUseCaseResponse deleteUseCase(int useCaseId, String token) throws Exception {
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.EDIT_USE_CASE);
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url + useCaseId).setMethod(HTTPMethod.DELETE).setContentType("application/json").setBearerAuthorization(token);
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(200))
      throw new Exception("Delete use case failed. Code: " + response.getCode() + " | Message: " + response.getBody());
    //map and return the response:
    return mapFromJSON(response.body, DeleteUseCaseResponse.class);
  }

  public static GetUseCaseResponse getUseCase (String token, int useCaseId) throws Exception {
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.GET_USE_CASE);
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url + useCaseId).setMethod(HTTPMethod.GET).setContentType("application/json").setBearerAuthorization(token);
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(200))
      throw new Exception("Get of use case failed. Code: " + response.getCode() + " | Message: " + response.getBody());
    //map and return the response:
    return mapFromJSON(response.body, GetUseCaseResponse.class);
  }

  public static EditUseCaseResponse editEveryPropertyInUseCase(String token, int useCaseId, GetUseCaseResponse useCase, String editString) throws Exception{
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.EDIT_USE_CASE);
    //set body of the request:
    GetUseCaseResponse useCaseForEditing = new GetUseCaseResponse();
    useCaseForEditing.setAutomated(true);
    useCaseForEditing.setTitle(useCase.getTitle() + editString);
    useCaseForEditing.setExpectedResult(useCase.getExpectedResult() + editString);
    useCaseForEditing.setDescription(useCase.getDescription() + editString);
    useCaseForEditing.setTeststeps(new ArrayList<>());
    for(int i = 0; i < useCase.getTeststeps().size(); i++) {
      useCaseForEditing.getTeststeps().add(i, useCase.getTeststeps().get(i).concat(editString));
    }
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url + useCaseId).setMethod(HTTPMethod.PUT).setBody(new Gson().toJson(useCaseForEditing)).setContentType("application/json").setBearerAuthorization(token);
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(200))
      throw new Exception("Editing of use case failed. Code: " + response.getCode() + " | Message: " + response.getBody());
    //map and return the response:
    return mapFromJSON(response.body, EditUseCaseResponse.class);
  }

  public static void reportIssue(String token, ReportIssueRequest reportIssueRequest) throws Exception {
    //set url of the request:
    String url = host.generateUrl(ServiceMethod.ADD_REPORT);
    //set request parameters and send request:
    HTTPRequest request = new HTTPRequest(url).setMethod(HTTPMethod.POST).setBody(new Gson().toJson(reportIssueRequest)).setContentType("application/json").setBearerAuthorization(token);
    HTTPResponse response = request.sendRequest();
    //verify that correct response code is received:
    if (!response.code.equals(200)) throw new Exception("Reporting issue failed. Code: " + response.getCode() + " | Message: " + response.getBody());
  }
}
