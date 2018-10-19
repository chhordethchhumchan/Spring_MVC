package com.mcnc.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value={"/edit"},method= RequestMethod.GET)
	public String edit(Model model, @RequestParam("id") String id){
		Category categoryByCode = catMap.getCatergoryById(id);
		model.addAttribute("categoryById",categoryByCode);
		return "category/edit";
	}
	
	@RequestMapping(value= {"/update"}, method  = RequestMethod.POST)
	public String update(Model model, Category category) {
		catMap.updateById(category);
		return("redirect:/category/");
		
	}
	
	@RequestMapping(value= {"/create"})
	public String create() {
		
		return("category/add");
		
	}
	@RequestMapping(value= {"/save"}, method  = RequestMethod.POST)
	public String save(Model model, Category category) {
		catMap.insertCategory(category);
		return("redirect:/category/");
		
	}
	
	@RequestMapping(value={"/delete"},method= RequestMethod.DELETE)
	public String delete(Model model, @RequestParam("id") String id){
		System.out.println("Delete page");
		catMap.deleteById(id);
		return("redirect:/category/");
	
	}
}
