package com.mcnc.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcnc.web.dto.User;

@Repository
public interface UserService {

	List<User> findAllUser();
}
