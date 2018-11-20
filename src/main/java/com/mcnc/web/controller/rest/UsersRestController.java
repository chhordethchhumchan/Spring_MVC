package com.mcnc.web.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mcnc.web.dto.UserDTO;
import com.mcnc.web.serveice.impl.UserServiceImp;

@RestController
@RequestMapping(value={"/api/user"})
public class UsersRestController {
	
	@Autowired
	private UserServiceImp userServiceImp;
	

	@RequestMapping(value={"/listUser"},method= RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listUser(){
		Map<String, Object> data = new ConcurrentHashMap<String, Object>();
		try {
			data.put("data",userServiceImp.findAllUser());
		}catch (Exception e) {
			data.put("data", null);
			data.put("count", 0);
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping(value={"/userlist"},method= RequestMethod.GET)
	@ResponseBody
	public List<UserDTO> user(){
		List<UserDTO> users = userServiceImp.findAllUser();
		
		return users;
	}



	@RequestMapping(value= {"/getUserByID"},method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserByID(@RequestParam String id){
		UserDTO user= userServiceImp.inquiryID(id);
		if(user==null) {
		  return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
//------------------- Update a User --------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") String id, @RequestBody UserDTO user) {
        System.out.println("================================Updating User========================== " + id);
         
   
        UserDTO currentUser =userServiceImp.inquiryID(id);
        
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(user.getName());
        userServiceImp.updateXUser(currentUser);
        
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }
	
//------------------- Create a User --------------------------------------------------------
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO, UriComponentsBuilder ucBuilder) {
      
        
        if (userServiceImp.isUserExist( userDTO)) {
        
        	return new ResponseEntity<UserDTO>(HttpStatus.CONFLICT);
        }
        userServiceImp.saveXUser(userDTO);
        
       
    
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }
	
    
	
  //------------------- Delete a User --------------------------------------------------------
      
      @RequestMapping(value = "/delete", method = RequestMethod.POST)
      public ResponseEntity<String> deleteUser(@RequestParam String id) {
                  
          if (id == null) {
          
          	return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
          }
          
          userServiceImp.deleteXUser(id);

          return new ResponseEntity<String>( HttpStatus.NO_CONTENT);
      }
      
      @RequestMapping(value = "/delete_lists", method = RequestMethod.POST)
      public ResponseEntity<String> deleteUsers( @RequestBody List<String> id) {
                  
    	  if (id == null) {
              
            	return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            }
            
          userServiceImp.deleteXUsers(id);

          return new ResponseEntity<String>("Done",HttpStatus.NO_CONTENT);

        
      }
      
  	@PostMapping("/delete_list")
  	public ResponseEntity<String> deleteLinkParameterList( @RequestBody  List<String> id) {
  		 userServiceImp.deleteXUsers(id);		
  	    return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
  	}
  	
}
