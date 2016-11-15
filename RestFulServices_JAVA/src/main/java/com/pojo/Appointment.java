package com.pojo;

public class Appointment {

	private static final long serialVersionUID = 5088863992478607917L;

	
	String subject;
	String startOn;
	String endOn;
	String primaryKey;
	Attendee attendee;
	
	public Appointment(){


	}
	public Appointment(String subject, String startOn, String endOn, Attendee attendee) {
		
		super();
		this.subject = subject;
		this.startOn = startOn;
		this.endOn = endOn;
	    this.attendee = attendee;

	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getStartOn() {
		return startOn;
	}

	public void setStartOn(String startOn) {
		this.startOn = startOn;
	}
	public String getEndOn() {
		return endOn;
	}

	public void setEndOn(String endOn) {
		this.endOn = endOn;
	}
	public Attendee getAttendee() {
		return attendee;
	}

	public void String(Attendee attendee) {
		this.attendee = attendee;
	}
	
	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

}
