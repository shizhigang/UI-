package com.hxg.updatedemo.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtil {

	public static void saveSp(SharedPreferences sp,int count){
		Editor ed = sp.edit();
		ed.putInt("count", count);
		ed.commit();
	}
	
	public static int getSp(SharedPreferences sp){
		return sp.getInt("count", 3);
	}
}
