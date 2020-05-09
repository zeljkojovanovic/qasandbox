package rs.htec.apps.qasandbox.qa.model;

import com.google.gson.annotations.SerializedName;

public class AllUseCasesResponse {

  @SerializedName("usercase_id")
  private int usecaseId;
  @SerializedName("user_id")
  private int userId;
  @SerializedName("title")
  private String title;
  @SerializedName("expected_result")
  private String expectedResult;
  @SerializedName("description")
  private String description;
  @SerializedName("automated")
  private boolean automated;

  public AllUseCasesResponse(int usecaseId, int userId, String title, String expectedResult,
      String description, boolean automated) {
    this.usecaseId = usecaseId;
    this.userId = userId;
    this.title = title;
    this.expectedResult = expectedResult;
    this.description = description;
    this.automated = automated;
  }

  public int getUsecaseId() {
    return usecaseId;
  }

  public void setUsecaseId(int usecaseId) {
    this.usecaseId = usecaseId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
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

  public boolean isAutomated() {
    return automated;
  }

  public void setAutomated(boolean automated) {
    this.automated = automated;
  }
}
