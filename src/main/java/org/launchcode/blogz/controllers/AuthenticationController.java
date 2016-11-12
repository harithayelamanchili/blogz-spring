package org.launchcode.blogz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		// TODO - implement signup
				// get parameters from request object
				// validate parameters (username, password, verify)
				// if validated, create new user, and put them in the session
				// access session...   Session thisSession = request.getSession(); (in AbstractController)
				
		String newUsername = request.getParameter("username");
		model.addAttribute("username", newUsername);
		String newPassword = request.getParameter("password");
		String newVerifyPassword = request.getParameter("verify");
		String user_error = request.getParameter("username_error");
		user_error = "Not a valid username";
		String pass_error = request.getParameter("password_error");
		pass_error = "Not a valid username";
		String v_error = request.getParameter("verify_error");
		v_error = "Not a valid username";
		
		
		if ((User.isValidUsername(newUsername)) && (User.isValidPassword(newPassword))){
			if (newVerifyPassword.equals(newPassword)){
				User newUser = new User(newUsername, newPassword);
				userDao.save(newUser);
				HttpSession thisSession = request.getSession();
				setUserInSession(thisSession, newUser);
				
				return "redirect:blog/newpost";
			}
			
		}			
		return "signup";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		// TODO - implement login
		// get parameters from request
		
		// get user by their username User.username
		
		//check password is correct, if not correct password redirect to the form and give an error message
		
		// log them in, if so (i.e. setting the user in the session
		
		String newUsername = request.getParameter("username");
		String newPassword = request.getParameter("password");
		//if (User.isMatchingPassword(newPassword))
		
		request.getSession();
		return "redirect:blog/newpost";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}