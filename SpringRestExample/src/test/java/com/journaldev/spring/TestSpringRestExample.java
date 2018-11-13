package com.journaldev.spring;

import org.springframework.web.client.RestTemplate;

import com.journaldev.spring.model.Student;

public class TestSpringRestExample {

	public static final String SERVER_URI = "http://localhost:9090/SpringRestExample";
	
	public static void main(String args[]){
		
		testCreateStudent();
		System.out.println("*****");
		testGetStudent();
		System.out.println("*****");
	}

	private static void testCreateStudent() {
		Student stu = new Student();
		stu.setId(1);stu.setName("Pankaj Kumar");
	}

	private static void testGetStudent() {
		RestTemplate restTemplate = new RestTemplate();
		Student stu = restTemplate.getForObject(SERVER_URI+"/rest/stu/1", Student.class);
		printStuData(stu);
	}
	
	public static void printStuData(Student stu){
		System.out.println("ID="+stu.getId()+",Name="+stu.getName()+",CreatedDate="+stu.getCreatedDate());
	}
}
