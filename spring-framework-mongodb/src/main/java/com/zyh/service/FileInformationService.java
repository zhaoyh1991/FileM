package com.zyh.service;

import java.util.List;

import com.zyh.model.FileInformation;

public interface FileInformationService {

	void save(FileInformation fileInformation);

	List<FileInformation> findAll();

}
