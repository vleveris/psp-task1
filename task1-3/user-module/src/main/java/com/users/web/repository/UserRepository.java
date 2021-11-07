package com.users.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.users.web.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	@Override
	List<User> findAll();

	User findById(long id);

	List<User> findByFirstName(String name);

	User findByEmail(String email);

	User findByPhone(String phone);

}
