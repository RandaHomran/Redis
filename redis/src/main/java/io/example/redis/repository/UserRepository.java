package io.example.redis.repository;

import java.util.Map;
import io.example.redis.model.User;

public interface UserRepository {
	User save(User user);
	Map<String, User> findAll();
	User findById(String id);
	User update(User user);
	void delete(String id);
}
