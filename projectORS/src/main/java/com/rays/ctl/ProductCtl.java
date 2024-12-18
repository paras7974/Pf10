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
import com.rays.dto.ProductDTO;
import com.rays.form.ProductForm;
import com.rays.service.ProductServiceInt;

@RestController
@RequestMapping(value = "Product")
public class ProductCtl extends BaseCtl<ProductForm, ProductDTO, ProductServiceInt>  {
	
	@Autowired
	private ProductServiceInt productService;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload");
		ORSResponse res = new ORSResponse(true);
    	ProductDTO dto = new ProductDTO();
		List<DropdownList> list = productService.search(dto, userContext);
		res.addResult("productList", list);
		return res;
	}
	
	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		ProductDTO dto = baseService.findByName(name, userContext);
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
