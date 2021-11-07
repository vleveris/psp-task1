package com.users.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.users.web.model.User;
import com.users.web.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;

	@GetMapping("/users")
	public String showAll(ModelMap model) {
		List<User> users = service.findAll();
		model.put("users", users);
		return "user-list";
	}

	@GetMapping("/user/delete")
	public String deleteById(@RequestParam Long id) {
		service.deleteById(id);
		return "redirect:/users";
	}

	@GetMapping("/user/new")
	public String addForm(ModelMap model) {
		model.put("user", new User());
		return "add-user";
	}

	@PostMapping("/user/new")
	public String add(@ModelAttribute("user") User user, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			return "add-user";
		}
		service.add(user);
		return "redirect:/users";
	}

	@GetMapping("/user/update")
	public String updateForm(ModelMap model, @RequestParam Long id) {
		User user = service.findById(id);
		model.put("user", user);
		return "update-user";
	}

	@PostMapping("/user/update")
	public String update(@ModelAttribute("user") User user, Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "update-user";
		}
		service.update(user);
		return "redirect:/users";
	}
}
