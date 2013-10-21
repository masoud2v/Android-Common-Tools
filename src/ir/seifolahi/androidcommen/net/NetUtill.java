package ir.seifolahi.androidcommen.net;

import ir.seifolahi.androidcommen.debug.Logg;

import java.util.ArrayList;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NetUtill {

	public static final String USER_AGENT = "android-commons";


	private static AsyncHttpClient getNetClient() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.setUserAgent(USER_AGENT);
		return client;
	}

	private static RequestParams convertParams(ArrayList<BasicNameValuePair> inputParams) {

		RequestParams convertedParams = new RequestParams();

		if (inputParams == null)
			return convertedParams;

		for (BasicNameValuePair param : inputParams) {
			convertedParams.put(param.getName(), param.getValue());
		}

		return convertedParams;
	}

	public static void GET(String url, AsyncHttpResponseHandler handler) {
		GET(url, null, handler);
	}

	public static void GET(String url, ArrayList<BasicNameValuePair> inputParams, AsyncHttpResponseHandler handler) {

		if (inputParams == null)
			inputParams = new ArrayList<BasicNameValuePair>();
		
		for (BasicNameValuePair basicNameValuePair : inputParams) {
			Logg.i(" get params " + basicNameValuePair.getName() + "   " + basicNameValuePair.getValue());
		}

		getNetClient().get(url, convertParams(inputParams), handler);
	}

	public static void POST(String url, AsyncHttpResponseHandler handler) {
		POST(url, null, null, handler);
	}

	public static void POST(String url, ArrayList<BasicNameValuePair> postParams, AsyncHttpResponseHandler handler) {
		POST(url, postParams, null, handler);
	}

	public static void POST(String url, ArrayList<BasicNameValuePair> postParams, ArrayList<BasicNameValuePair> urlParams, AsyncHttpResponseHandler handler) {

		if (postParams == null)
			postParams = new ArrayList<BasicNameValuePair>();
		
		if (urlParams == null)
			urlParams = new ArrayList<BasicNameValuePair>();

		for (BasicNameValuePair basicNameValuePair : urlParams) {
			Logg.i(" get params " + basicNameValuePair.getName() + "   " + basicNameValuePair.getValue());
		}

		for (BasicNameValuePair basicNameValuePair : postParams) {
			Logg.i(" post params " + basicNameValuePair.getName() + "   " + basicNameValuePair.getValue());
		}

		
		
		if(urlParams.size()>0)
			url = url + "&" + URLEncodedUtils.format(urlParams, "utf-8");
		
		getNetClient().post(url, convertParams(postParams), handler);

	}

}
