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
import com.rays.dto.AccountIdDTO;
import com.rays.dto.TransactionTypeDTO;
import com.rays.dto.TransactionsDTO;
import com.rays.form.TransactionsForm;
import com.rays.service.AccountIdServiceInt;
import com.rays.service.JobServiceInt;
import com.rays.service.TransactionTypeServiceInt;
import com.rays.service.TransactionsServiceInt;

@RestController
@RequestMapping(value = "Transactions")
public class TransactionsCtl extends BaseCtl<TransactionsForm, TransactionsDTO, TransactionsServiceInt> {

	@Autowired
	TransactionTypeServiceInt transactionTypeService;

	@Autowired
	AccountIdServiceInt accountIdService;
	
	@Autowired
	JobServiceInt jobService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		TransactionTypeDTO dto = new TransactionTypeDTO();
		AccountIdDTO dto1 = new AccountIdDTO();
		List<DropdownList> list = accountIdService.search(dto1, userContext);
		List<DropdownList> list1 = transactionTypeService.search(dto, userContext);
		res.addResult("accountIdList", list);
		res.addResult("transactionTypeList", list1);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createTransactions(@Valid @RequestBody TransactionsForm transactionsForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Transactions created successfully", HttpStatus.CREATED);
	}
}