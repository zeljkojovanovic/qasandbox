package rs.htec.apps.qasandbox.qa.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GetUseCaseResponse {

  @SerializedName("user_id")
  private int userId;
  @SerializedName("usecase_id")
  private int useCaseId;
  @SerializedName("automated")
  private boolean automated;
  @SerializedName("title")
  private String title;
  @SerializedName("expected_result")
  private String expectedResult;
  @SerializedName("description")
  private String description;
  @SerializedName("teststeps")
  private List<String> teststeps;

  public GetUseCaseResponse() {
  }

  public GetUseCaseResponse(int userId, int useCaseId, boolean automated, String title, String expectedResult, String description, List<String> teststeps) {
    this.userId = userId;
    this.useCaseId = useCaseId;
    this.automated = automated;
    this.title = title;
    this.expectedResult = expectedResult;
    this.description = description;
    this.teststeps = teststeps;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getUseCaseId() {
    return useCaseId;
  }

  public void setUseCaseId(int useCaseId) {
    this.useCaseId = useCaseId;
  }

  public boolean isAutomated() {
    return automated;
  }

  public void setAutomated(boolean automated) {
    this.automated = automated;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public List<String> getTeststeps() {
    return teststeps;
  }

  public void setTeststeps(List<String> teststeps) {
    this.teststeps = teststeps;
  }
}
