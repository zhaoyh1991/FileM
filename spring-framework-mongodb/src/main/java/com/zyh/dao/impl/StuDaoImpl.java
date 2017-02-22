package com.zyh.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.zyh.dao.StuDao;
import com.zyh.model.Student;
@Repository
public class StuDaoImpl implements StuDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public Map finAll(int page, int rows) {
		int startIndex=(page-1)*rows;
		Map m=new HashMap<>();
		int total=mongoTemplate.find(Query.query(Criteria.where("state").is("1")),Student.class).size();
		Query query=new Query();
		query.skip(startIndex);
		query.limit(rows);
		List<Student> students=mongoTemplate.find(query, Student.class);
		m.put("total", total);
		m.put("students", students);
		return m;
	}

	@Override
	public void insert(Student student) {
		mongoTemplate.insert(student);
		
	}

	@Override
	public void update(Student student) {
		Update update;
		try {
			update = this.getUpdate(student);
			mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(student.getId())), update, Student.class);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public Update getUpdate(Student student) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class stuclass=student.getClass();
		Update update=new Update();
		Field[] fields=stuclass.getDeclaredFields();
		for(Field field:fields){
			String fname=field.getName();
			String mname="get"+fname.substring(0, 1).toUpperCase()+fname.substring(1);
			Class fclass=field.getType();
			Method m=stuclass.getDeclaredMethod(mname, null);
			Object object=m.invoke(student, null);
			update.set(fname, object);
		}
		return update;
		
	}

	@Override
	public void remove(String id) {
		mongoTemplate.remove(Query.query(Criteria.where("id").is(id)),Student.class);
		
	}

}
