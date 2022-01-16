package br.com.finance.cdd.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class homePageController {
	
	@RequestMapping(path = ("/"), method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("///////");
		return mv;
	}

}
