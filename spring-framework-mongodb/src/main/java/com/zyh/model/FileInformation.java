package com.zyh.model;

import java.io.Serializable;
import java.util.Date;

public class FileInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filename;//文件名称
	private String storename;//保存时名称
	private String username;//上传用户名称
	private String groupname;//文件所属文件类别名称
	private Date  uploadTime;//上传时间
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	
	
	
	
}
