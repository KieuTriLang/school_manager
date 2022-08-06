package com.model;

public class StudentModel {
	public int id;
	public String firstName;
	public String lastName;
	public boolean gender;
	public String doB;
	public String phoneNumber;
	public String address;
	public int classId;
	public StudentModel(int id, String firstName, String lastName, boolean gender, String doB, String phoneNumber,
			String address,int classId) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.doB = doB;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.classId = classId;
	}
}
