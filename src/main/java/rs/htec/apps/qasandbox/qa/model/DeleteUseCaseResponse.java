package rs.htec.apps.qasandbox.qa.model;

import com.google.gson.annotations.SerializedName;

public class DeleteUseCaseResponse {

  @SerializedName("Success")
  private String sucess;

  public DeleteUseCaseResponse(String sucess) {
    this.sucess = sucess;
  }

  public String getSucess() {
    return sucess;
  }

  public void setSucess(String sucess) {
    this.sucess = sucess;
  }
}
