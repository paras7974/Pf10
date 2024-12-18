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
import com.rays.dto.ATMsDTO;
import com.rays.dto.LocationDTO;
import com.rays.form.ATMsForm;
import com.rays.service.ATMsServiceInt;
import com.rays.service.LocationServiceInt;

@RestController
@RequestMapping(value = "ATMs")
public class ATMsCtl extends BaseCtl<ATMsForm, ATMsDTO, ATMsServiceInt> {

	@Autowired
	LocationServiceInt locationService;

	@Autowired
	ATMsServiceInt ATMsService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		LocationDTO dto = new LocationDTO();
		List<DropdownList> list = locationService.search(dto, userContext);
		res.addResult("locationList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createATMs(@Valid @RequestBody ATMsForm atmsForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("ATMs created successfully", HttpStatus.CREATED);
	}
}
