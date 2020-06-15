package com.papershare.papershare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import com.papershare.papershare.DTO.JwtResponseDTO;
import com.papershare.papershare.DTO.LoginDTO;
import com.papershare.papershare.DTO.UserDTO;
import com.papershare.papershare.model.TUser;
import com.papershare.papershare.repository.UserRepository;
import com.papershare.papershare.config.JwtTokenUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public TUser findOneByUsername(String username) {
		return userRepository.findOneByUsername("mira");
	}

	public boolean register(UserDTO dto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {

		if (userRepository.findOneByUsername(dto.getUsername()) != null) {
			return false;
		}
		TUser newUser = new TUser(dto.getUsername(), passwordEncoder.encode(dto.getPassword()), dto.getEmail(), dto.getFirstName(),
				dto.getLastName(), dto.getTitle());
		newUser.setRole("ROLE_USER");
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		newUser.setPassword(bc.encode(dto.getPassword()));
		String xmlFragment = userRepository.marshal(newUser);
		xmlFragment = xmlFragment.substring(55);
		System.out.println(xmlFragment);
		userRepository.addUser(xmlFragment);
		return true;
	}

	public JwtResponseDTO logIn(LoginDTO authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException | InternalAuthenticationServiceException e) {
			e.printStackTrace();
		}
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		System.out.println(jwt);
		JwtResponseDTO jwtDto = new JwtResponseDTO(jwt);
		
		return jwtDto;
	}

}
