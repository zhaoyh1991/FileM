package com.zyh.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyh.model.Student;
import com.zyh.service.StuService;

@Controller
public class StuController {
	@Autowired
	private StuService stuService;
	@RequestMapping("/stuinfo")
	public String stuinfo(){
		return "stuInfos";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/stuInfos")
	public @ResponseBody
	Map getStuInfos(Integer page,Integer rows){
		Map map=new HashMap<>();
		Map stuMap=stuService.findAll(page,rows);
		map.put("total",stuMap.get("total"));
		map.put("rows", stuMap.get("students"));
		return map;
	}
	
	@RequestMapping("/saveStu")
	public @ResponseBody
	Map saveStu(String id,String name,String birthday,String addr,String academy,String major,String clazz,String gender,String state){
		Student student=new Student();
		student.setAcademy(academy);
		student.setAddr(addr);
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date date=null;
		try {
			date=simpleDateFormat.parse(birthday);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		student.setBirthday(date);
		student.setClazz(clazz);
		student.setGender(gender);
		student.setId(id);
		student.setMajor(major);
		student.setName(name);
		student.setState(state);
		Map<String, String> re=new HashMap<>();
		try {
			stuService.insert(student);
			re.put("result", "sucess");
		} catch (Exception e) {
			e.printStackTrace();
			re.put("result", "fail");
		}
		
		return re;
		
	}
	
	
	@RequestMapping("/updateStu")
	public @ResponseBody
	Map updateStu(String id,String name,String birthday,String addr,String academy,String major,String clazz,String gender,String state){
		Student student=new Student();
		student.setAcademy(academy);
		student.setAddr(addr);
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date date=null;
		try {
			date=simpleDateFormat.parse(birthday);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		student.setBirthday(date);
		student.setClazz(clazz);
		student.setGender(gender);
		student.setId(id);
		student.setMajor(major);
		student.setName(name);
		student.setState(state);
		Map<String, String> re=new HashMap<>();
		try {
			stuService.update(student);
			re.put("result", "sucess");
		} catch (Exception e) {
			e.printStackTrace();
			re.put("result", "fail");
		}
		
		return re;
		
	}
	
	@RequestMapping("/delStu")
	public @ResponseBody
	Map delStu(String id){
		Map<String, String> re=new HashMap<>();
		try {
			stuService.remove(id);
			re.put("result", "sucess");
		} catch (Exception e) {
			e.printStackTrace();
			re.put("result", "fail");
		}
		
		return re;
	}
 	
}
