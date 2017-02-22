package com.zyh.service;

import java.io.InputStream;

import com.mongodb.gridfs.GridFSDBFile;

public interface GFSService {
	String store(InputStream inputStream,String filename);
	GridFSDBFile findOne(String filename);
	
}
