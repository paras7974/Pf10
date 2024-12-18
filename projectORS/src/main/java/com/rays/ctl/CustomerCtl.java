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
import com.rays.dto.CustomerDTO;
import com.rays.dto.GenderDTO;
import com.rays.form.CustomerForm;
import com.rays.service.CustomerServiceInt;
import com.rays.service.GenderServiceInt;

@RestController
@RequestMapping(value = "Customer")
public class CustomerCtl extends BaseCtl<CustomerForm, CustomerDTO, CustomerServiceInt> {

	@Autowired
	GenderServiceInt genderService;

	@Autowired
	CustomerServiceInt customerService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		GenderDTO dto = new GenderDTO();
		List<DropdownList> list = genderService.search(dto, userContext);
		res.addResult("genderList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerForm customerForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);
	}
}
