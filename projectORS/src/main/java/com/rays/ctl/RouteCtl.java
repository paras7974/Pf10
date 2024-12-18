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
import com.rays.dto.RouteDTO;
import com.rays.dto.VehicleIdDTO;
import com.rays.form.RouteForm;
import com.rays.service.RouteServiceInt;
import com.rays.service.VehicleIdServiceInt;

@RestController
@RequestMapping(value = "Route")
public class RouteCtl extends BaseCtl<RouteForm, RouteDTO, RouteServiceInt> {

	@Autowired
	VehicleIdServiceInt vehicleIdService;

	@Autowired
	RouteServiceInt routeService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		VehicleIdDTO dto = new VehicleIdDTO();
		List<DropdownList> list = vehicleIdService.search(dto, userContext);
		res.addResult("vehicleIdList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createRoute(@Valid @RequestBody RouteForm routeForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Route created successfully", HttpStatus.CREATED);
	}
}
