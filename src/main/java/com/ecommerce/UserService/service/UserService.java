package com.ecommerce.UserService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.UserService.entity.User;
import com.ecommerce.UserService.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public User createUser(User user) {

		return userRepo.insert(user);

	}

	public List<User> listUsers() {

		return userRepo.findAll();

	}

	public User getSingleUser(Integer userId) {

		Optional<User> user = userRepo.findById(userId);

		if (user.isPresent()) {

			return user.get();

		} else {

			throw new RuntimeException("User Not Found");

		}

	}

	public void deleteUser(Integer userId) {

		userRepo.deleteById(userId);

	}

	public User editUser(User user) {

		return userRepo.save(user);

	}

}
