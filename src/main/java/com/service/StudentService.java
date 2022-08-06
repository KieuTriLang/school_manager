package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.model.DB;
import com.model.StudentModel;

public class StudentService implements ICrudService<StudentModel> {
	@Override
	public List<HashMap<String, String>> GetAll() {
		String sql = "SELECT s.`Id`,s.`FirstName`,s.`LastName`,IF(s.`Gender`=1,'Male','Female') as `Gender`,s.`Dob`,c.`NameClass` FROM `students` s LEFT JOIN `classrooms` c ON s.`ClassId` = c.`Id`";

		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("Gender", rs.getString("Gender"));
				row.put("Dob", rs.getString("Dob"));
				row.put("NameClass", rs.getString("NameClass"));

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
	public List<HashMap<String, String>> GetAllByAlphabet() {
		String sql = "SELECT s.`Id`,s.`FirstName`,s.`LastName`,IF(s.`Gender`=1,'Male','Female') as `Gender`,s.`Dob`,c.`NameClass` FROM `students` s LEFT JOIN `classrooms` c ON s.`ClassId` = c.`Id` ORDER BY s.`LastName`";

		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("Gender", rs.getString("Gender"));
				row.put("Dob", rs.getString("Dob"));
				row.put("NameClass", rs.getString("NameClass"));
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
	public List<HashMap<String, String>> GetAllByDob() {
		String sql = "SELECT s.`Id`,s.`FirstName`,s.`LastName`,IF(s.`Gender`=1,'Male','Female') as `Gender`,s.`Dob`,c.`NameClass` FROM `students` s LEFT JOIN `classrooms` c ON s.`ClassId` = c.`Id` ORDER BY s.`Dob`";

		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("Gender", rs.getString("Gender"));
				row.put("Dob", rs.getString("Dob"));
				row.put("NameClass", rs.getString("NameClass"));

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
		String sql = "SELECT s.`Id`,s.`FirstName`,s.`LastName`,IF(s.`Gender`=1,'Male','Female') AS `Gender`,s.`Dob`,s.`Phone`,s.`Address`,c.`Id` as `ClassId`,c.`NameClass` FROM `students` s LEFT JOIN `classrooms` c ON s.`ClassId` = c.`Id` WHERE s.`Id` ="
				+ id;
		// TODO Auto-generated method stub
		try {
			DB.open();
			ResultSet rs = DB.q(sql);

			HashMap<String, String> row = new HashMap<>();
			while (rs.next()) {
				row.put("Id", rs.getString("Id"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("Gender", rs.getString("Gender"));
				row.put("Dob", rs.getString("Dob"));
				row.put("Phone", rs.getString("Phone"));
				row.put("Address", rs.getString("Address"));
				row.put("ClassId", rs.getString("ClassId"));
				row.put("NameClass", rs.getString("NameClass"));
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

	public HashMap<String, String> GetById(String id) {
		String sql = "SELECT s.`Id`,s.`FirstName`,s.`LastName`,IF(s.`Gender`=1,'Male','Female') AS `Gender`,s.`Dob`,s.`Phone`,s.`Address`,c.`Id` as `ClassId`,c.`NameClass` FROM `students` s LEFT JOIN `classrooms` c ON s.`ClassId` = c.`Id` WHERE s.`Id` ="
				+ id;
		// TODO Auto-generated method stub
		try {
			DB.open();
			ResultSet rs = DB.q(sql);

			HashMap<String, String> row = new HashMap<>();
			while (rs.next()) {
				row.put("Id", rs.getString("Id"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("Gender", rs.getString("Gender"));
				row.put("Dob", rs.getString("Dob"));
				row.put("Phone", rs.getString("Phone"));
				row.put("Address", rs.getString("Address"));
				row.put("ClassId", rs.getString("ClassId"));
				row.put("NameClass", rs.getString("NameClass"));
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

	public List<HashMap<String, String>> GetStudentsByClassId(int id) {
		String sql = "SELECT s.`Id`,s.`FirstName`,s.`LastName`,IF(s.`Gender`=1,'Male','Female') AS `Gender`,s.`Dob` FROM `students` s WHERE s.`ClassId` = "
				+ id;
		// TODO Auto-generated method stub
		try {
			DB.open();
			ResultSet rs = DB.q(sql);

			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("FirstName", rs.getString("FirstName"));
				row.put("LastName", rs.getString("LastName"));
				row.put("Gender", rs.getString("Gender"));
				row.put("Dob", rs.getString("Dob"));

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
	public boolean Add(StudentModel model) {
		String sql = "insert into `students` (`FirstName`,`LastName`,`Gender`,`Dob`,`Phone`,`Address`,`ClassId`)"
				+ "values ('" + model.firstName + "','" + model.lastName + "',"
				+ (model.gender ? 1 : 0) + ",'" + model.doB + "','" + model.phoneNumber + "','" + model.address + "',"
				+ model.classId + ")";
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
	public boolean Update(StudentModel model) {
		String sql = "update `students`" + "        set `FirstName` = '" + model.firstName + "',`LastName` = '"
				+ model.lastName + "', `Gender` = " + (model.gender ? 1 : 0) + ",`Dob`='" + model.doB + "',`Phone`='"
				+ model.phoneNumber + "',`Address`='" + model.address + "',`ClassId` = " + model.classId
				+ "        where `Id` = '" + model.id + "'";
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
		String sql2 = "delete from `students` where `Id` = '" + id + "'";

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

	public boolean DeleteById(String id) {
		String sql2 = "delete from `students` where `Id` = '" + id + "'";

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

	public int Count() {
		String sql = "select count(`Id`) as `count` from `students`";
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			while (rs.next()) {
				return Integer.parseInt(rs.getString("count"));
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
	public boolean IsExistsPhone(String phone) {
		// TODO Auto-generated method stub
		String sql = "select * from `students` where `Phone` = '" + phone +"'";
		try {
			DB.open();
			ResultSet rs = DB.q(sql);

			HashMap<String, String> row = new HashMap<>();
			while (rs.next()) {
				row.put("Id", rs.getString("Id"));
			}
			return row.size() > 0 ? true : false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
