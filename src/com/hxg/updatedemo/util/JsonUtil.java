package com.hxg.updatedemo.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.hxg.updatedemo.params.Params;

public class JsonUtil {

	public static Params parseJson(String data){
		JSONObject object;
		try {
			object = new JSONObject(data);
			String status = object.getString("status");
			String path = object.getString("path");
			Params params = new Params(status, path);
			return params;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
