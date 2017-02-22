package com.zyh.service;

import java.util.List;

import com.zyh.model.User;

public interface PeopleService {
	void save(User people);
	List<User> findByName(String name);
	List<User> findAll();
	User findByNameAndPassword(String name, String password);
	
}
