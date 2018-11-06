package com.mcnc.web.serveiceImp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcnc.web.dao.UserService;
import com.mcnc.web.dto.User;

@Service("UserService")
@Transactional
public class UserServiceImp implements UserService {
	 private static List<User> users;
	 
	@Override
	 public List<User> findAllUser() {
        return users;
    }


}
