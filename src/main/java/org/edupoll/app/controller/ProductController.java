package org.edupoll.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.edupoll.app.model.Account;
import org.edupoll.app.model.NewProduct;
import org.edupoll.app.model.Pick;
import org.edupoll.app.model.Product;
import org.edupoll.app.model.ProductImage;
import org.edupoll.app.repository.PickRepository;
import org.edupoll.app.repository.ProductsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {
	private final ProductsRepository productsRepository;
	private final PickRepository pickRepository;

	@GetMapping("/{productId}")
	public String showProductDetail(@PathVariable int productId,
			@SessionAttribute(required = false) Account logonAccount, Model model) {
		Product found = productsRepository.findById(productId);
		int totalPick = pickRepository.countByTarget(productId);

		if (logonAccount == null) {
			model.addAttribute("picked", false);
		} else {
			Pick pick = Pick.builder().ownerAccountId(logonAccount.getId()).targetProductId(productId).build();
			int cnt = pickRepository.countByOwnerAndTarget(pick);
			model.addAttribute("picked", cnt == 1 ? true : false);
		}

		model.addAttribute("product", found);
		model.addAttribute("totalPick", totalPick);
		return "product/view";
	}

	@PostMapping("/pick")
	public String proceedAddPick(@RequestParam int targetProductId, @SessionAttribute Account logonAccount) {

		Pick one = Pick.builder() //
				.ownerAccountId(logonAccount.getId()) //
				.targetProductId(targetProductId) //
				.build();
		int cnt = pickRepository.countByOwnerAndTarget(one);
		if (cnt == 0)
			pickRepository.savePick(one);

		return "redirect:/product/" + targetProductId;
	}
	
	// 찜 등록 처리 하는 AJAX 처리 매핑
	@PostMapping("/pickAjax")
	@ResponseBody
	public String proceedAjaxAddPick(@RequestParam int targetProductId, @SessionAttribute Account logonAccount) {

		Pick one = Pick.builder() //
				.ownerAccountId(logonAccount.getId()) //
				.targetProductId(targetProductId) //
				.build();
		int cnt = pickRepository.countByOwnerAndTarget(one);
		if (cnt == 0)
			pickRepository.savePick(one);
		int updatedPicked = pickRepository.countByTarget(targetProductId);
		
		Map<String, Object> response = new HashMap<>();
		response.put("result", "success");
		response.put("updatePick", updatedPicked);
		
		Gson gson = new Gson();
		return gson.toJson(response);
	}
	

	@DeleteMapping("/pick")
	public String proceedRemovePick(@RequestParam int targetProductId, @SessionAttribute Account logonAccount) {

		Pick one = Pick.builder() //
				.ownerAccountId(logonAccount.getId()) //
				.targetProductId(targetProductId) //
				.build();

		pickRepository.deleteByOwnerAndTarget(one);

		return "redirect:/product/" + targetProductId;
	}


	@DeleteMapping("/pickAjax")
	@ResponseBody
	public String proceedAjaxRemovePick(@RequestParam int targetProductId, @SessionAttribute Account logonAccount) {

		Pick one = Pick.builder() //
				.ownerAccountId(logonAccount.getId()) //
				.targetProductId(targetProductId) //
				.build();

		pickRepository.deleteByOwnerAndTarget(one);
		int updatedPicked = pickRepository.countByTarget(targetProductId);
		Map<String, Object> response = new HashMap<>();
		response.put("result", "success");
		response.put("updatePick", updatedPicked);
		
		Gson gson = new Gson();
		return gson.toJson(response);
	}
	
	@GetMapping("/register")
	public String showProductReigster(Model model) {

		return "product/register";
	}

	@PostMapping("/register")
	public String proceedAddNewProduct(@ModelAttribute NewProduct newProduct, @SessionAttribute Account logonAccount,
			Model model) throws IllegalStateException, IOException {
		System.out.println(newProduct);
		// newProduct ==> Product 화 (세팅시켜야 할 값들..?)

		Product product = Product.builder() //
				.title(newProduct.getTitle()) //
				.type(newProduct.getType()) //
				.description(newProduct.getDescription()) //
				.price(newProduct.getPrice()) //
				.accountId(logonAccount.getId()) //
				.build();
		// dao 이용해서 save
		productsRepository.saveProdcut(product);
		// System.out.println(product.getId());

		for (MultipartFile file : newProduct.getImages()) {
			if (file.isEmpty())
				continue;
			File dir = new File("d:\\upload\\productImage\\" + product.getId());
			dir.mkdirs();
			String fileName = UUID.randomUUID().toString();
			File target = new File(dir, fileName);
			file.transferTo(target);

			ProductImage one = ProductImage.builder() //
					.url("/upload/productImage/" + product.getId() + "/" + fileName) //
					.path(target.getAbsolutePath()) //
					.productId(product.getId()) //
					.build();
			productsRepository.saveProdcutImage(one);
		}

		// newProduct안의 multipartFile 들을 업로드 처리하며 ProductImage 화
		// dao 이용해서 save

		return "redirect:/product/" + product.getId();
	}
}
