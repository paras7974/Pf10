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
import com.rays.dto.CompanyDTO;
import com.rays.dto.ExperienceDTO;
import com.rays.form.CompanyForm;
import com.rays.service.CompanyServiceInt;
import com.rays.service.ExperienceServiceInt;

@RestController
@RequestMapping(value = "Company")
public class CompanyCtl extends BaseCtl<CompanyForm, CompanyDTO, CompanyServiceInt> {

	@Autowired
	ExperienceServiceInt experienceService;

	@Autowired
	CompanyServiceInt companyService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		ExperienceDTO dto = new ExperienceDTO();
		List<DropdownList> list = experienceService.search(dto, userContext);
		res.addResult("experienceList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createCompany(@Valid @RequestBody CompanyForm patientForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
	}
}
