package org.edupoll.app.controller;

import java.util.List;

import org.edupoll.app.model.Product;
import org.edupoll.app.repository.ProductsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	private final ProductsRepository productsRepository;
	
	
	@GetMapping({ "/", "/index" })
	public String showWelcome(Model model) {
	
		List<Product> list = productsRepository.findAllOrderByIdDesc();
		model.addAttribute("products", list);
		
		return "index";
	}
}
