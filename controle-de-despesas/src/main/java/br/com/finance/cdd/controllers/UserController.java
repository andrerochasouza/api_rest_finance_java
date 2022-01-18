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

import br.com.finance.cdd.controllers.dto.UserDTO;
import br.com.finance.cdd.model.entities.User;
import br.com.finance.cdd.model.services.UserServices;

@Controller
@RequestMapping("/userpage")
public class UserController {

	@Autowired
	private UserServices serviceUser;

	@GetMapping({ "", "/" })
	public String userPage() {
		return "/////";
	}

	@GetMapping("/{id}")
	@ResponseBody
	public UserDTO findByIdUser(@PathVariable long id) {
		return new UserDTO(serviceUser.findById(id));
	}

	@GetMapping("/users")
	@ResponseBody
	public List<User> findAll() {
		return serviceUser.findAll();
	}

	
	@GetMapping("/salvarpessoa")
	public String userCadastro() {
		return "user/cadastroPessoa";
	}
	
	@PostMapping("/salvarpessoa")
	public String saveUser(@Valid @RequestBody User user) {
		serviceUser.save(user);
		return "user/cadastroPessoa";

	}
}
