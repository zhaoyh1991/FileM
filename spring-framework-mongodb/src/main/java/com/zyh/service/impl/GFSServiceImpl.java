package com.zyh.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.zyh.dao.GFSDao;
import com.zyh.service.GFSService;
@Service
public class GFSServiceImpl implements GFSService {
	@Autowired
	private GFSDao gfsDao;
	@Override
	public String store(InputStream inputStream, String filename) {
		GridFSFile fsFile= gfsDao.store(inputStream,filename);
		return fsFile.getFilename();
	}

	@Override
	public GridFSDBFile findOne(String filename) {
		GridFSDBFile file= gfsDao.findOne(Query.query(Criteria.where("filename").is(filename)));
		return file;
	}

}
