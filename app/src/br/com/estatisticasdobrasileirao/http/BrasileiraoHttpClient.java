package br.com.estatisticasdobrasileirao.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BrasileiraoHttpClient {
	
	
	private static final String BASE_URL = "http://ec2-54-186-1-66.us-west-2.compute.amazonaws.com:9000/";

	  private static AsyncHttpClient client = new AsyncHttpClient();

	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.get(getAbsoluteUrl(url), params, responseHandler);
	      client.setMaxRetriesAndTimeout(10, 1000);
	  }

	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.post(getAbsoluteUrl(url), params, responseHandler);
	  }
	  
	  
	  public static String getSyncronized(String url){
	        InputStream inputStream = null;
	        String result = "";
	        try {
	            // create HttpClient
	            HttpClient httpclient = new DefaultHttpClient();
	            
	            HttpGet getRequest = new HttpGet(getAbsoluteUrl(convertURL(url)));
	            getRequest.addHeader("accept", "application/json");
	            
	            // make GET request to the given URL
	            HttpResponse httpResponse = httpclient.execute(getRequest);
	 
	            // receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	 
	            // convert inputstream to string
	            if(inputStream != null)
	                result = convertInputStreamToString(inputStream);
	            else
	                result = "Did not work!";
	 
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	 
	        return result;
	  }
	 
	  private static String convertInputStreamToString(InputStream inputStream) throws IOException{
	        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	        String line = "";
	        String result = "";
	        while((line = bufferedReader.readLine()) != null)
	            result += line;
	 
	        inputStream.close();
	        return result;
	 
	 }
  
	  
	  private static String getAbsoluteUrl(String relativeUrl) {
	      return BASE_URL + relativeUrl;
	  }
	  
	  
	  public static String convertURL(String str) {

		    String url = null;
		    try{
		    url = new String(str.trim().replace(" ", "%20").replace("&", "%26")
		            .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
		            .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
		            .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
		            .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
		            .replace(".", "%2E").replace(":", "%3A")
		            .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
		            .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
		            .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
		            .replace("|", "%7C").replace("}", "%7D"));//.replace("/", "%2F")
		    }catch(Exception e){
		        e.printStackTrace();
		    }
		    return url;
		}

}
