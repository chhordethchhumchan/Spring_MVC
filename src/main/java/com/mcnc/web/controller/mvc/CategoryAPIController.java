package com.mcnc.web.controller.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mcnc.web.dao.CategoryMapper;
import com.mcnc.web.dao.UserDAO;
import com.mcnc.web.dto.Category;
import com.mcnc.web.dto.UserDTO;

@RestController
public class CategoryAPIController {
	@Autowired
	
	private UserDAO userService;

	@Autowired
	public CategoryMapper catMap;
	
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testapi() {
    	return "hello";
    }
    
    @RequestMapping(value ="/list", method = RequestMethod.GET)
	public List<UserDTO> index(){
    	
		List<UserDTO> user = userService.findAllUser();
	
		return user;
	}
    
    
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> listAllUsers() {
        List<UserDTO> users = userService.findAllUser();
        if(users.isEmpty()){
            return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }
 
	
	
	@RequestMapping(value="/listcate", method=RequestMethod.GET)
	public @ResponseBody List<Category> listcate(@RequestParam String name) {
		
				List<Category> listCategory = catMap.listCatergory();
		
				return listCategory;
	  }
	

	@RequestMapping(value={"/edit"},method= RequestMethod.GET)
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
	

	
    
 


	
	
}
