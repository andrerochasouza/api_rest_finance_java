package br.com.finance.cdd.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.finance.cdd.controllers.dto.UserDTO;
import br.com.finance.cdd.model.entities.User;
import br.com.finance.cdd.model.services.UserServices;

@Controller
public class UserController {

	@Autowired
	private UserServices serviceUser;

	@GetMapping("/")
	public String userPage(Model model) {
		List<UserDTO> users = new UserDTO().listUsersDTOconverter(serviceUser.findAll());
		UserDTO user1 = new UserDTO(serviceUser.findById(1L));
		List<UserDTO> users2 = Arrays.asList(user1);
		
		model.addAllAttributes(users2);		
		return "index";
	}

	@GetMapping("/{id}")
	@ResponseBody
	public UserDTO findByIdUser(@PathVariable long id) {
		return new UserDTO().userDTOConverter(serviceUser.findById(id));
	}
	
	@GetMapping("/user/{id}")
	public String userPageId(@PathVariable long id, Model model) {
		UserDTO user = new UserDTO().userDTOConverter(serviceUser.findById(id));
		model.addAttribute("id" , user.getId());
		model.addAttribute("name", user.getName());
		model.addAttribute("wallet", user.getWallet());
		return "index";
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
