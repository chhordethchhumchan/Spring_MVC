package com.mcnc.web.serveice.api;

import java.util.List;

import com.mcnc.web.dto.UserDTO;

public interface UserService {

	
	public List<UserDTO> findAllUser();
	
	UserDTO inquiryID(String id);
	
	int updateXUser(UserDTO userDTO);
	
	int saveXUser(UserDTO userDTO);
	
	int deleteXUser(String id);
	
	void deleteXUsers(List<String> id);
		
	public boolean isUserExist(UserDTO userDTO);
	
}