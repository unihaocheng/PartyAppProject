package com.wy.util;

import android.os.Environment;


public class Constants {
	/**
	 ******************************************* 参数设置信息******************************************
	 */

	// 应用名称
	public static String APP_NAME = "";

	// 保存参数文件夹名�??
	public static final String SHARED_PREFERENCE_NAME = "queue_prefs";

	// SDCard路径
	public static final String SD_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	// 图片存储路径
	public static final String BASE_PATH = SD_PATH + "/shopqueue/";

	// 缓存图片路径
	public static final String BASE_IMAGE_CACHE = BASE_PATH + "cache/images/";
	
	/**
	 ******************************************* 参数设置信息结束 ******************************************
	 */
	

	public static final int MESSAGE_ID_CHECK_UPDATE_NEED_UPDATE = 1;
	public static final int MESSAGE_ID_DOWNLOAD_APK_SUCCESS = 2;
	public static final int MESSAGE_ID_DOWNLOAD_APK_FAILED = 3;
	public static final int MESSAGE_ID_DOWNLOAD_APK_LOADING = 4;
}
