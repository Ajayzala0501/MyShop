package com.projects.myshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.myshop.enitity.ProductDetailsEntity;

@Controller
@RequestMapping("/ViewProduct")
public class ProductViewPageController {
	
	@Autowired
	ManageProductController controller;
	
	@GetMapping("/addNewProductPage")
	public String addNewProduct(Model model,HttpServletRequest request) {
		String prodId =  request.getParameter("hide-proid");
		if(prodId != null) {
			String btnCheck = request.getParameter("submit");
			if(btnCheck.equals("edit-btn") && btnCheck !=null) {
				ProductDetailsEntity pdDetailsEntity =  controller.getProductByIdAndUserName(prodId, request);
				model.addAttribute("PdDetails",pdDetailsEntity);
				return "addNewProductPage";	
			}else {
				controller.deleteProductById(prodId, request);
				return "searchProductPage";
			}	
		}else {
			model.addAttribute("PdDetails",new ProductDetailsEntity());
			return "addNewProductPage";
		}	
	}
	@GetMapping("/searchProductPage")
	public String searchProductPage() {
		return "searchProductPage";
	}
	
	@GetMapping("/manageProductStockPage")
	public String manageProductStockPage() {	
		return "manageProductStockPage";
	}
	
	@GetMapping("/createNewInvoicePage")
	public String createNewInvoicePage() {
		return "createNewInvoicePage";
	}
	
	@GetMapping("/generateInvoicePage")
	public String generateInvoicePage() {
		return "generateInvoicePage";
	}

}
