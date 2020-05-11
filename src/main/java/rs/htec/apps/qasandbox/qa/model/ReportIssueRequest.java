package rs.htec.apps.qasandbox.qa.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ReportIssueRequest {

  @SerializedName("summary")
  private String summary;
  @SerializedName("type")
  private String type;
  @SerializedName("severity")
  private String severity;
  @SerializedName("priority")
  private String priority;
  @SerializedName("description")
  private String description;
  @SerializedName("steps")
  private List<String> steps;


  public ReportIssueRequest() {
  }

  public ReportIssueRequest(String summary, String type, String severity, String priority,
      String description, List<String> steps) {
    this.summary = summary;
    this.type = type;
    this.severity = severity;
    this.priority = priority;
    this.description = description;
    this.steps = steps;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getSteps() {
    return steps;
  }

  public void setSteps(List<String> steps) {
    this.steps = steps;
  }
}
