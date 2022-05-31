package com.ecommerce.UserService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.UserService.entity.Address;
import com.ecommerce.UserService.entity.User;
import com.ecommerce.UserService.service.UserService;

@SpringBootTest(classes = { UserControllerTest.class })
public class UserControllerTest {

	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	User user;
	
	@Test
	@Order(1)
	public void test_saveUser() {

		user = new User(1,"User1@gmail.com","User1","Pwd",new Address("line1","line2","city1",1234,"dist","state","Country"),401L);

		when(userService.createUser(user)).thenReturn(user);

		ResponseEntity<User> res = userController.saveUser(user);

		assertEquals(HttpStatus.CREATED, res.getStatusCode());

		assertEquals(user, res.getBody());

	}
}
