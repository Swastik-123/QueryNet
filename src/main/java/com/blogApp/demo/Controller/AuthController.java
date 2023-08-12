package com.blogApp.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blogApp.demo.Dto.RegistrationDto;
import com.blogApp.demo.Entity.User;
import com.blogApp.demo.Service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;

	//handler method to handle user registration request
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		RegistrationDto user = new RegistrationDto();//this empty object hold form data.
		model.addAttribute("user", user);
		return "register";
	}
	
	//handler method to handle user register form submission request
	@PostMapping("/register/save")
	public String register(@Valid @ModelAttribute("user") RegistrationDto user,
							BindingResult result,Model model) {
		
		//11111111111111111111111111111111111111111111111111111111111111
		//under this we are writing a logic for email we are not allow multiple user with same email
		//we checking the database that the user is already present or not.
		User existingUser = userService.findByEmail(user.getEmail());
		if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null,"already a user with this email");
		}
		
		//11111111111111111111111111111111111111111111111111111111111111
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}
		
		userService.saveUser(user);
		return "redirect:/register?success";
		//when user success fully register then i will show a success message in this page that's why i create
		//return statement like this.
	}
	
	//handler method to handle login page request
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	
	
	
	
}
