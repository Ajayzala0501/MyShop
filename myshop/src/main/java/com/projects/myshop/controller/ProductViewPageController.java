package com.projects.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ViewProduct")
public class ProductViewPageController {
	@GetMapping("/addNewProductPage")
	public String addNewProduct() {
		return "addNewProductPage";
	}
	@GetMapping("/searchProductPage")
	public String searchProductPage() {
		return "searchProductPage";
	}
}
