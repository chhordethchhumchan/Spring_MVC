package com.mcnc.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mcnc.web.dao.CategoryMapper;
import com.mcnc.web.model.Category;

@Controller
@RequestMapping(value={"/category"})
public class CategoryController {
	
	@Autowired
	public CategoryMapper catMap;
	
	@RequestMapping(value={"/"},method= RequestMethod.GET)
	public String index(Model model){
		List<Category> listCategory = catMap.listCatergory();
		model.addAttribute("categorys",listCategory);
		return "category/index";
	}

}
