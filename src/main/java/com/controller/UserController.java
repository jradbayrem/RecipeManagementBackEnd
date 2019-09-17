package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.UserService;

@RestController  //@Component + @Path
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8034")		//Eviter le probleme de cross, les requetes du port 8034
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping(value = "/adduser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User addNewUser(@RequestBody User user) {		//User doit eter envoyé comme parametre
		System.out.println(user.getUserName());
		return this.userService.addUser(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser (@RequestBody User user, @PathVariable long id) {
		if (userService.getUserById(id) == null) {
			return null;
		}
		else 
			return userService.addUser(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) {
		if (userService.getUserById(id) != null) {
			userService.deleteUser(userService.getUserById(id));
		}
	}
}
