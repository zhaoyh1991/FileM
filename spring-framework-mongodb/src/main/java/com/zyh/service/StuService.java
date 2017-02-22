package com.zyh.service;

import java.util.Map;

import com.zyh.model.Student;

public interface StuService {
	/**
	 * 分页查找
	 * @param page //页码
	 * @param rows //行数
	 * @return 总页数  和信息
	 */
	Map findAll(int page, int rows);

	void insert(Student student);

	void update(Student student);

	void remove(String id);

}
