package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

import com.crm.dto.UserDto;
import com.crm.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	//add
	@PostMapping("")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
		System.out.println("Inside controller -->"+userDto);
		return new ResponseEntity<UserDto>(userService.addUser(userDto), HttpStatus.CREATED);
	}
	
	//fetch user
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
		System.out.println("UserId in controller -->"+id);
		return new ResponseEntity<UserDto>(userService.getUser(id), HttpStatus.OK);
	}
	
	//update 
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody UserDto newValues){
		return new ResponseEntity<>(userService.updateUser(id, newValues), HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
	}
	
}
