package cn.edu.bit.sms.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient的工具类
 * @author zhengchong.wan
 *
 */
public class HttpClientUtil {
	
	private static PoolingHttpClientConnectionManager connectionPool;
	
	public static final String EMPTY_STR = "";
	
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	private static volatile boolean isInit = false;
	
	public static void init() {
		if (isInit) return;
		connectionPool = new PoolingHttpClientConnectionManager();
		connectionPool.setMaxTotal(50);//整个连接池最大连接数
		connectionPool.setDefaultMaxPerRoute(5);//每路由最大连接数，默认值是2
		isInit = true;
	}
	
	public static void close() {
		if (connectionPool != null) connectionPool.close();
	}
	
	private static CloseableHttpClient getHttpClient(){
		return HttpClients.custom().setConnectionManager(connectionPool).build();
	}
	
	public static String httpGetRequest(String url){
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet);
	}
	
	public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException{
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
	}
	
	public static String httpGetRequest(String url, Map<String, Object> headers, 
			Map<String, Object> params) throws URISyntaxException{
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);
		
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        
        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param: headers.entrySet()) {
			httpGet.addHeader(param.getKey(), param.getValue().toString());
		}
        return getResult(httpGet);
	}
	
	public static String httpPostRequest(String url){
		HttpPost httpPost = new HttpPost(url);
		return getResult(httpPost);
	}
	
	public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException{
		HttpPost httpPost = new HttpPost(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, DEFAULT_CHARSET));
		return getResult(httpPost);
	}
	
	public static String httpPostRequest(String url, Map<String, Object> headers, 
			Map<String, Object> params) throws UnsupportedEncodingException{
		HttpPost httpPost = new HttpPost(url);
		
		for (Map.Entry<String, Object> param: headers.entrySet()) {
			httpPost.addHeader(param.getKey(), param.getValue().toString());
		}
		
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, DEFAULT_CHARSET));
        
		return getResult(httpPost);
	}
	
	private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params){
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param: params.entrySet()) {
        	if (param.getValue() == null) continue;
        	if (param.getValue() instanceof String) {
        		pairs.add(new BasicNameValuePair(param.getKey(), (String) param.getValue()));
        	} else if (param.getValue() instanceof Collection<?>) {
        		Collection<?> collection = (Collection<?>) param.getValue();
        		if (CollectionUtil.isEmpty(collection)) continue;
        		String strValue = collection.toString();
        		pairs.add(new BasicNameValuePair(param.getKey(), strValue.substring(1, strValue.length() - 1)));
        	} else {
        		pairs.add(new BasicNameValuePair(param.getKey(), param.getValue().toString()));
        	}
		}
        return pairs;
	}
	
	
	/**
	 * 处理HTTP请求
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request) {
		CloseableHttpClient httpClient = getHttpClient();
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) return EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					response = null;
				}
			}
		}
		return EMPTY_STR;
	}
	
}