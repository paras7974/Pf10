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
import com.rays.dto.CategoryDTO;
import com.rays.dto.SupplierDTO;
import com.rays.form.SupplierForm;
import com.rays.service.CategoryServiceInt;
import com.rays.service.SupplierServiceInt;

@RestController
@RequestMapping(value = "Supplier")
public class SupplierCtl extends BaseCtl<SupplierForm, SupplierDTO, SupplierServiceInt> {

	@Autowired
	CategoryServiceInt categoryService;

	@Autowired
	SupplierServiceInt supplierService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Paras");
		ORSResponse res = new ORSResponse(true);
		CategoryDTO dto = new CategoryDTO();
		List<DropdownList> list = categoryService.search(dto, userContext);
		res.addResult("categoryList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createSupplier(@Valid @RequestBody SupplierForm supplierForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Supplier created successfully", HttpStatus.CREATED);
	}
}
