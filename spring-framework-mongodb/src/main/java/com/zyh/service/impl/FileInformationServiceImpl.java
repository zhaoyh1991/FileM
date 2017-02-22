package com.zyh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.dao.FileInformationDao;
import com.zyh.model.FileInformation;
import com.zyh.service.FileInformationService;

@Service
public class FileInformationServiceImpl implements FileInformationService {
	@Autowired
	private FileInformationDao fileInformationDao;
	@Override
	public void save(FileInformation fileInformation) {
		 fileInformationDao.save(fileInformation);
	}
	@Override
	public List<FileInformation> findAll() {
		return fileInformationDao.findAll();
	}

}
