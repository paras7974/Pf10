package com.rays.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.UserDTO;

public abstract class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {

	protected static final String OP_SAVE = "Save";
	protected static final String OP_NEW = "New";
	protected static final String OP_DELETE = "Delete";
	protected static final String OP_CANCEL = "Cancel";
	protected static final String OP_ERROR = "Error";
	protected static final String OP_NEXT = "Next";
	protected static final String OP_PREVIOUS = "Previous";
	protected static final String OP_LOGOUT = "Logout";
	protected static final String OP_GO = "Go";
	protected static final String OP_GET = "Get";

	@Autowired
	protected S baseService;

	@Value("${page.size}")
	private int pageSize = 0;

	protected UserContext userContext = null;

	@ModelAttribute
	public void setUserContext(HttpSession session) {
		System.out.println("inside setUserContext  inside BaseCtl --");
		userContext = (UserContext) session.getAttribute("userContext");
		if (userContext == null) {
			UserDTO dto = new UserDTO();
			dto.setLoginId("root@sunilos.com");
			dto.setFirstName("demo firstName");
			dto.setLastName("demo lastName");
			dto.setOrgId(0L);
			dto.setRoleId(1L);
			dto.setOrgName("root");
			userContext = new UserContext(dto);
		}
	}

	@GetMapping
	public ORSResponse get() {
		System.out.println("BaseCtl Get() method run");
		ORSResponse res = new ORSResponse(true);
		res.addData("I am okay " + this.getClass() + " --" + new Date());
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		System.out.println("BaseCtl Get() method run.......Paras");
		ORSResponse res = new ORSResponse(true);
		T dto = baseService.findById(id, userContext);

		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		System.out.println("Edit response :" + res);
		return res;
	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		System.out.println("BaseCtl Delete() method run........Paras");
		ORSResponse res = new ORSResponse(true);
		try {
			T dto = baseService.delete(id, userContext);
			res.addData(dto);
			System.out.println("Record Deleted Successfully");
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}
		return res;
	}

	@PostMapping("deleteMany/{ids}")
	public ORSResponse deleteMany(@PathVariable String[] ids, @RequestParam("pageNo") String pageNo,
			@RequestBody F form) {
		System.out.println("BaseCtl DeleteMany() method....Paras... run");
		ORSResponse res = new ORSResponse(true);
		try {
			for (String id : ids) {
				System.out.println("Records To be Deleted :: " + id);
				baseService.delete(Long.parseLong(id), userContext);
			}
			T dto = (T) form.getDto();
			List<T> list = baseService.search(dto, Integer.parseInt(pageNo), pageSize, userContext);
			res.addData(list);
			res.setSuccess(true);
			res.addMessage("Records Deleted Successfully");
			System.out.println("Records Deleted Successfully by Paras");
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody F form) {
		System.out.println("BaseCtl Search Running");
		String operation = form.getOperation();
		int pageNo = form.getPageNo();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}

		pageNo = (pageNo < 0) ? 0 : pageNo;
		form.setPageNo(pageNo);
		System.out.println("Page No is :: " + pageNo + "   Page size is :: " + pageSize);
		T dto = (T) form.getDto();
		ORSResponse res = new ORSResponse(true);
		res.addData(baseService.search(dto, pageNo, pageSize, userContext));
		return res;
	}

	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody F form, @PathVariable int pageNo) {
		System.out.println("BaseCtl Search method with pageNo :: " + pageNo + "   Page size is :: " + pageSize);
		pageNo = (pageNo < 0) ? 0 : pageNo;
		System.out.println("Operation :: " + form.getOperation());
		T dto = (T) form.getDto();
		ORSResponse res = new ORSResponse(true);
		res.addData(baseService.search(dto, pageNo, pageSize, userContext));
		List nextList = baseService.search(dto, pageNo + 1, pageSize, userContext);
		res.addResult("nextList", nextList.size());
		return res;
	}

	@PostMapping("/save")
	public ORSResponse save(@RequestBody @Valid F form, BindingResult bindingResult) {
		System.out.println("228save() run in BaseCtl :: +Paras " + form);
		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}
		try {
			T dto = (T) form.getDto();
			if (dto.getId() != null && dto.getId() > 0) {
				T existDto1 = (T) baseService.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);
				if (existDto1 != null && !dto.getId().equals(existDto1.getId())) {
					res.addMessage("Login Id already exist");
					res.setSuccess(false);
					return res;
				}
				baseService.update(dto, userContext);
			} else {
				if (dto.getUniqueKey() != null && !dto.getUniqueKey().isEmpty()) {
					T existDto = (T) baseService.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);
					if (existDto != null) {
						res.addMessage("Login Id already exist");
						res.setSuccess(false);
						return res;
					}
				}
				baseService.add(dto, userContext);
			}
			res.addData(dto.getId());
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	public ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse(true);
		if (bindingResult.hasErrors()) {
			res.setSuccess(false);
			Map<String, String> errors = new HashMap<>();
			List<FieldError> list = bindingResult.getFieldErrors();
			list.forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
			res.addInputErrors(errors);
		}
		return res;
	}
}
