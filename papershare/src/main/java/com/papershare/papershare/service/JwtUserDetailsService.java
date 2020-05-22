package com.papershare.papershare.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.papershare.papershare.model.TUser;
import com.papershare.papershare.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	// Method gets the User with given username from database
	// if user with that username doesn't exists the method will throw exception
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TUser user = userRepository.findOneByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
			Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
			authorities.add(authority);

			return new User(user.getUsername(), user.getPassword(), authorities);
		}
	}

}
