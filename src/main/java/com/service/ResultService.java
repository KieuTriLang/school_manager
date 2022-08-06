package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model.DB;
import com.model.ResultModel;
import com.model.ResultModelUpdate;

public class ResultService implements ICrudService<ResultModel> {

	@Override
	public List<HashMap<String, String>> GetAll() {
		// TODO Auto-generated method stub
		String sql = "select r.`Id`,c.`Id` as `ClassId`,c.`NameClass`,sj.`Id` as `SubjectId`,sj.`NameSubject`,s.`Id` as `StudentId`,s.`FirstName`,s.`LastName`,r.`NameTest`,r.`Mark` from `results` r\r\n"
				+ "	inner join `classsubject` cs on cs.`Id` = r.`ClassSubjectId`\r\n"
				+ "	inner join `classrooms` c on c.`Id` = cs.`ClassId`\r\n"
				+ "	inner join `subjects` sj on sj.`Id` = cs.`SubjectId`\r\n"
				+ "    inner join `students` s on s.`Id` = r.`StudentId`;";
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("ClassId", rs.getString("ClassId"));
				row.put("NameClass", rs.getString("NameClass"));
				row.put("SubjectId", rs.getString("SubjectId"));
				row.put("NameSubject", rs.getString("NameSubject"));
				row.put("StudentId", rs.getString("StudentId"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("NameTest", rs.getString("NameTest"));
				row.put("Mark", rs.getString("Mark"));

				list.add(row);

			}
			return list;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<HashMap<String, String>> GetByClassSubjectNameTest(int classId, int subjectId, String nameTest) {
		// TODO Auto-generated method stub
		String sql = "select r.`Id`,c.`Id` as `ClassId`,c.`NameClass`,sj.`Id` as `SubjectId`,sj.`NameSubject`,s.`Id` as `StudentId`,s.`FirstName`,s.`LastName`,r.`NameTest`,r.`Mark` from `results` r\r\n"
				+ "	inner join `classsubject` cs on cs.`Id` = r.`ClassSubjectId`\r\n"
				+ "	inner join `classrooms` c on c.`Id` = cs.`ClassId`\r\n"
				+ "	inner join `subjects` sj on sj.`Id` = cs.`SubjectId`\r\n"
				+ "    inner join `students` s on s.`Id` = r.`StudentId` where c.`Id` =" + classId + " and sj.`Id` = "
				+ subjectId + " and r.`NameTest` = '" + nameTest + "'";
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("ClassId", rs.getString("ClassId"));
				row.put("NameClass", rs.getString("NameClass"));
				row.put("SubjectId", rs.getString("SubjectId"));
				row.put("NameSubject", rs.getString("NameSubject"));
				row.put("StudentId", rs.getString("StudentId"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("NameTest", rs.getString("NameTest"));
				row.put("Mark", rs.getString("Mark"));

				list.add(row);

			}
			return list;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean IsExistsNameTest(int classId,int subjectId,String nameTest) {
		String sql = "select r.`Id`,c.`Id` as `ClassId`,c.`NameClass`,sj.`Id` as `SubjectId`,sj.`NameSubject`,s.`Id` as `StudentId`,s.`FirstName`,s.`LastName`,r.`NameTest`,r.`Mark` from `results` r\r\n"
				+ "	inner join `classsubject` cs on cs.`Id` = r.`ClassSubjectId`\r\n"
				+ "	inner join `classrooms` c on c.`Id` = cs.`ClassId`\r\n"
				+ "	inner join `subjects` sj on sj.`Id` = cs.`SubjectId`\r\n"
				+ "    inner join `students` s on s.`Id` = r.`StudentId` where c.`Id` =" + classId + " and sj.`Id` = "
				+ subjectId + " and r.`NameTest` = '" + nameTest + "'";
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("ClassId", rs.getString("ClassId"));
				row.put("NameClass", rs.getString("NameClass"));
				row.put("SubjectId", rs.getString("SubjectId"));
				row.put("NameSubject", rs.getString("NameSubject"));
				row.put("StudentId", rs.getString("StudentId"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("NameTest", rs.getString("NameTest"));
				row.put("Mark", rs.getString("Mark"));

				list.add(row);

			}
			return list.size() > 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;		
	}
	public List<HashMap<String, String>> GetAllIncMark() {
		// TODO Auto-generated method stub
		String sql = "select r.`Id`,c.`Id` as `ClassId`,c.`NameClass`,sj.`Id` as `SubjectId`,sj.`NameSubject`,s.`Id` as `StudentId`,s.`FirstName`,s.`LastName`,r.`NameTest`,r.`Mark` from `results` r\r\n"
				+ "	inner join `classsubject` cs on cs.`Id` = r.`ClassSubjectId`\r\n"
				+ "	inner join `classrooms` c on c.`Id` = cs.`ClassId`\r\n"
				+ "	inner join `subjects` sj on sj.`Id` = cs.`SubjectId`\r\n"
				+ "    inner join `students` s on s.`Id` = r.`StudentId` order by r.`Mark` asc;";
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("ClassId", rs.getString("ClassId"));
				row.put("NameClass", rs.getString("NameClass"));
				row.put("SubjectId", rs.getString("SubjectId"));
				row.put("NameSubject", rs.getString("NameSubject"));
				row.put("StudentId", rs.getString("StudentId"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("NameTest", rs.getString("NameTest"));
				row.put("Mark", rs.getString("Mark"));

				list.add(row);

			}
			return list;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<HashMap<String, String>> GetAllDesMark() {
		// TODO Auto-generated method stub
		String sql = "select r.`Id`,c.`Id` as `ClassId`,c.`NameClass`,sj.`Id` as `SubjectId`,sj.`NameSubject`,s.`Id` as `StudentId`,s.`FirstName`,s.`LastName`,r.`NameTest`,r.`Mark` from `results` r\r\n"
				+ "	inner join `classsubject` cs on cs.`Id` = r.`ClassSubjectId`\r\n"
				+ "	inner join `classrooms` c on c.`Id` = cs.`ClassId`\r\n"
				+ "	inner join `subjects` sj on sj.`Id` = cs.`SubjectId`\r\n"
				+ "    inner join `students` s on s.`Id` = r.`StudentId` order by r.`Mark` desc;";
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("ClassId", rs.getString("ClassId"));
				row.put("NameClass", rs.getString("NameClass"));
				row.put("SubjectId", rs.getString("SubjectId"));
				row.put("NameSubject", rs.getString("NameSubject"));
				row.put("StudentId", rs.getString("StudentId"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("NameTest", rs.getString("NameTest"));
				row.put("Mark", rs.getString("Mark"));

				list.add(row);

			}
			return list;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public HashMap<String, String> GetById(int id) {
		// TODO Auto-generated method stub
		String sql = "select r.`Id`,sj.`Id` as `SubjectId`,s.`Id` as `StudentId`,r.`NameTest`,r.`Mark` from `results` r\r\n"
				+ "	inner join `classsubject` cs on cs.`Id` = r.`ClassSubjectId`\r\n"
				+ "	inner join `classrooms` c on c.`Id` = cs.`ClassId`\r\n"
				+ "	inner join `subjects` sj on sj.`Id` = cs.`SubjectId`\r\n"
				+ "    inner join `students` s on s.`Id` = r.`StudentId`where r.`Id` = " + id;
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			HashMap<String, String> row = new HashMap<>();
			while (rs.next()) {
				row.put("Id", rs.getString("Id"));
				row.put("StudentId", rs.getString("StudentId"));
				row.put("SubjectId", rs.getString("SubjectId"));
				row.put("NameTest", rs.getString("NameTest"));
				row.put("Mark", rs.getString("Mark"));

			}
			return row;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<HashMap<String, String>> GetByClassId(int id) {
		// TODO Auto-generated method stub
		String sql = "select r.`Id`,sj.`Id` as `SubjectId`,s.`Id` as `StudentId`,s.`FirstName`,s.`LastName`,r.`NameTest`,r.`Mark` from `results` r\r\n"
				+ "	inner join `classsubject` cs on cs.`Id` = r.`ClassSubjectId`\r\n"
				+ "	inner join `classrooms` c on c.`Id` = cs.`ClassId`\r\n"
				+ "	inner join `subjects` sj on sj.`Id` = cs.`SubjectId`\r\n"
				+ "    inner join `students` s on s.`Id` = r.`StudentId`where c.`Id` = " + id;
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("StudentId", rs.getString("StudentId"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("SubjectId", rs.getString("SubjectId"));
				row.put("NameTest", rs.getString("NameTest"));
				row.put("Mark", rs.getString("Mark"));

				list.add(row);
			}
			return list;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<HashMap<String, String>> GetByStudentId(int id) {
		String sql = "select r.`Id`,r.`NameTest`,r.`Mark`,sj.`NameSubject`"
				+ "from `results` r inner join `classsubject` cs on r.`ClassSubjectId` = cs.`Id`"
				+ "inner join `subjects` sj on cs.`SubjectId` = sj.`Id`"
				+ "inner join `students` s on r.`StudentId` = s.`Id`" + "where r.`StudentId` = '" + id
				+ "' and cs.`ClassId` = s.`ClassId`";
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("NameTest", rs.getString("NameTest"));
				row.put("Mark", rs.getString("Mark"));
				row.put("NameSubject", rs.getString("NameSubject"));

				list.add(row);

			}
			return list;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean Add(ResultModel model) {
		// TODO Auto-generated method stub
		String sql = "insert into `results` (`ClassSubjectId`,`StudentId`,`NameTest`,`Mark`)\r\n" + "values ";
		for (int i = 0; i < model.studentId.length; i++) {
			if (i == model.studentId.length - 1) {
				sql += "(" + model.classSubjectId + ",'" + model.studentId[i] + "','" + model.nameTest + "',"
						+ model.mark[i] + ")";
			} else {
				sql += "(" + model.classSubjectId + ",'" + model.studentId[i] + "','" + model.nameTest + "',"
						+ model.mark[i] + "),";
			}
		}
		try {
			if (DB.exec(sql)) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean Update(ResultModel model) {

		return false;
	}

	public boolean Update(ResultModelUpdate model) {

		String sql = "update `results` set `ClassSubjectId` =" + model.classSubjectId + " , `StudentId`="
				+ model.studentId + ",`NameTest`='" + model.nameTest + "',`Mark`=" + model.mark + " where `Id` = "
				+ model.id;
		// TODO Auto-generated method stub
		try {
			if (DB.exec(sql)) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteById(int id) {
		String sql2 = "delete from `results` where `Id` = '" + id + "'";

		try {
			if (DB.exec(sql2)) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean DeleteRange(String[] ids) {
		String sql = "delete from `results` where `Id` in (";
		for(int i= 0;i<ids.length;i++) {
			if(i < ids.length -1) {
				sql += ids[i] + ",";
			}else {
				sql += ids[i] +")";
			}
		}
		try {
			if (DB.exec(sql)) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int GetClassSubjectId(int classId, int subjectId) {
		String sql = "select `Id` from `classsubject` where `ClassId` = " + classId + " and `SubjectId` = " + subjectId;
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			while (rs.next()) {
				return Integer.parseInt(rs.getString("Id"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public List<HashMap<String, String>> GetNameTestByClassSubject(String nameClass, String nameSubject) {
		String sql = "select c.`Id` as `ClassId`,sj.`Id` as `SubjectId`,r.`NameTest`from `results` r\r\n"
				+ "	inner join `classsubject` cs on cs.`Id` = r.`ClassSubjectId`\r\n"
				+ "	inner join `classrooms` c on c.`Id` = cs.`ClassId`\r\n"
				+ "	inner join `subjects` sj on sj.`Id` = cs.`SubjectId`\r\n" + " where c.`NameClass` = '" + nameClass
				+ "' and sj.`NameSubject` = '" + nameSubject + "' group by r.`NameTest`,c.`Id`,sj.`Id`";
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("ClassId", rs.getString("ClassId"));
				row.put("SubjectId", rs.getString("SubjectId"));
				row.put("NameTest", rs.getString("NameTest"));

				list.add(row);

			}
			return list;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
