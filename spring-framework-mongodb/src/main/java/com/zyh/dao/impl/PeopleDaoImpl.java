package com.zyh.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.zyh.dao.PeopleDao;
import com.zyh.model.User;
@Repository
public class PeopleDaoImpl implements PeopleDao {
	
	//主要使用了mongoTemplate
	//用法参考：
	@Autowired
	private MongoTemplate mongoTemplate;
	@Override
	public void save(User people) {
		mongoTemplate.save(people);
	}

	@Override
	public List<User> findByName(String name) {
		return mongoTemplate.find(Query.query(Criteria.where("name").is(name)), User.class);
	}

	@Override
	public List<User> findAll() {
		return mongoTemplate.findAll(User.class);
	}

	@Override
	public User findByNameAndPassword(String name, String password) {
		return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name).and("password").is(password)), User.class);
	}

}
