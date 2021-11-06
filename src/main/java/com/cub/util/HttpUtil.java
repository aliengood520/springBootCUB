package com.cub.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {

	public static String getUrlData(String urlStr, String CodeStr) throws Exception {
		String urlString = "";
		URL url = new URL(urlStr);
		URLConnection urlConnection = url.openConnection();
		HttpURLConnection connection = null;
		if (urlConnection instanceof HttpURLConnection) {
			connection = (HttpURLConnection) urlConnection;
		} else {
			throw new Exception("請輸入URL");
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), CodeStr));
		String current;
		while ((current = in.readLine()) != null) {
			urlString += current;
		}
		return urlString;
	}
}
