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
import com.rays.dto.CategoryDTO;
import com.rays.dto.ProjectDTO;
import com.rays.dto.ProjectForm;
import com.rays.service.CategoryServiceInt;
import com.rays.service.ProjectServiceInt;

@RestController
@RequestMapping(value = "Project")
public class ProjectCtl extends BaseCtl<ProjectForm, ProjectDTO, ProjectServiceInt> {

	@Autowired
	CategoryServiceInt categoryService;

	@Autowired
	ProjectServiceInt projectService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		CategoryDTO dto = new CategoryDTO();
		List<DropdownList> list = categoryService.search(dto, userContext);
		res.addResult("categoryList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createProject(@Valid @RequestBody ProjectForm projectForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Project created successfully", HttpStatus.CREATED);
	}
}
