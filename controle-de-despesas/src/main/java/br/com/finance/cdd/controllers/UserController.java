package br.com.finance.cdd.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.entities.User;
import br.com.finance.cdd.model.repository.UserRepository;

@RestController

public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
//	@RequestMapping(value = "/index",method = RequestMethod.GET)
//	public ModelAndView index(ModelAndView modelAndView) {
//		modelAndView = new ModelAndView("index");
//		System.out.println("entrou no /index");
//		return modelAndView;
//	}


	@GetMapping("/user/{id}")
	public @ResponseBody User findByIdUser(@PathVariable long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By ID: " + id));
	}


	@PostMapping("/salvar")
	public @ResponseBody User saveUser(@Valid User user) {
		userRepository.save(user);
		return user;
	}

}
