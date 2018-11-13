package com.journaldev.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.spring.model.Student;

/**
 * Handles requests for the Student service.
 */
@Controller
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	//Map to store employees, ideally we should use database
	Map<Integer, Student> stuData = new HashMap<Integer, Student>();
	
	@RequestMapping(value ="/rest/stu/ippy", method = RequestMethod.GET)
	public @ResponseBody Student getDummyStudent() {
		logger.info("Start getDummyStudent");
		Student stu = new Student();
		stu.setId(101);
		stu.setName("Ippy");
		stu.setCreatedDate(new Date());
		stuData.put(101, stu);
		return stu;
	}
	
	@RequestMapping(value ="/rest/stu/{id}", method = RequestMethod.GET)
	public @ResponseBody Student getEmployee(@PathVariable("id") int stuId) {
		logger.info("Start getEmployee. ID="+stuId);
		
		return stuData.get(stuId);
	}
	
	@RequestMapping(value = "/rest/sts", method = RequestMethod.GET)
	public @ResponseBody List<Student> getAllEmployees() {
		logger.info("Start getAllEmployees.");
		List<Student> sts = new ArrayList<Student>();
		Set<Integer> stuIdKeys = stuData.keySet();
		for(Integer i : stuIdKeys){
			sts.add(stuData.get(i));
		}
		return sts;
	}
	
	@RequestMapping(value = "/rest/stu/create", method = RequestMethod.POST)
	public @ResponseBody Student createEmployee(@RequestBody Student stu) {
		logger.info("Start createEmployee.");
		stu.setCreatedDate(new Date());
		stuData.put(stu.getId(), stu);
		return stu;
	}
	
	@RequestMapping(value ="/rest/stu/delete/{id}", method = RequestMethod.PUT)
	public @ResponseBody Student deleteEmployee(@PathVariable("id") int stuId) {
		logger.info("Start deleteEmployee.");
		Student stu = stuData.get(stuId);
		stuData.remove(stuId);
		return stu;
	}
	
}
