package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model.ClassRoomModel;
import com.model.DB;

public class ClassRoomService implements ICrudService<ClassRoomModel> {

	@Override
	public List<HashMap<String, String>> GetAll() {
		String sql = "SELECT c.`Id`,c.`NameClass`,count(distinct st.`Id`) as `NumberSt`,count(distinct cs.`SubjectId`) as `NumberSj`,count(distinct r.`NameTest`) as `NumberTest` FROM `classrooms` c \r\n"
				+ "	left join `students` st on c.`Id` = st.`ClassId`\r\n"
				+ "    left join `classsubject` cs on c.`Id` = cs.`ClassId`\r\n"
				+" left join `results` r on r.`ClassSubjectId` = cs.`Id`\r\n"
				+ "    group by c.`Id`";
		// TODO Auto-generated method stub
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
				row.put("NameClass", rs.getString("NameClass"));
				row.put("NumberSt", rs.getString("NumberSt"));
				row.put("NumberSj", rs.getString("NumberSj"));
				row.put("NumberTest", rs.getString("NumberTest"));

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
	public List<HashMap<String, String>> GetClassSubject(int id) {
		String sql = "SELECT cs.`ClassId`,cs.`SubjectId`,sj.`NameSubject` FROM `classsubject` cs INNER JOIN `subjects` sj ON cs.`SubjectId` = sj.`Id` WHERE cs.`ClassId`="+id;
		// TODO Auto-generated method stub
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("ClassId", rs.getString("ClassId"));
				row.put("SubjectId", rs.getString("SubjectId"));
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
	public HashMap<String, String> GetById(int id) {
		String sql = "SELECT `Id`,`NameClass` FROM `classrooms` where `Id` =" + id;
		// TODO Auto-generated method stub
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			HashMap<String, String> row = new HashMap<>();
			while (rs.next()) {
				row.put("Id", rs.getString("Id"));
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

	@Override
	public boolean Add(ClassRoomModel model) {
		// TODO Auto-generated method stub
		String sql = "insert into `classrooms` (`NameClass`) values ('" + model.nameClass + "')";
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
	public boolean AddSubjectToClass(int id, String[] arrSubject) {
		// TODO Auto-generated method stub
		String sqlDelete = "delete from `classsubject` where `ClassId` ="+id;
		String sql = "insert into `classsubject` (`ClassId`,`SubjectId`) values ";
		for(int i = 0;i<arrSubject.length;i++) {			
			if(i == arrSubject.length-1) {
				sql += "("+id+","+arrSubject[i]+");";
			}else {
				sql += "("+id+","+arrSubject[i]+"),";
			}
		}
		try {
			if(DB.exec(sqlDelete)) {
				if (DB.exec(sql)) {
					return true;
				}
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
	public boolean Update(ClassRoomModel model) {
		String sql = "update `classrooms` set `NameClass` = '" + model.nameClass + "' where `Id` = " + model.id;
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
		String sql2 = "delete from `classrooms` where `Id` = '" + id + "'";

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

}
