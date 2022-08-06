package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model.DB;
import com.model.SubjectModel;

public class SubjectService implements ICrudService<SubjectModel>{

	@Override
	public List<HashMap<String, String>> GetAll() {
		String sql = "SELECT `Id`,`NameSubject` FROM `subjects`";
		// TODO Auto-generated method stub
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
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

	public List<HashMap<String, String>> GetSubjectByClassId(int id) {
		String sql = "SELECT sj.`Id`,sj.`NameSubject` FROM `subjects` sj LEFT JOIN `classsubject` cs ON sj.`Id` = cs.`SubjectId` WHERE cs.`ClassId` = " + id;
		// TODO Auto-generated method stub
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
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

	public HashMap<String, String> GetById(int id) {
		String sql = "SELECT `Id`,`NameSubject` FROM `subjects` where `Id` =" + id;
		// TODO Auto-generated method stub
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			HashMap<String, String> row = new HashMap<>();
			while (rs.next()) {
				row.put("Id", rs.getString("Id"));
				row.put("NameSubject", rs.getString("NameSubject"));

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
	public boolean Add(SubjectModel model) {
		// TODO Auto-generated method stub
				String sql = "insert into `subjects` (`NameSubject`) values ('" + model.nameSubject + "')";
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
	public boolean Update(SubjectModel model) {
		String sql = "update `subjects` set `NameSubject` = '" + model.nameSubject + "' where `Id` = " + model.id;
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
		String sql2 = "delete from `subjects` where `Id` = '" + id + "'";

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

	public List<HashMap<String, String>> GetSubjectByClassName(String name) {
		String sql = "SELECT sj.`Id`,sj.`NameSubject` FROM `subjects` sj LEFT JOIN `classsubject` cs ON sj.`Id` = cs.`SubjectId` left join `classrooms` c on c.`Id` = cs.`ClassId` WHERE c.`NameClass` = '" + name+"'";
		// TODO Auto-generated method stub
		try {
			DB.open();
			ResultSet rs = DB.q(sql);
			List<HashMap<String, String>> list = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("Id", rs.getString("Id"));
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
}
