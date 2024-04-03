package com.main.screenflicks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.main.screenflicks.models.Movie;
import com.main.screenflicks.models.User;
import com.main.screenflicks.services.UserService;

import utils.CustomizedResponse;

import java.util.Collections;
import java.util.List;

@Controller
public class UserController

{

	@Autowired
    private UserService userService;

	 @Autowired
	    @Qualifier("passwordEncoder")
	    private PasswordEncoder passwordEncoder;
	
    @GetMapping("/users")
    public ResponseEntity<CustomizedResponse<List<User>>> getUsers()
    {

        var response = new CustomizedResponse<List<User>>( " A list of all users ", userService.getUsers());

        return new ResponseEntity<CustomizedResponse<List<User>>>( response, HttpStatus.OK);
    }

    @PostMapping(value = "/user", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<CustomizedResponse<User>> getUser(@RequestBody User user)
    {
    	User returnedUser = userService.getUser(user);
    	
    	if(returnedUser == null) {
    		System.out.println("returnedUser is null");
    		CustomizedResponse<User> errorResponse = new CustomizedResponse<>("user not found", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    	}
    	var response = new CustomizedResponse<User>( "success", null);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping(value = "/users", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<CustomizedResponse<List<User>>> createUsers(@RequestBody User user)
    {

     var response = new CustomizedResponse<List<User>>( "success", Collections.singletonList(userService.addUser(user)));

     return new ResponseEntity<CustomizedResponse<List<User>>>( response, HttpStatus.OK);

    }
}
