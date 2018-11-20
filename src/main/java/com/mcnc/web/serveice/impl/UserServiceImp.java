package com.mcnc.web.serveice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcnc.web.dao.UserDAO;
import com.mcnc.web.dto.UserDTO;
import com.mcnc.web.serveice.api.UserService;

@Service("UserService")
@Transactional
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	 
    private static List<UserDTO> users;
    
	@Override
	 public List<UserDTO> findAllUser() {
		
       return  userDAO.findAllUser();
	
    }

	@Override
	public UserDTO inquiryID(String id) {
		
	    return userDAO.findUserByID(id);
	       
	}

	@Override
	public int updateXUser(UserDTO userDTO) {
		
		return userDAO.update(userDTO);
	}

	@Override
	public boolean isUserExist(UserDTO userDTO) {
		 return inquiryID(userDTO.getId())!=null;
	}

	@Override
	public int saveXUser(UserDTO userDTO) {
		
		return userDAO.save(userDTO);
	}

	@Override
	public int deleteXUser(String id) {
		// TODO Auto-generated method stub
		return userDAO.delete(id);
	}
	
	@Override
	public void deleteXUsers(List<String> id) {
		
		  userDAO.deletes(id);
		
	}

	

}
