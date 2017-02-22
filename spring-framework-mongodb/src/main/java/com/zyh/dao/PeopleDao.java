package com.zyh.dao;

import java.util.List;

import com.zyh.model.User;

public interface PeopleDao {
	/**
	 * 保存用户
	 * @param people
	 */
	void save(User people);
	
	List<User> findByName(String name);

	List<User> findAll();
	/**
	 * 依据用户名和密码查询
	 * @param name
	 * @param password
	 * @return
	 */
	User findByNameAndPassword(String name, String password);

}
