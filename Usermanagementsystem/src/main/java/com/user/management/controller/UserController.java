package com.user.management.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.management.model.Address;
import com.user.management.model.User;
import com.user.management.repository.UserRepository;
import com.user.management.service.UserService;

@Controller
public class UserController {
	
	static org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserController.class);
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository UserRepository;
	


	// display list of Users
	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<User> findAllactiveUser = UserRepository.getAllActiveUsers();
		model.addAttribute("listUsers", findAllactiveUser);
		return "index";
	}
	
	//display the add user form
	@GetMapping("/showaddUserForm")
	public String showNewUserForm(Model model) {
		// create model attribute to bind form data
		User User = new User();
		model.addAttribute("User", User);
		return "new_User";
	}

	
	//display the in-activelist
	@GetMapping("/in-activelist")
	public String showinActivelist(Model model) {
		List<com.user.management.model.User> allActiveUsers = UserRepository.getAllInActiveUsers();
		model.addAttribute("inactive", allActiveUsers);
		return "in-activeusers";
	}
	
	//save and update new user  
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("User") User User ,@RequestParam("permanetAdd") String permanetAdd, 
			@RequestParam("currentAdd") String currentAdd,HttpSession session){
		   try {
				boolean exist = UserRepository.existsByEmailAndMobileNumberIsActive(User.getEmail(),User.getMobileno());
				if (exist){
					session.setAttribute("msg", "Please Enter Email and Mobile Number Unique");
					return "new_User";
				}else {	
					List<Address> address = User.getAddress();
				    address.get(0).setAddresstype(permanetAdd);
				    address.get(1).setAddresstype(currentAdd);
				    String line = address.get(1).getLine();
				    String street = address.get(1).getStreet();				    
				    if(line==null || line.equals("")) {
				    	throw new NullPointerException("cannot saved without current address");
				    }
					userService.saveUser(User);
			        return "redirect:/";  
				}

		   }catch(DataIntegrityViolationException ex){
			   session.setAttribute("msg", "Please Enter Email and Mobile Number Unique");
				return "new_User";
		   }
		
	}

	//for json response (postman) 
	@PostMapping("/saveUserInJakson")
	public ResponseEntity<String> saveUser1(@RequestBody User User){
		   try {
	            userService.saveUser(User);
	            return ResponseEntity.ok().body("User saved successfully");
	        } catch (ConstraintViolationException ex) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user data");
	        } catch (DataIntegrityViolationException ex) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email address is already in use");
	        }
	}
	
	//display update form onclick of update
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
		
		// get User from the service
		User User = userService.getUserById(id);
		// set User as a model attribute to pre-populate the form
		model.addAttribute("User", User);
		return "update_User";
	}
	
	//soft delete user from list
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable (value = "id") long id,HttpSession session){
		       userService.deleteUserById(id);
		       boolean empty = UserRepository.findById(id).isEmpty();
		       if(empty) {
			       session.setAttribute("msg", "User is deleted permantly");
		    	   return "redirect:/in-activelist";
		       }
		       logger.info("emty:::while soft and parmant:"+empty);
		       session.setAttribute("msg", "User is deleted");
		       return "redirect:/";
	}
	@GetMapping("/restorefromdelete/{id}")
	public String restoreDeletedUser(@PathVariable (value = "id") long id,HttpSession session){
		       userService.restoreUserById(id);
			   session.setAttribute("msg", "User is Restored Successfully");
		       return "redirect:/";
	}
}
