package rs.htec.apps.qasandbox.http;

public enum HTTPMethod {
	POST("POST"),
	DELETE("DELETE"),
	PUT("PUT"),
	GET("GET");
	
	private String method;
	
	public String getValue() { 
		return method;
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
	
	HTTPMethod(String method_name) {
		this.method=method_name;
	}
}
