package com.user.management.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.management.controller.UserController;
import com.user.management.model.Address;
import com.user.management.model.User;
import com.user.management.repository.AddressRepository;
import com.user.management.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	
	static org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserServiceImpl.class);


	@Autowired
	private UserRepository UserRepository;
	
	
	@Autowired
	private AddressRepository addressRepository;

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUsers() {
		return UserRepository.findAll();
	}

	@Override
	public User saveUser(User User) {
		String password = User.getPassword();
		if (password!=null) {
			String encodedPassword = passwordEncoder.encode(User.getPassword());
			User.setPassword(encodedPassword);
		}
		com.user.management.model.User save = UserRepository.save(User);
		return User;
	}
	
	
	    
	@Override
	public User getUserById(long id) {
		Optional<User> optional = UserRepository.findById(id);
		
		User User = null;
		if (optional.isPresent()) {
			User = optional.get();
		} else {
			throw new RuntimeException(" User not found for id :: " + id);
		}
		return User;
	}
@Override
	public void deleteUserById(long id) {
	 Optional<User> optionalUser = UserRepository.findById(id);
     optionalUser.ifPresent(user -> {
         Boolean isActive = user.getIsactive();
         if(isActive) {
             user.setIsactive(false);
             UserRepository.save(user);
         }else {
        	 UserRepository.delete(user);
         }
     });
	}

	@Override
	public void loginpage(User user) {
			String email = user.getEmail();
			String password = user.getPassword();
	        // Validate email and password
	        if (isValidCredentials(email, password)) {
	            return;
	        } else {
	            throw new RuntimeException("Wrong email and password");
	        }
	    }

	    private boolean isValidCredentials(String email, String password) {
	        // Check if email and password match a user in the database
	        List<User> findByEmailandPassword = UserRepository.findByEmailandPassword(email,password);
	        if (findByEmailandPassword != null && !findByEmailandPassword.isEmpty()) {
	            return true;
	        } else {
	            return false;
	        }
	    }

		@Override
		public void restoreUserById(long id) {
			Optional<User> optionalUser = UserRepository.findById(id);
		     optionalUser.ifPresent(user -> {
			user.setIsactive(true);
			UserRepository.save(user);
		     });
		}
}
