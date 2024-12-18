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
import com.rays.dto.PaymentDTO;
import com.rays.form.PaymentForm;
import com.rays.service.PaymentServiceInt;

@RestController
@RequestMapping(value = "Payment")
public class PaymentCtl  extends BaseCtl<PaymentForm, PaymentDTO, PaymentServiceInt>  {
	
	@Autowired
	private PaymentServiceInt paymentService;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload");
		ORSResponse res = new ORSResponse(true);
    	PaymentDTO dto = new PaymentDTO();
		List<DropdownList> list = paymentService.search(dto, userContext);
		res.addResult("paymentList", list);
		return res;
	}
	
	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		PaymentDTO dto = baseService.findByName(name, userContext);
		System.out.println("Payment " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}


}
