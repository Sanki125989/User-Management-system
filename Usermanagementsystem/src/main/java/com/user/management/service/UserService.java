package com.user.management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.user.management.model.User;


public interface UserService {
	List<User> getAllUsers();
	User getUserById(long id);
	void deleteUserById(long id);
	void loginpage(User user);
	User saveUser(User user);
	void restoreUserById(long id);
}
