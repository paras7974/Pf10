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
import com.rays.dto.InventoryDTO;
import com.rays.dto.ProductDTO;
import com.rays.form.InventoryForm;
import com.rays.service.InventoryServiceInt;
import com.rays.service.ProductServiceInt;

@RestController
@RequestMapping(value = "Inventory")
public class InventoryCtl extends BaseCtl<InventoryForm, InventoryDTO, InventoryServiceInt> {

	@Autowired
	ProductServiceInt productService;

	@Autowired
	InventoryServiceInt inventoryService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Paras");
		ORSResponse res = new ORSResponse(true);
		ProductDTO dto = new ProductDTO();
		List<DropdownList> list = productService.search(dto, userContext);
		res.addResult("productList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createInventory(@Valid @RequestBody InventoryForm inventoryForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Inventory created successfully", HttpStatus.CREATED);
	}
}