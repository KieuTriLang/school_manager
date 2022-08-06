package com.model;

public class ResultModel {
	public String[] id;
	public int classSubjectId;
	public String[] studentId;
	public String nameTest;
	public String[] mark;
	public ResultModel(String[] id, int classSubjectId, String[] studentId, String nameTest, String[] mark) {
		this.id = id;
		this.classSubjectId = classSubjectId;
		this.studentId = studentId;
		this.nameTest = nameTest;
		this.mark = mark;
	}
}