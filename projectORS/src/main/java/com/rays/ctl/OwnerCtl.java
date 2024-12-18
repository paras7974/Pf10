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
import com.rays.dto.OwnerDTO;
import com.rays.dto.VehicleIdDTO;
import com.rays.form.OwnerForm;
import com.rays.service.OwnerServiceInt;
import com.rays.service.VehicleIdServiceInt;

@RestController
@RequestMapping(value = "Owner")
public class OwnerCtl extends BaseCtl<OwnerForm, OwnerDTO, OwnerServiceInt> {

	@Autowired
	VehicleIdServiceInt vehicleIdService;

	@Autowired
	OwnerServiceInt ownerService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Paras");
		ORSResponse res = new ORSResponse(true);
		VehicleIdDTO dto = new VehicleIdDTO();
		List<DropdownList> list = vehicleIdService.search(dto, userContext);
		res.addResult("vehicleIdList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createOwner(@Valid @RequestBody OwnerForm ownerForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Owner created successfully", HttpStatus.CREATED);
	}
}
