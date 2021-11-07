package com.users.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.users.web.model.User;
import com.users.web.service.UserService;

@RestController
public class UserRestController {
	@Autowired
	private UserService service;

	@GetMapping("/users/json")
	public List<User> viewAllJson() {
		return service.findAll();
	}

	@GetMapping("/user/{id}/json")
	public User viewByIdJson(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping("/users/name/{name}/json")
	public List<User> viewByNameJson(@PathVariable String name) {
		return service.findByName(name);
	}

	@GetMapping("/user/email/{email}/json")
	public User viewByEmailJson(@PathVariable String email) {
		return service.findByEmail(email);
	}

	@GetMapping("/user/phone/{phone}/json")
	public User viewByPhoneJson(@PathVariable String phone) {
		return service.findByPhone(phone);
	}

	@PostMapping("/user")
	public ResponseEntity<Void> addJson(@RequestBody User user) {
		User addedUser = service.add(user);
		if (addedUser == null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/json")
				.buildAndExpand(addedUser.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PostMapping("/user/update/json")
	public ResponseEntity<Void> updateJson(@RequestBody User user) {
		service.update(user);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/user/delete/{id}/json")
	public void deleteById(@PathVariable Long id) {
		service.deleteById(id);
	}
}
