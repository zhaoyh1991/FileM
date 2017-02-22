package com.zyh.dao;

import java.io.InputStream;

import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

public interface GFSDao {
	/**
	 * 保存文件
	 * @param inputStream
	 * @param filename
	 * @return
	 */
	GridFSFile store(InputStream inputStream, String filename);
	
	/**
	 * 依据条件获取文件
	 * @param query
	 * @return
	 */
	GridFSDBFile findOne(Query query);

}
