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
import com.rays.dto.FieldDTO;
import com.rays.dto.TypeDTO;
import com.rays.form.FieldForm;
import com.rays.service.FieldServiceInt;
import com.rays.service.TypeServiceInt;

@RestController
@RequestMapping(value = "Field")
public class FieldCtl extends BaseCtl<FieldForm, FieldDTO, FieldServiceInt> {

	@Autowired
	TypeServiceInt typeService;

	@Autowired
	FieldServiceInt FieldService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		TypeDTO dto = new TypeDTO();
		List<DropdownList> list = typeService.search(dto, userContext);
		res.addResult("typeList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createField(@Valid @RequestBody FieldForm fieldForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Field created successfully", HttpStatus.CREATED);
	}
}
