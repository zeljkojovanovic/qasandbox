package utils;

public enum  Host {

  QA("https://qa-sandbox.apps.htec.rs/api/");

  private String url;

  Host(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String generateUrl(ServiceMethod method) {
    StringBuilder host = new StringBuilder(this.url);
    return host.append(method.getMethod()).toString();
  }
}
