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
import com.rays.dto.CustomerIdDTO;
import com.rays.dto.LoansDTO;
import com.rays.form.LoansForm;
import com.rays.service.CustomerIdServiceInt;
import com.rays.service.LoansServiceInt;

@RestController
@RequestMapping(value = "Loans")
public class LoansCtl extends BaseCtl<LoansForm, LoansDTO, LoansServiceInt> {

	@Autowired
	CustomerIdServiceInt customerIdService;

	@Autowired
	LoansServiceInt loansService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		CustomerIdDTO dto = new CustomerIdDTO();
		List<DropdownList> list = customerIdService.search(dto, userContext);
		res.addResult("customerIdList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createLoans(@Valid @RequestBody LoansForm vehicleTrackingForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Loans created successfully", HttpStatus.CREATED);
	}
}
