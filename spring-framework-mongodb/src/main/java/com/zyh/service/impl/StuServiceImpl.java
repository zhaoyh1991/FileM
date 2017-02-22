package com.zyh.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.dao.StuDao;
import com.zyh.model.Student;
import com.zyh.service.StuService;
@Service
public class StuServiceImpl implements StuService {
	@Autowired
	private StuDao stuDao;
	
	@Override
	public Map findAll(int page, int rows) {
		return stuDao.finAll(page,rows);
	}

	@Override
	public void insert(Student student) {
		stuDao.insert(student);
		
	}

	@Override
	public void update(Student student) {
		stuDao.update(student);
		
	}

	@Override
	public void remove(String id) {
		stuDao.remove(id);
		
	}

}
