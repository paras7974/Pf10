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
import com.rays.dto.JobDTO;
import com.rays.dto.StatusDTO;
import com.rays.form.JobForm;
import com.rays.service.JobServiceInt;
import com.rays.service.StatusServiceInt;

@RestController
@RequestMapping(value = "Job")
public class JobCtl extends BaseCtl<JobForm, JobDTO, JobServiceInt> {

	@Autowired
	StatusServiceInt statusService;

	@Autowired
	JobServiceInt jobService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		StatusDTO dto = new StatusDTO();
		List<DropdownList> list = statusService.search(dto, userContext);
		res.addResult("statusList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createJob(@Valid @RequestBody JobForm jobForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
	}
}