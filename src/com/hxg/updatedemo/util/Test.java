package com.hxg.updatedemo.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase{

	
	public void test() throws MalformedURLException, IOException{
		InputStream is = HttpUtil.sendReq(1);
		String str = StreamUtil.stream2String(is);
		System.out.println("````````````````"+str);
	}
	
	public void testt(){
		HttpUtil.download("http://www.yasite.net/apk/locatecamera.apk");
	}
}
