package com.user.management;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.management.model.User;
import com.user.management.repository.UserRepository;
import com.user.management.service.UserService;

@SpringBootTest
class Savemethod {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository repository;
	
	 @Test
	    void testSaveUser() {
	        // Create a new user
			LocalDate date=LocalDate.now();

	        User user = new User(1L,"Sanket","Shinde","s@gmail.com",date, "9097876767");

	        // Mock the userRepository's save() method to return the same user object that was passed in
	        when(repository.save(user)).thenReturn(user);

	        // Save the user using the userService
	            User saveUser = userService.saveUser(user);
	         
	        // Assert that the savedUser matches the original user's properties
	        assertEquals("Sanket", saveUser.getFirstName());
	        assertEquals("Shinde", saveUser.getLastName());
	        assertEquals("s@gmail.com", saveUser.getEmail());
	        assertEquals("9097876767", saveUser.getMobileno());

	        // Verify that the userRepository's save() method was called once with the user object
	        verify(repository, times(1)).save(user);
	        
	        
	        //save user--1)create object of user(objUser1)
	        //2) assign the test data
	        //3) save to DB from controller 
	        //4) in return get the user ID generated (new)
	        //5) create a new user object (objUser2)
	        //6) read the user object using get service
	        //7) compare (objUser1 and objUser2)
	        //result of test case
	    }	 
	 @Test
	    public void testGetAllUsers() {
	        // Given
			LocalDate date=LocalDate.now();

			List<User> users = Arrays.asList(
				    new User(1L,"Sanket","Shinde","s@gmail.com",date, "9097876767"),
				    new User(2L,"Sanket","Shinde","s@gmail.com",date, "9097876767"));	
			when(repository.findAll()).thenReturn(users);
	        // When
	        List<User> result = userService.getAllUsers();

	        // Then
	        assertEquals(users, result);
	        verify(repository, times(1)).findAll();
	    }
	 
	    @Test
	    public void testGetUserById() {
	        // Given
			LocalDate date=LocalDate.now();
	        User user = new User(1L,"Sanket","Shinde","s@gmail.com",date, "9097876767");
	        when(repository.findById(1L)).thenReturn(Optional.of(user));

	        // When
	        User result = userService.getUserById(1L);

	        // Then
	        assertEquals(user, result);
	        verify(repository, times(1)).findById(1L);
	    }
	    
	    @Test
	    public void testDeleteUser() {
	        // arrange
	    	  long id = 1L;
	          User user = new User();
	          user.setId(id);
	          user.setIsactive(true);
	          Optional<User> optionalUser = Optional.of(user);

	          when(repository.findById(id)).thenReturn(optionalUser);

	          // act
	          userService.deleteUserById(id);

	          // assert
	          verify(repository, times(1)).findById(id);
	          verify(repository, times(1)).save(user);
	          assertFalse(user.getIsactive());
	      }
	    
	    @Test
	    public void testUpdateUser() {
	        // Create a new user to be updated
	        User user = new User();
	        user.setFirstName("johndoe");
	        user.setPassword("password");
	        user.setEmail("johndoe@example.com");

	        // Save the user to the database
	        user = userService.saveUser(user);

	        // Update the user's full name and email
	        user.setFirstName("sanket shinde");
	        user.setEmail("janedoe@example.com");

	        // Call the update method and get the updated user object
	        User updatedUser = userService.saveUser(user);

	        // Verify that the user object was updated correctly
	        assertNotNull(updatedUser);
	        assertEquals("sanket shinde", updatedUser.getFirstName());
	        assertEquals("janedoe@example.com", updatedUser.getEmail());
	    }
	    
	 
	    @Test
	    public void testMathadition() {
	        MathClass obj = new MathClass();
	        int result = obj.addition(2, 3);
	        assertEquals(5, result);
	    }
	   

	    

}
