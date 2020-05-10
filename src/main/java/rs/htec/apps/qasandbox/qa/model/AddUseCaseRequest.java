package rs.htec.apps.qasandbox.qa.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AddUseCaseRequest {
  @SerializedName("title")
  private String title;
  @SerializedName("teststeps")
  private List<String> teststeps;
  @SerializedName("expected_result")
  private String expectedResult;
  @SerializedName("description")
  private String description;
  @SerializedName("automated")
  private Boolean automated;

  public AddUseCaseRequest(String title, List<String> teststeps, String expectedResult,
      String description, Boolean automated) {
    this.title = title;
    this.teststeps = teststeps;
    this.expectedResult = expectedResult;
    this.description = description;
    this.automated = automated;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getTeststeps() {
    return teststeps;
  }

  public void setTeststeps(List<String> teststeps) {
    this.teststeps = teststeps;
  }

  public String getExpectedResult() {
    return expectedResult;
  }

  public void setExpectedResult(String expectedResult) {
    this.expectedResult = expectedResult;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getAutomated() {
    return automated;
  }

  public void setAutomated(Boolean automated) {
    this.automated = automated;
  }
}
