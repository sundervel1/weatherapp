package com.abc.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.dto.UserDetails;
import com.abc.service.WeatherService;

@RestController
@RequestMapping("/weather")
@Validated
public class WeatherController {
	
	@Autowired
	private WeatherService wService;
	
	@PostMapping("/login")
	public String login(@RequestBody UserDetails userDetails, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String message="";
		System.out.println("login:"+userDetails);
		String uname = (String) session.getAttribute("user");
		System.out.println("session uname: "+uname);
		UserDetails user = wService.login(userDetails);
		System.out.println("got user: " + user);
		if(user!=null) {
		   session.setAttribute("user", userDetails.getUsername());
		   message = "Hi "+ userDetails.getUsername() +", You have successfully logged in to the system";
		}
		else {
			message = "Authentication failed for user "+userDetails.getUsername();
		}
		return 	message;	
	}
	
	@PostMapping("/logout")
	public String logout(@RequestBody UserDetails userDetails, HttpServletRequest request) {
		System.out.println("logging out: "+userDetails);
		HttpSession session = request.getSession();
		Enumeration<String> attributes = session.getAttributeNames();
		String message = "Hi "+ userDetails.getUsername() +", You have not logged in";
		while (attributes.hasMoreElements()) {
			System.out.println("session has elements");
			String attribute = attributes.nextElement();
			String uName = (String) session.getAttribute(attribute);
			System.out.println(attribute +" - "+uName);
			if(uName.equals(userDetails.getUsername())) {
				System.out.println("invalidating session..."+uName);
				message = "Hi "+ userDetails.getUsername() +", You have successfully logged out of the system";
				session.invalidate();
			}
		}
		return message;		
	}
}
