package br.com.finance.cdd.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.finance.cdd.model.entities.User;
import br.com.finance.cdd.model.services.UserServices;

@RestController
@RequestMapping("/userpage")
public class UserController {

	@Autowired
	private UserServices service;

	@GetMapping({"", "/"})
	public ModelAndView userPage() {
		ModelAndView mv = new ModelAndView("/////");
		return mv;
	}

	@GetMapping("/{id}")
	public @ResponseBody User findByIdUser(@PathVariable long id) {
		return service.findById(id);
	}

	@PostMapping("/salvar")
	public @ResponseBody User saveUser(@Valid User user) {
		return service.saveAll(user);
	}

}
