package io.example.redis.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.example.redis.model.User;
import io.example.redis.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(String id) {
		System.out.println("get user from database");
		return userRepository.findById(id);
	}
	
	public Map<String,User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public void deleteUser(String id) {
		userRepository.delete(id);
	}
	
	public User updateUser(User user) {
		return userRepository.update(user);
	}
	
}
