package com.hxg.updatedemo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

	private static final String PATH = "http://www.yasite.net/api/checkVersion.php";
	private static final int TIMEOUT = 5000;
	public static InputStream sendReq(int versionCode){
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(PATH+"?versionCode="+versionCode).openConnection();
			conn.setConnectTimeout(TIMEOUT);
			conn.setReadTimeout(TIMEOUT);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() == 200){
				return conn.getInputStream();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void download(String url){
		InputStream is = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setConnectTimeout(TIMEOUT);
			conn.setReadTimeout(TIMEOUT);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() == 200){
				is = conn.getInputStream();
				saveFile(is, "test.apk");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void saveFile(InputStream is,String name){
		File file = new File("/mnt/sdcard/"+name);
		byte[] buffer = new byte[10240];
		int len = 0;
		try {
			OutputStream os = new FileOutputStream(file);
			while((len = is.read(buffer))!=-1){
				os.write(buffer,0,len);
			}
			os.flush();
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
