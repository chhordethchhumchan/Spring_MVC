package com.mcnc.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcnc.web.dto.UserDTO;

@Repository
public interface UserDAO {

   List<UserDTO> findAllUser();
	
  UserDTO findUserByID(String id);
	
  int update(UserDTO user);
  
  int save(UserDTO user);
  
  int delete(UserDTO user);
	
  boolean isUserExist(UserDTO user);
}
