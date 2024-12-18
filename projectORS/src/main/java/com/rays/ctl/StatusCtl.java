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
import com.rays.dto.StatusDTO;
import com.rays.form.StatusForm;
import com.rays.service.StatusServiceInt;

@RestController
@RequestMapping(value = "Status")
public class StatusCtl extends BaseCtl<StatusForm, StatusDTO, StatusServiceInt> {

	@Autowired
	private StatusServiceInt statusService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload");
		ORSResponse res = new ORSResponse(true);
		StatusDTO dto = new StatusDTO();
		List<DropdownList> list = statusService.search(dto, userContext);
		res.addResult("statusList", list);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		StatusDTO dto = baseService.findByName(name, userContext);
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
