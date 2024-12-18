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
import com.rays.dto.DepartmentDTO;
import com.rays.form.DepartmentForm;
import com.rays.service.DepartmentServiceInt;

@RestController
@RequestMapping(value = "Department")
public class DepartmentCtl extends BaseCtl<DepartmentForm, DepartmentDTO, DepartmentServiceInt> {

	@Autowired
	private DepartmentServiceInt departmentService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload");
		ORSResponse res = new ORSResponse(true);
		DepartmentDTO dto = new DepartmentDTO();
		List<DropdownList> list = departmentService.search(dto, userContext);
		res.addResult("departmentList", list);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		DepartmentDTO dto = baseService.findByName(name, userContext);
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
