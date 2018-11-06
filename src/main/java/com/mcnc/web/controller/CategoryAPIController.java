package com.mcnc.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mcnc.web.dao.CategoryMapper;
import com.mcnc.web.dto.Category;

@RestController
public class CategoryAPIController {


	@Autowired
	public CategoryMapper catMap;
	

	
	
	@RequestMapping(value="/listcate", method=RequestMethod.GET)
	public @ResponseBody List<Category> listcate(@RequestParam String name) {
		
				List<Category> listCategory = catMap.listCatergory();
		
				return listCategory;
	  }
	
	
}
