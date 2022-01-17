package br.com.finance.cdd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // Vai retornar um template (podendo usar response body)
public class homePageController {
	
	@RequestMapping(path = ("/"), method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
