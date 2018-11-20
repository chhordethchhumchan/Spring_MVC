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
  
  int deletes(List<String> id);
  
  int delete(String id);
	
  boolean isUserExist(UserDTO user);
}
