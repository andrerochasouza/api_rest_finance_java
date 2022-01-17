package br.com.finance.cdd.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.finance.cdd.model.entities.User;
import br.com.finance.cdd.model.services.UserServices;

@Controller
@RequestMapping("/userpage")
public class UserController {

	@Autowired
	private UserServices service;

	@GetMapping({ "", "/" })
	@ResponseBody // Vai retornar no corpo da pagina como JSON
	public ModelAndView userPage() { // Serve para mandar um template com informações junto com as informações JSON (Configura o JSON com thymeleaf)
		ModelAndView mv = new ModelAndView("/////");
		return mv;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public User findByIdUser(@PathVariable long id) {
		return service.findById(id);
	}

	@GetMapping("/users")
	@ResponseBody
	public List<User> findAll() {
		return service.findAll();
	}

	
	@GetMapping("/salvarpessoa")
	public String userCadastro() {
		return "user/cadastroPessoa";
	}
	
	@PostMapping("/salvarpessoa")
	public String saveUser(@Valid @RequestBody User user) {
		service.save(user);
		return "user/cadastroPessoa";

	}
}
