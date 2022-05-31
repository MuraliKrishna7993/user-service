package com.ecommerce.UserService.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.UserService.entity.User;
import com.ecommerce.UserService.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/SignUp")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		try {
			user = userService.createUser(user);

			return new ResponseEntity<User>(user, HttpStatus.CREATED);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> listAllUsers() {
		try {

			List<User> users = userService.listUsers();

			return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
		try {
			User user = userService.getSingleUser(userId);
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer userId) {
		User user = null;
		try {
			user = userService.getSingleUser(userId);
			userService.deleteUser(userId);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User user) {

		try {
			User existUser = userService.getSingleUser(userId);

			existUser.setUserName(user.getUserName());
			existUser.setEmail(user.getEmail());
			existUser.setPassword(user.getPassword());
			existUser.setAddress(user.getAddress());
			existUser.setPhone(user.getPhone());

			User updateUser = userService.editUser(existUser);

			return new ResponseEntity<User>(updateUser, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);

		}

	}

}
