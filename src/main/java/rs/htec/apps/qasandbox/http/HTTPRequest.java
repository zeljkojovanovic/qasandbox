package rs.htec.apps.qasandbox.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class HTTPRequest {
	
	private String url = "";
	private String method = HTTPMethod.GET.getValue();
	private String body = "";
	private String charset = "UTF-8";
	private Map<String,String> headers = new HashMap<String, String>();
	
		private class HttpDeleteWithBody extends HttpPost {
			
			public HttpDeleteWithBody(String url) {
		        super(url);
		    }
		    
			@Override
		    public String getMethod() {
		        return "DELETE";
		    }
		}
	
	public HTTPRequest(String url)  {
		this.url = url;
	}

	public HTTPRequest()  { }
	
	public HTTPRequest setUrl(String url) {
		this.url = url;
		return this;
	}
	
	public HTTPRequest(URL url)  {
		this.url = url.toString();
	}
	
	public HTTPRequest setMethod(HTTPMethod method) {
		this.method = method.getValue();
		return this;
	}
	
	public HTTPRequest setRequestHeader(String name, String value) {
		this.headers.put(name, value);
		return this;
	}
	
	public HTTPRequest setBody(String body) {
		this.body = body;
		return this;
	}
	
	public HTTPRequest setCharset(String charset) {
		this.charset = charset;
		return this;
	}
	
	public HTTPRequest setAccept(String value) {
		this.headers.put("Accept", value);
		return this;
	}
	
	public HTTPRequest setContentType(String value) {
		this.headers.put("Content-Type", value + ";charset=" + this.charset);
		return this;
	}
	
	public HTTPRequest setContentType(String value, String chset) {
		this.headers.put("Content-Type", value + ";charset=" + chset);
		this.charset = chset;
		return this;
	}
	
	public HTTPRequest setBearerAuthorization(String value) {
		this.setRequestHeader("Authorization", "Bearer " + value);
		return this;
	}
	
	public HTTPRequest setBasicAuthorization(String value) {
		this.setRequestHeader("Authorization", "Basic " + value);
		return this;
	}
	
	public HTTPRequest setAuthorization(String type, String value) {
		this.setRequestHeader("Authorization", type + " " + value);
		return this;
	}
	
	public HTTPRequest setAcceptLanguage(String value) {
		this.setRequestHeader("Accept-Language", value);
		return this;
	}
	
	private void addHeaderFields(HttpUriRequest request) {
		Set<String> keys = this.headers.keySet();
		for(String key:keys) request.setHeader(key, this.headers.get(key));
    }

	public String getBody() {
		return body;
	}
	
	private HTTPResponse getHTTPResponse(HttpResponse res) throws Exception {
		HTTPResponse response = new HTTPResponse();
		response.code =  res.getStatusLine().getStatusCode();
		response.codeMessage = res.getStatusLine().getReasonPhrase();
		//StringBuffer response_body = new StringBuffer();
		String body = "";
		if(res.getEntity()!= null) {
			InputStreamReader isr = new InputStreamReader(res.getEntity().getContent(), Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);
			// Next two lines should be replaced with third line
			//String line = "";
			//while((line = br.readLine()) != null) response_body.append(line);
			body = br.lines().collect(Collectors.joining("\n"));
		}
		response.body = body;
		//response.body = response_body.toString();
		Header caching = res.getFirstHeader("Cache-Control");
		if(caching != null) response.cache = caching.getValue();
		for(Header header:res.getAllHeaders()) response.headers.put(header.getName(), header.getValue());
		return response;
	}
	
	public HTTPResponse sendRequest() throws Exception {
		HttpClient client = HttpClientBuilder.create()
				.setDefaultRequestConfig(RequestConfig.custom()
						.setCookieSpec(CookieSpecs.STANDARD).build()).build(); //HttpClientBuilder.create().build();
		HttpResponse res = null;
					
				if(this.method.equals(HTTPMethod.GET.getValue())) {
					HttpGet request = new HttpGet(this.url);
					this.addHeaderFields(request);
					res = client.execute(request);
				}
				
				if(this.method.equals(HTTPMethod.POST.getValue())) {
					HttpPost request = new HttpPost(this.url);
					this.addHeaderFields(request);
					request.setEntity(new ByteArrayEntity(body.getBytes(Charset.forName(this.charset))));
					res = client.execute(request);
				}
		
				if(this.method.equals(HTTPMethod.PUT.getValue())) {
					HttpPut request = new HttpPut(this.url);
					this.addHeaderFields(request);
					request.setEntity(new ByteArrayEntity(body.getBytes(Charset.forName(this.charset))));
					res = client.execute(request);
				}
				
				if(this.method.equals(HTTPMethod.DELETE.getValue())) {
					HttpDeleteWithBody request = new HttpDeleteWithBody(this.url);
					this.addHeaderFields(request);
					request.setEntity(new ByteArrayEntity(body.getBytes(Charset.forName(this.charset))));
					res = client.execute(request);
				}
	return this.getHTTPResponse(res);
	}
	
	public HTTPResponse getResponse() throws Exception {
		return this.sendRequest();
	}
}
