package com.example.coolweather.util;

import android.text.TextUtils;
import com.example.coolweather.model.City;
import com.example.coolweather.model.CoolWeatherDB;
import com.example.coolweather.model.County;
import com.example.coolweather.model.Province;

/**
 * 解析服务器返回的数据工具类
 * */
public class Utility {
	/**
	 * 	解析省级数据
	 * 	001|**,002|***
	 * */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response) {
		if(!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length>0) {
				for(String p:allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					coolWeatherDB.saveProvince(province);		//存进数据库
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 	解析市级的数据
	 * */
	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId) {
		if(!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if(allCities != null && allCities.length>0) {
				for (String city : allCities) {
					String[] array = city.split("\\|");
					City c = new City();
					c.setCityCode(array[0]);
					c.setCityName(array[1]);
					c.setProvinceId(provinceId);
					coolWeatherDB.saveCity(c);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 解析县级的数据
	 * */
	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId) {
		if(!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if(allCounties != null && allCounties.length>0) {
				for (String county : allCounties) {
					String[] array = county.split("\\|");
					County c = new County();
					c.setCityId(cityId);
					c.setCountyCode(array[0]);
					c.setCountyName(array[1]);
					coolWeatherDB.saveCounty(c);
				}
				return true;
			}
		}
		return false;
	}
}
