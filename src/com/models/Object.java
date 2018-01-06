package com.models;

public class Object {
	
	private String userName;
	private String locationName;

	public Object() {
		// TODO Auto-generated constructor stub
	}

	
	public Object(String userName, String locationName) {
		super();
		this.userName = userName;
		this.locationName = locationName;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	@Override
	public String toString() {
		return "Object [userName=" + userName + ", locationName=" + locationName + "]";
	}
	
}
