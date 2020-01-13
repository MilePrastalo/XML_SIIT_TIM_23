package com.papershare.papershare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.papershare.papershare.model.TUser;
import com.papershare.papershare.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public TUser findOneByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}
	

}
