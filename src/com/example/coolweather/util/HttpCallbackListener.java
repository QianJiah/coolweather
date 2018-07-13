package com.example.coolweather.util;
/**服务器回调函数*/
public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
