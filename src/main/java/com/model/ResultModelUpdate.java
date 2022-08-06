package com.model;

public class ResultModelUpdate {
	public int id;
	public int classSubjectId;
	public int studentId;
	public String nameTest;
	public float mark;
	public ResultModelUpdate(int id, int classSubjectId, int studentId, String nameTest,float mark) {
		this.id = id;
		this.classSubjectId = classSubjectId;
		this.studentId = studentId;
		this.nameTest = nameTest;
		this.mark = mark;
	}
}
