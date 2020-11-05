package io.example.redis.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import io.example.redis.model.User;

@Repository
public class UserRepositoryImp implements UserRepository {

	private RedisTemplate<String,User> redisTemplate;
	private HashOperations hashOperations;
	
	public UserRepositoryImp(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations= redisTemplate.opsForHash();
	}

	@Override
	public User save(User user) {
		hashOperations.put("USER", user.getId(), user);
		return user;
	}

	@Override
	public Map<String,User> findAll() {
		return hashOperations.entries("USER");
	}

	@Override
	public User findById(String id) {
		return (User) hashOperations.get("USER", id);
	}

	@Override
	public User update(User user) {
		save(user);
		return user;
	}

	@Override
	public void delete(String id) {
		hashOperations.delete("USER", id);
	}

}
