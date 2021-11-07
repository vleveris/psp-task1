package com.users.web.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.users.web.exception.ApiError;
import com.users.web.model.User;
import com.users.web.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	@Mock
	UserRepository repository;
	@InjectMocks
	UserService service;

	@Test
	void testFindAll() {
		User user = new User();
		List<User> users = new ArrayList<>();
		users.add(user);
		when(repository.findAll()).thenReturn(users);
		List<User> found = service.findAll();
		verify(repository).findAll();
		assertEquals(1, found.size());
	}

	@Test
	void testFindById() {
		User user = new User();
		when(repository.findById(Mockito.anyLong())).thenReturn(user);
		User found = service.findById(1);
		verify(repository).findById(Mockito.anyLong());
		assertNotNull(found);
	}

	@Test
	void testAddCorrect() {
		User user = new User("first", "last", "first.last@gmail.com", "+37066666666", "address", "Pas1ssWOR_das");
		when(repository.save(Mockito.any(User.class))).thenReturn(user);
		User added = service.add(user);
		verify(repository).save(Mockito.any(User.class));
		assertNotNull(added);
	}

	@Test
	void testAddInvalidPasswordUppercaseMissing() {
		User user = new User("first", "last", "first.last@gmail.com", "+37066666666", "address", "pas1sswor_das");
		assertThrows(ApiError.class, () -> service.add(user));
	}

	@Test
	void testAddInvalidPasswordSpecialSymbolMissing() {
		User user = new User("first", "last", "first.last@gmail.com", "+37066666666", "address", "pas1ssWORRdas");
		assertThrows(ApiError.class, () -> service.add(user));
	}

	@Test
	void testAddInvalidPasswordTooShort() {
		User user = new User("first", "last", "first.last@gmail.com", "+37066666666", "address", "pas1ssw");
		assertThrows(ApiError.class, () -> service.add(user));
	}

	@Test
	void testAddInvalidEmailAtMissing() {
		User user = new User("first", "last", "first.lastgmail.com", "+37066666666", "address", "pas1ssWOR_das");
		assertThrows(ApiError.class, () -> service.add(user));
	}

	@Test
	void testAddInvalidPhoneIncorrectPrefix() {
		User user = new User("first", "last", "first.last@gmail.com", "+27066666666", "address", "pas1ssWOR_das");
		assertThrows(ApiError.class, () -> service.add(user));
	}

	@Test
	void testUpdate() {
		User user = new User();
		assertThrows(ApiError.class, () -> service.update(user));
	}

	@Test
	void testUpdateNewPhonePrefix() {
		User user = new User("first", "last", "first.last@gmail.com", "+123666", "address", "pas1ssWOR_das");
		user.setId(1L);
		when(repository.save(Mockito.any(User.class))).thenReturn(user);
		when(repository.findById(Mockito.anyLong())).thenReturn(user);
		service.addPrefix("+123", 7);
		assertDoesNotThrow(() -> service.update(user));
	}

	@Test
	void testUpdateDifferentPasswordSettings() {
		User user = new User("first", "last", "first.last@gmail.com", "+37066666666", "address", "pasWz");
		user.setId(1L);
		when(repository.save(Mockito.any(User.class))).thenReturn(user);
		when(repository.findById(Mockito.anyLong())).thenReturn(user);
		char[] specialSymbols = { 'z' };
		service.setPasswordValidatorSettings(specialSymbols, 5);
		assertDoesNotThrow(() -> service.update(user));
	}

	@Test
	void testDeleteById() {
		service.deleteById(1L);
		verify(repository).deleteById(Mockito.anyLong());

	}

}