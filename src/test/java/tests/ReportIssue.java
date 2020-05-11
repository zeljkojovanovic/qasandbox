package tests;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rs.htec.apps.qasandbox.qa.model.LoginResponse;
import rs.htec.apps.qasandbox.qa.model.ReportIssueRequest;
import utils.Methods;
import utils.User;

public class ReportIssue {

  private String username;
  private String password;
  private String token;
  private String summary;
  private String type;
  private String severity;
  private String priority;
  private String description;
  private List<String> steps = new ArrayList<>();

  @BeforeClass
  public void init() throws Exception {
    username = User.USER_ZELJKO.getUsername();
    password = User.USER_ZELJKO.getPassword();
    LoginResponse loginResponse = Methods.login(username, password);
    token = loginResponse.getToken();
  }

  @Test
  public void reportAnIssue() throws Exception {
    //initialize fields required for reporting an issue:
    summary = "Automated summary";
    type = "Bug";
    severity = "High";
    priority = "Major";
    description = "Automated description";
    steps.add("Automated first step");
    steps.add("Automated second step");
    //set fields in request for reporting an issue:
    ReportIssueRequest rIR = new ReportIssueRequest(summary, type, severity, priority, description, steps);
    //report an issue:
    Methods.reportIssue(token, rIR);
  }

}
