package com.zyh.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.zyh.dao.FileInformationDao;
import com.zyh.model.FileInformation;

@Repository
public class FileInformationDaoImpl implements FileInformationDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	@Override
	public void save(FileInformation fileInformation) {
		mongoTemplate.save(fileInformation);
	}
	@Override
	public List<FileInformation> findAll() {
		return mongoTemplate.findAll(FileInformation.class);
	}

}
