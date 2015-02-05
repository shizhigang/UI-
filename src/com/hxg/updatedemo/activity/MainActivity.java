package com.hxg.updatedemo.activity;

import java.io.File;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.hxg.updatedemo.params.Params;
import com.hxg.updatedemo.util.HttpUtil;
import com.hxg.updatedemo.util.JsonUtil;
import com.hxg.updatedemo.util.SPUtil;
import com.hxg.updatedemo.util.StreamUtil;

public class MainActivity extends Activity {

	private InputStream is;
	private Params params;
	private int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		count = SPUtil.getSp(getPreferences(MODE_PRIVATE));
		if(count >0 ){
			new Thread() {
				public void run() {
					is = HttpUtil.sendReq(getVersion());
					params = JsonUtil.parseJson(StreamUtil.stream2String(is));
					handler.sendEmptyMessage(1);
				};
			}.start();
		}

	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				if (params.getStatus().equals("true") && count > 0) {
					showDialog();
				}
			}else if(msg.what ==2){
				update();
			}
		};
	};

	private int getVersion() {
		// 获取packagemanager的实例
		PackageManager packageManager = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(), 0);
			int version = packInfo.versionCode;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private void showDialog() {
		new AlertDialog.Builder(MainActivity.this).setTitle("检测到有新版本需要更新吗？")
				.setPositiveButton("下载", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 下载
						count = 0;
						new Thread() {
							public void run() {
								HttpUtil.download(params.getPath());
								handler.sendEmptyMessage(2);
							};
						}.start();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						count--;
						SPUtil.saveSp(getPreferences(MODE_PRIVATE), count);
						if (count > 0) {
							showDialog();
						}
					}
				}).create().show();
	}

	public void update() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File("/sdcard/test.apk")),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}

}
