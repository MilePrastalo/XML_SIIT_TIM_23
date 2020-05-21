package com.papershare.papershare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.papershare.papershare.DTO.JwtResponseDTO;
import com.papershare.papershare.DTO.LoginDTO;
import com.papershare.papershare.DTO.UserDTO;
import com.papershare.papershare.model.TUser;
import com.papershare.papershare.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	public UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public @ResponseBody ResponseEntity<Boolean> register(@RequestBody UserDTO dto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {

		boolean result = userService.register(dto);
		if (result) {
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(result, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public @ResponseBody ResponseEntity<JwtResponseDTO> login(@RequestBody LoginDTO dto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		JwtResponseDTO jwt = userService.logIn(dto);
		if (jwt != null) {
			return new ResponseEntity<JwtResponseDTO>(jwt, HttpStatus.OK);
		}
		return new ResponseEntity<JwtResponseDTO>(jwt, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/testFindByUsername", method = RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize("hasAuthority('USER')")
	public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {

		String username = "mira";
		TUser user = userService.findOneByUsername(username);
		if (user == null) {
			System.out.println("USER DOESN'T EXIST");
		} else {
			System.out.println("\tFIRST NAME = " + user.getFirstName());
			System.out.println("\tLAST NAME = " + user.getLastName());
		}
	}
}
