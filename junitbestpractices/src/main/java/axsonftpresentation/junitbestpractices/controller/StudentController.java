package axsonftpresentation.junitbestpractices.controller;

import axsonftpresentation.junitbestpractices.business.StudentBusiness;
import axsonftpresentation.junitbestpractices.model.StudentDet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
public class StudentController {

	private StudentBusiness studentBusiness;
	@Inject
	public void setStudentBusiness(final StudentBusiness studentBusiness) {
		this.studentBusiness = studentBusiness;
	}

	@RequestMapping("/welcome")
	public String getWelcomeMsg(ModelMap modelMap) {
		modelMap.put("nume", "Pop");
		return "welcome";
	}

	@GetMapping("/hello-world")
	public ResponseEntity<String> getHelloWorldMsg() {
		return ResponseEntity.ok("Hello World!");
	}

	@GetMapping("/student")
	public String getStudent(ModelMap model) {
		model.put("student", studentBusiness.getStudentById(1L));
		return "student";
	}

	@GetMapping("/studentInf")
	public ResponseEntity<StudentDet> getJsonForStudentInf(@RequestParam(value = "id") final Long id) {
		return ResponseEntity.ok(studentBusiness.getStudentById(id));
	}
}
