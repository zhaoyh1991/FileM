package com.zyh.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.gridfs.GridFSDBFile;
import com.zyh.model.FileInformation;
import com.zyh.model.User;
import com.zyh.service.FileInformationService;
import com.zyh.service.GFSService;

@Controller
public class FileController {
	@Autowired
	private GFSService gfsService;
	
	@Autowired
	private FileInformationService fileInformationService;
	
	@RequestMapping("/upload")
	public String upload(){
	return "upload";	
	}
	
	
	@RequestMapping("/save")
	public void saveFile(HttpServletRequest request,HttpServletResponse response,
			String group,@RequestParam("file") MultipartFile file){
		User user=(User) request.getSession().getAttribute("user");
		FileInformation fileInformation=new FileInformation();
		String filename=file.getOriginalFilename();
		fileInformation.setFilename(filename);
		fileInformation.setGroupname(group);
		String storename=filename+new Date().getTime();
		fileInformation.setStorename(storename);
		fileInformation.setUploadTime(new Date());
		fileInformation.setUsername(user.getName());
		fileInformationService.save(fileInformation);//鏂囨。淇℃伅鍏ュ簱
		
		try {
			InputStream inputStream=file.getInputStream();
			gfsService.store(inputStream,storename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			response.sendRedirect("listFile.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@RequestMapping("/getFile")
	public void getFile(String f,HttpServletRequest request,HttpServletResponse response){
		GridFSDBFile file=	gfsService.findOne(f);
		try {
			OutputStream outputStream= response.getOutputStream();
			file.writeTo(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
