package com.wy.util;


import java.io.IOException;




import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	//

//	public static final String BASE_URL="http://192.168.18.102:8080/Party_Server/";
//	public static final String BASE_URL="http://192.168.251.52:8080/Party_Server/";
	public static final String BASE_URL="http://10.164.32.60:8080/Party_Server/";
	public static HttpGet getHttpGet(String url){
		HttpGet request = new HttpGet(url);
		 return request;
	}
	// 获得Post请求对象request
	public static HttpPost getHttpPost(String url){
		 HttpPost request = new HttpPost(url);
		 return request;
	}
	// 根据请求获得响应对象response
	public static HttpResponse getHttpResponse(HttpGet request) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	// 根据请求获得响应对象response
	public static HttpResponse getHttpResponse(HttpPost request) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	
	// 发�?�Post请求，获得响应查询结�?
	public static String queryStringForPost(String url){
		// 根据url获得HttpPost对象
		HttpPost request = HttpUtil.getHttpPost(url);
		
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if(response.getStatusLine().getStatusCode()==200){
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "error";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "error";
			return result;
		}
        return null;
    }
	
	public static String queryStringForPost(String url,HttpParams params){
		// 根据url获得HttpPost对象
		HttpPost request = HttpUtil.getHttpPost(url);
		request.setParams(params);
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if(response.getStatusLine().getStatusCode()==200){
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "error";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "error";
			return result;
		}
        return null;
    }
	
	
	// 获得响应查询结果
	public static String queryStringForPost(HttpPost request){
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if(response.getStatusLine().getStatusCode()==200){
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "error";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "error";
			return result;
		}
        return null;
    }
	// 发�?�Get请求，获得响应查询结�?
	public static  String queryStringForGet(String url){
		// 获得HttpGet对象
		HttpGet request = HttpUtil.getHttpGet(url);
		request.addHeader("Content-Type", "text/html"); //这行很重
		request.addHeader("charset", "utf-8");         //这行很重
		String result = null;
		try {
			// 获得响应对象
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判断是否请求成功
			if(response.getStatusLine().getStatusCode()==200){
				// 获得响应
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "error";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "error";
			return result;
		}
        return null;
    }
}
