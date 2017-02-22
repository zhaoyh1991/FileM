package com.zyh.dao.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.zyh.dao.GFSDao;
@Repository
public class GFSDaoImpl implements GFSDao {
	
	//这里使用了 GridFsTemplate 存取文件
	@Autowired
	private GridFsTemplate  gridFsTemplate;

	/**
	 * 保存文件
	 */
	@Override
	public GridFSFile store(InputStream inputStream, String filename) {
		GridFSFile gridFSFile= gridFsTemplate.store(inputStream, filename);
		return gridFSFile;
	}
	/**
	 * 查找文件
	 */
	@Override
	public GridFSDBFile findOne(Query query) {
		GridFSDBFile file=gridFsTemplate.findOne(query);
		return file;
	}

}
