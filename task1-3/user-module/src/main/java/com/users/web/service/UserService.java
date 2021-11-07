package com.users.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.validation.Validator;
import com.users.web.exception.ApiError;
import com.users.web.model.User;
import com.users.web.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	private final Validator userValidator = new Validator();

	public UserService() {
		char[] specialSymbols = { ' ', '_', '/', '\\', ',', '(', ')', '[', ']', '{', '}', '~', '`', '&', '^', '*', '-',
				'+', '.', ';', ':', '!' };
		userValidator.setPasswordValidatorSettings(8, specialSymbols);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(long id) {
		return repository.findById(id);
	}

	public List<User> findByName(String name) {
		return repository.findByFirstName(name);
	}

	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public User findByPhone(String phone) {
		return repository.findByPhone(phone);
	}

	public User add(User newUser) {
		validateUser(newUser);

		return repository.save(newUser);
	}

	public void update(User updatedUser) {
		if (updatedUser.getId() == null) {
			throw new ApiError(400, "ID must be provided.");
		}
		User exists = repository.findById((long) updatedUser.getId());
		if (exists == null) {
			throw new ApiError(404, "User with ID " + updatedUser.getId() + " does not exist.");
		}
		validateUser(updatedUser);
		repository.save(updatedUser);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public void delete(User deletedUser) {
		repository.delete(deletedUser);
	}

	private void validateUser(User user) {
		if (!userValidator.validateEmail(user.getEmail())) {
			throw new ApiError(400, "Email is invalid.");
		}
		if (!userValidator.validatePhoneNumber(user.getPhone())) {
			throw new ApiError(400, "Phone number is invalid.");
		}
		if (!userValidator.validatePassword(user.getPassword())) {
			throw new ApiError(400, "Password is not valid.");
		}
	}

	public void addPrefix(String prefix, int length) {
		userValidator.addPhoneNumberPrefixes(length, prefix);
	}

	public void setPasswordValidatorSettings(char[] specialSymbols, int length) {
		userValidator.setPasswordValidatorSettings(length, specialSymbols);
	}

}