package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.DepartmentDTO;
import com.rays.dto.EmployeeDTO;
import com.rays.form.EmployeeForm;
import com.rays.service.DepartmentServiceInt;
import com.rays.service.EmployeeServiceInt;

@RestController
@RequestMapping(value = "Employee")
public class EmployeeCtl extends BaseCtl<EmployeeForm, EmployeeDTO, EmployeeServiceInt> {

	@Autowired
	DepartmentServiceInt departmentService;

	@Autowired
	EmployeeServiceInt employeeService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		DepartmentDTO dto = new DepartmentDTO();
		List<DropdownList> list = departmentService.search(dto, userContext);
		res.addResult("departmentList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeForm employeeForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
	}
}
