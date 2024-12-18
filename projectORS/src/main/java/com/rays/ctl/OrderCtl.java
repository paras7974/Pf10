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
import com.rays.dto.CustomerDTO;
import com.rays.dto.OrderDTO;
import com.rays.form.OrderForm;
import com.rays.service.CustomerServiceInt;
import com.rays.service.OrderServiceInt;

@RestController
@RequestMapping(value = "Order")
public class OrderCtl extends BaseCtl<OrderForm, OrderDTO, OrderServiceInt> {

	@Autowired
	CustomerServiceInt customerService;

	@Autowired
	OrderServiceInt orderService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		CustomerDTO dto = new CustomerDTO();
		List<DropdownList> list = customerService.search(dto, userContext);
		res.addResult("customerList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createOrder(@Valid @RequestBody OrderForm orderForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
	}
}
