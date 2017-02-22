package com.zyh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyh.model.FileInformation;
import com.zyh.model.User;
import com.zyh.service.FileInformationService;
import com.zyh.service.PeopleService;


@Controller
public class HelloController {
	@Autowired
	private PeopleService peopleService;
	@Autowired
	private FileInformationService fileInformationService;
	
	@RequestMapping("/listFile")
	String listPeople(HttpServletRequest request){
		List<FileInformation> fileInformations=fileInformationService.findAll();
		request.setAttribute("files", fileInformations);
		return "listFile";
	}
	
	@RequestMapping("/go")
	public String index(){
		return "login";
	}
	@RequestMapping("/index")
	public String indexpage(){
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,String name,String password){
		User user=peopleService.findByNameAndPassword(name,password);
		if(user!=null){
			request.getSession().setAttribute("user", user);
			return "index";
		}else{
			return "login";
		}
	}
	
	@RequestMapping("/registerUser")
	public String registerUser(){
		return "registerUser";
		
	}
	@RequestMapping("/reguser")
	public @ResponseBody Map reguser(String name,String password){
		Map<String, String> re=new HashMap<>();
		try {
			User user=new User();
			user.setName(name);
			user.setPassword(password);
			peopleService.save(user);
			re.put("result", "sucess");
		} catch (Exception e) {
			e.printStackTrace();
			re.put("result", "fail");
		}
		return re;
	}

}
