package com.pojo;

public class Attendee {
	
	private static final long serialVersionUID = 5088863992478607917L;
	
	UserLink user;
	String attendanceType;


	public Attendee(){


	}
	public Attendee(UserLink user, String attendanceType) {


		this.user = user;
		this.attendanceType = attendanceType;


	}
	public UserLink getUser(){

		return user;
	}

	public void setUser(UserLink user){

		user = this.user;

	}

	public String getAttendanceType(){

		return attendanceType;
	}

	public void setAttendanceType(String attendanceType){

		attendanceType = this.attendanceType;

	}
}
