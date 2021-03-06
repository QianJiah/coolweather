package com.example.coolweather.util;
/**
 * 连接服务器并将结果返回
 * */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtil {
	public static void sendHttpRequest(final String address,final HttpCallbackListener listener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpsURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpsURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuffer buffer = new StringBuffer();
					String line;
					while((line = reader.readLine()) != null) {
						buffer.append(line);
					}
					if(listener != null) {
						//回调onFinish();
						listener.onFinish(buffer.toString());
					}
				} catch (Exception e) {
					if(listener != null) {
						listener.onError(e);
					}
				}finally {
					if(connection != null) {
						connection.disconnect();
					}
				}
				
			}
		}).start();
	}
}
