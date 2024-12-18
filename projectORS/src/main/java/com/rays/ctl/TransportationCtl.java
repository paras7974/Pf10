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
import com.rays.dto.ModeDTO;
import com.rays.dto.TransportationDTO;
import com.rays.form.TransportationForm;
import com.rays.service.ModeServiceInt;
import com.rays.service.TransportationServiceInt;

@RestController
@RequestMapping(value = "Transportation")
public class TransportationCtl extends BaseCtl<TransportationForm, TransportationDTO, TransportationServiceInt> {

	@Autowired
	ModeServiceInt modeService;

	@Autowired
	TransportationServiceInt transportationService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Paras");
		ORSResponse res = new ORSResponse(true);
		ModeDTO dto = new ModeDTO();
		List<DropdownList> list = modeService.search(dto, userContext);
		res.addResult("modeList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createTransportation(@Valid @RequestBody TransportationForm transportationForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Transportation created successfully", HttpStatus.CREATED);
	}
}
