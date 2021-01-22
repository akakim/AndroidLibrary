package com.akakim.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;


/**
 * Androidx에서 SharedPreferenceManager 클래스를 이용한다.
 */

public class SharedPrefUtil {

	public static int DEFAULT_INT = 0;
	public static String DEFAULT_STR = "";


	public static SharedPreferences.OnSharedPreferenceChangeListener  listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

		}
	};

	public static SharedPreferences.Editor getSharedPref(Context context){
		SharedPreferences share = context.getSharedPreferences("sdp", Context.MODE_PRIVATE);

		SharedPreferences.Editor edit = share.edit();

		return edit;
	}

	@RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
	public static void clearAll(Context context){
		SharedPreferences share = context.getSharedPreferences("sdp", Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = share.edit();
		edit.clear();
		edit.apply();
	}

	public static int getInteger(Context context, String key){
		SharedPreferences share = context.getSharedPreferences("sdp", Context.MODE_PRIVATE);

		return share.getInt( key ,DEFAULT_INT);
	}

	public static Long getLong(Context context, String key){
		SharedPreferences share = context.getSharedPreferences("sdp", Context.MODE_PRIVATE);
		return share.getLong( key ,DEFAULT_INT);
	}


	public static String getString(Context context, String key){
		SharedPreferences share = context.getSharedPreferences("sdp", Context.MODE_PRIVATE);
		return share.getString( key ,DEFAULT_STR);
	}

}
