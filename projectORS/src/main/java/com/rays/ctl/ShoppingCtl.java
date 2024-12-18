package com.rays.ctl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.common.attachment.AttachmentDTO;
import com.rays.common.attachment.AttachmentServiceInt;
import com.rays.dto.PaymentDTO;
import com.rays.dto.ShoppingDTO;
import com.rays.form.ShoppingForm;
import com.rays.service.PaymentServiceInt;
import com.rays.service.ShoppingServiceInt;

@RestController
@RequestMapping(value = "Shopping")
public class ShoppingCtl extends BaseCtl<ShoppingForm, ShoppingDTO, ShoppingServiceInt> {

	@Autowired
	PaymentServiceInt paymentService = null;

	@Autowired
	ShoppingServiceInt shoppingService;

	@Autowired
	AttachmentServiceInt attachmentService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		PaymentDTO dto = new PaymentDTO();
		List<DropdownList> list = paymentService.search(dto, userContext);
		res.addResult("paymentList", list);
		return res;
	}

	/**
	 * Uploads user profile picture of logged in user.
	 * 
	 * @param id
	 * @param file
	 * @return
	 */
	@PostMapping("/profilePic")
	public ORSResponse uploadPic(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		return uploadPic(userContext.getUserId(), file, req);
	}

	/**
	 * Uploads profile picture of given user id
	 * 
	 * @param userId
	 * @param file
	 * @param req
	 * @return
	 */

	// user page se jab image upload krte hai tab ye method chalti hai

	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		System.out.println("User ID id --------------Paras Mahajan" + userId);

		ShoppingDTO shoppingDTO = baseService.findById(userId, userContext);

		AttachmentDTO doc = new AttachmentDTO(file);

		doc.setDescription("Profile picture");
		System.out.println(doc.getDescription() + "description");

		doc.setPath(req.getServletPath());
		System.out.println(doc.getPath() + "path-----rahul");

		doc.setUserId(userId);
		System.out.println(doc.getUserId() + "id-----rahul");

		if (shoppingDTO.getImageId() != null && shoppingDTO.getImageId() > 0) {
			doc.setId(shoppingDTO.getImageId());
		}
		System.out.println("before calling save");

		Long imageId = attachmentService.save(doc, userContext);

		System.out.println("after save");

		// Update new image id

		if (shoppingDTO.getImageId() == null || shoppingDTO.getImageId() == 0) {
			shoppingDTO.setImageId(imageId);
			baseService.update(shoppingDTO, userContext);
		}

		ORSResponse res = new ORSResponse();
		res.setSuccess(true);
		res.addResult("imageId", imageId);

		return res;
	}

	/**
	 * Downloads profile picture of logged in user
	 * 
	 * @param response
	 */
	@GetMapping("/profilePic")
	public @ResponseBody void downloadPic(HttpServletResponse response) {
		downloadPic(userContext.getUserId(), response);
	}

	/**
	 * Downloads profile picture of given user id
	 * 
	 * @param userId
	 * @param response
	 */
	@GetMapping("/profilePic/{userId}")
	public @ResponseBody void downloadPic(@PathVariable Long userId, HttpServletResponse response) {

		ShoppingDTO shoppingDTO = baseService.findById(userId, userContext);
		AttachmentDTO attachmentDTO = attachmentService.findById(shoppingDTO.getImageId(), userContext);
		try {
			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();

				System.out.println("Profile pic......rahul");
			} else {
				response.getWriter().write("ERROR: File not found");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Uploads a document for a user
	 * 
	 * @param id
	 * @param file
	 * @return
	 */
	@PostMapping("/doc/{userId}")
	public ORSResponse upload(@PathVariable Long userId, @RequestParam(required = false) String description,
			@RequestParam("file") MultipartFile file, HttpServletRequest req) {

		AttachmentDTO doc = new AttachmentDTO(file);
		doc.setDescription(description);
		doc.setPath(req.getServletPath());
		doc.setUserId(userId);

		Long pk = attachmentService.save(doc, userContext);

		ORSResponse res = new ORSResponse();
		res.setSuccess(true);
		res.addResult("docId", pk);

		return res;
	}

	/**
	 * Downloads user document
	 * 
	 * @param id
	 * @param response
	 */
	@GetMapping("/doc/{id}")
	public @ResponseBody void download(@PathVariable Long id, HttpServletResponse response) {

		AttachmentDTO attachmentDTO = attachmentService.findById(id, userContext);
		try {
			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				response.getWriter().write("ERROR: File not found");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
