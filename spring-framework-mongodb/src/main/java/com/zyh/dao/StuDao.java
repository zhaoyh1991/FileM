package com.zyh.dao;

import java.util.Map;

import com.zyh.model.Student;

public interface StuDao {

	Map finAll(int page, int rows);

	void insert(Student student);

	void update(Student student);

	void remove(String id);

}
