package com.boreas.plainlife.Location;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.boreas.plainlife.Constant;


public class GaoDeLocationClient {
	private AMapLocationClient client = null;
	private AMapLocationClientOption mOption;
	private Object objLock = new Object();
	private int updateLocationTime = 5 * 1000;
	/***
	 *
	 * @param locationContext
	 */
	public GaoDeLocationClient(Context locationContext){
		synchronized (objLock) {
			if(client == null){
				client = new AMapLocationClient(locationContext);
				client.setLocationOption(getDefaultLocationClientOption());
			}
		}
	}
	
	/***
	 * 
	 * @param listener
	 * @return
	 */
	
	public boolean registerListener(AMapLocationListener listener){
		boolean isSuccess = false;
		if(listener != null){
			client.setLocationListener(listener);
			isSuccess = true;
		}
		return  isSuccess;
	}
	
	public void unregisterListener(AMapLocationListener listener){
		if(listener != null){
			client.unRegisterLocationListener(listener);
		}
	}

	private AMapLocationClientOption getDefaultLocationClientOption(){
		if(mOption == null){
			mOption = new AMapLocationClientOption();
			mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
			mOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);//设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
			mOption.setInterval(Constant.location_time);//设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
		    mOption.setNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
		    mOption.setMockEnable(false);//设置是否允许模拟软件Mock位置结果，多为模拟GPS定位结果，默认为true，允许模拟位置。
			mOption.setHttpTimeOut(20000);//设置定位请求超时时间，默认为30秒。
			mOption.setLocationCacheEnable(false);//设置是否开启定位缓存机制
			mOption.setOnceLocation(false);
		}
		return mOption;
	}


	public void start(){
		synchronized (objLock) {
			if(client != null && !client.isStarted()){
				client.startLocation();
			}
		}
	}
	public void stop(){
		synchronized (objLock) {
			if(client != null && client.isStarted()){
				client.stopLocation();
			}
		}
	}

	public boolean isStart() {
		return client.isStarted();
	}
	
}
