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
import com.rays.dto.DiseaseDTO;
import com.rays.dto.PatientDTO;
import com.rays.form.PatientForm;
import com.rays.service.DiseaseServiceInt;
import com.rays.service.PatientServiceInt;

@RestController
@RequestMapping(value = "Patient")
public class PatientCtl extends BaseCtl<PatientForm, PatientDTO, PatientServiceInt> {

	@Autowired
	DiseaseServiceInt diseaseService;

	@Autowired
	PatientServiceInt PatientService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		DiseaseDTO dto = new DiseaseDTO();
		List<DropdownList> list = diseaseService.search(dto, userContext);
		res.addResult("diseaseList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createPatient(@Valid @RequestBody PatientForm patientForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Patient created successfully", HttpStatus.CREATED);
	}
}
