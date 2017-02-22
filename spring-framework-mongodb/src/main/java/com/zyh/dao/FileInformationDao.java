package com.zyh.dao;

import java.util.List;

import com.zyh.model.FileInformation;

public interface FileInformationDao {
	/**
	 * 保存文档信息
	 * @param fileInformation
	 */
	void save(FileInformation fileInformation);
	/**
	 * 获取全部文档信息
	 * @return
	 */
	List<FileInformation> findAll();

}
