package com.service;

import java.util.HashMap;
import java.util.List;

public interface ICrudService<T> {
	List<HashMap<String,String>> GetAll();
	HashMap<String,String> GetById(int id);
	boolean Add(T model);
	boolean Update(T model);
	boolean DeleteById(int id);
}
