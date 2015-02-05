package com.hxg.updatedemo.params;

public class Params {

	private String status;
	private String path;

	public Params(String status, String path) {
		super();
		this.status = status;
		this.path = path;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Params [status=" + status + ", path=" + path + "]";
	}

}
