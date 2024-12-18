package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.VehicleIdDTO;
import com.rays.form.VehicleIdForm;
import com.rays.service.VehicleIdServiceInt;

@RestController
@RequestMapping(value = "VehicleId")
public class VehicleIdCtl extends BaseCtl<VehicleIdForm, VehicleIdDTO, VehicleIdServiceInt> {

	@Autowired
	private VehicleIdServiceInt vehicleIdService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload");
		ORSResponse res = new ORSResponse(true);
		VehicleIdDTO dto = new VehicleIdDTO();
		List<DropdownList> list = vehicleIdService.search(dto, userContext);
		res.addResult("vehicleIdList", list);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		VehicleIdDTO dto = baseService.findByName(name, userContext);
		System.out.println("Product " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
