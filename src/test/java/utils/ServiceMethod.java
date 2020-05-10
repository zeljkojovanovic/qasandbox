package utils;

public enum ServiceMethod {
  LOGIN("users/login"),
  RESET_PASSWORD("mailer/password-reset"),
  GET_USE_CASE("usecases/"),
  EDIT_USE_CASE("usecases/usecase/"),
  ALL_USE_CASES("usecases/all");

  private String method;

  ServiceMethod(String method) {
    this.method = method;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }
}
