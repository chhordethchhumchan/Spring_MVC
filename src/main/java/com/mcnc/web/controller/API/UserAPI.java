package com.mcnc.web.controller.API;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mcnc.web.dao.CategoryMapper;
import com.mcnc.web.dao.UserService;
import com.mcnc.web.dto.Category;
import com.mcnc.web.dto.User;

@RestController
@RequestMapping(value={"/user"})
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public CategoryMapper catMap;
	
	
	@RequestMapping(value={"/listUserMap"},method= RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> edit(){
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			data.put("data",userService.findAllUser());
		}catch (Exception e) {
			data.put("data", null);
			data.put("count", 0);
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping(value= {"/testuser"},method = RequestMethod.GET)
	public List<User> user(){
		List<User> user= userService.findAllUser();
		return user;
	}
	
	
	

	    
}
