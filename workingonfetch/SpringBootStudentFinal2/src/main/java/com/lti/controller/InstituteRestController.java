package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.model.Institute;
import com.lti.model.Scheme;
import com.lti.model.Student;
import com.lti.service.InstituteService;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class InstituteRestController {
	@Autowired
	private InstituteService service;
	// localhost:9091/

	@RequestMapping(path = "hello")
	public String HelloWorld() {
		return "Hello";
	}

	// localhost:9091/institute/add
	@RequestMapping(path = "institute/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addInstitute(@RequestBody Institute institute) {
		ResponseEntity<String> response;
		boolean result = service.addInstitute(institute);
		System.out.println("Controller");
		if (result) {
			response = new ResponseEntity<String>("Institute  is added", HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<String>("Institute is not added", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	// localhost:9091/
	@RequestMapping(path = "/institute/{instituteCode}/{setPassword}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int LoginInstitute(@PathVariable("instituteCode") int instituteCode,
			@PathVariable("setPassword") String setPassword) {

		boolean result = service.loginInstitute(instituteCode, setPassword);
		System.out.println("Result " + result);
		if (result) {
			return 1;
		} else {
			return 0;
		}
	}

	@RequestMapping(path = "student/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		ResponseEntity<String> response;
		boolean result = service.addStudent(student);
		if (result) {
			response = new ResponseEntity<String>("Student  is added", HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<String>("Student is not added", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(path = "/student/{aadharNo}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int studentLogin(@PathVariable("aadharNo") long aadharNo,
			@PathVariable("password") String Password) {

		boolean result = service.studentLogin(aadharNo, Password);
		System.out.println("Result" + result);
		if (result) {
			return 1;
		} else {
			return 0;
		}
	}

	@RequestMapping(path = "scheme/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addStudentScheme(@RequestBody Scheme scheme) {
		ResponseEntity<String> response;
		boolean result = service.addStudentScheme(scheme);
		if (result) {
			response = new ResponseEntity<String>("Scheme is added", HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<String>("Scheme is not added", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	// http://localhost:9091/viewAllStudents
	@RequestMapping(path = "find/institute", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Institute> findAllInstitute() {
		List<Institute> institute = service.getAllInstitute();
		return institute;

	}
	@RequestMapping(path="find/student",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Student> findAllStudent(){
		List<Student> student=service.getAllStudent();
		return student;
	}
}
