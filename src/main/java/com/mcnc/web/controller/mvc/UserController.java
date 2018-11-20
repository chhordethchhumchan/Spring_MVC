package com.mcnc.web.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class UserController {
	
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public ModelAndView  welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "User Demo!");
		model.setViewName("pages/users");
		return model;

	}

	

}
