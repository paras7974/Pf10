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
import com.rays.dto.TransactionTypeDTO;
import com.rays.form.TransactionTypeForm;
import com.rays.service.TransactionTypeServiceInt;

@RestController
@RequestMapping(value = "TransactionType")
public class TransactionTypeCtl extends BaseCtl<TransactionTypeForm, TransactionTypeDTO, TransactionTypeServiceInt> {

	@Autowired
	private TransactionTypeServiceInt transactionTypeService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload");
		ORSResponse res = new ORSResponse(true);
		TransactionTypeDTO dto = new TransactionTypeDTO();
		List<DropdownList> list = transactionTypeService.search(dto, userContext);
		res.addResult("transactionTypeList", list);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		TransactionTypeDTO dto = baseService.findByName(name, userContext);
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
