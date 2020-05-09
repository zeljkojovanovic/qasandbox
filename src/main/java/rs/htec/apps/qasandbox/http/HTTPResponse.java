package rs.htec.apps.qasandbox.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HTTPResponse {
	 public Integer code = null;
	 public String codeMessage = null;
	 public String body = null;
	 public String cache =  null;
	 public Map<String, String> headers = new HashMap<String, String>();
	  
	 public Integer getCode() {
		 return this.code;
	 }
	 
	 public String getCodeMessage() {
		 return this.codeMessage;
	 }
	 
	 public String getBody() { 
		 return this.body;
	 }
	 
	  public String getCache() {
		 return this.cache;
	 }
	 
	 public boolean isCached() {
		 return ((this.cache != null) && ((this.cache.toLowerCase().contains("no-cache")) || (this.cache.toLowerCase().contains("no-store"))));
	 }
	 
	 public String getHeader(String name) {
		 return this.headers.get(name);
	 }
	 
	 @Override
	 public String toString() {
		 StringBuilder response = new StringBuilder();
		 response.append("Code: " + this.code.toString()).append(" ").append(this.codeMessage);
		 response.append(" | Headers:: ");
		 for(Entry<String, String> entry:this.headers.entrySet()) response.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
		 response.append(" | Body:: ");
		 response.append(this.body);
		 return response.toString();
	 }

	 public String getResponseHeaders() {
		 StringBuilder headers = new StringBuilder();
		 for(Entry<String, String> entry:this.headers.entrySet()) 
			 headers.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
		 return headers.toString();
		 
	 }
	 
	 public String headersToString() {
		 StringBuilder headers = new StringBuilder();
		 headers.append(this.code.toString()).append(" ").append(this.codeMessage).append("\n");
		 for(Entry<String, String> entry:this.headers.entrySet()) headers.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
		 return headers.toString();
	 }
	 
	 public boolean hasHeader(String name, String value) {
		  for(Entry<String,String> entry:headers.entrySet()) 
			 if(entry.getKey().toLowerCase().equals(name.toLowerCase()) && entry.getValue().toLowerCase().equals(value.toLowerCase())) return true;
		 return false;
	 }
	 
	 
 }