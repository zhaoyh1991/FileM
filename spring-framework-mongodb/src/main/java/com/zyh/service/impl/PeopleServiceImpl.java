package com.zyh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.dao.PeopleDao;
import com.zyh.model.User;
import com.zyh.service.PeopleService;
@Service
public class PeopleServiceImpl implements PeopleService {
	@Autowired
	private PeopleDao peopleDao;
	@Override
	public void save(User people) {
		peopleDao.save(people);
	}

	@Override
	public List<User> findByName(String name) {
		return peopleDao.findByName(name);
	}

	@Override
	public List<User> findAll() {
		return peopleDao.findAll();
	}

	@Override
	public User findByNameAndPassword(String name, String password) {
		return peopleDao.findByNameAndPassword(name,password);
	}

}
