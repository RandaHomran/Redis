package io.example.redis.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.example.redis.model.User;
import io.example.redis.service.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public User add(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@GetMapping("/find/{id}")
	@Cacheable(value= "users",key="#id")
	public User getUserById(@PathVariable String id){
		return userService.getUserById(id);
	}
	
	@PutMapping("/update")
	@ResponseBody
	@CachePut(value = "users", key = "#user.id")
	public User updateItem(@RequestBody User user){
		return userService.updateUser(user);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	@CacheEvict(value= "users",key = "#id")
	public void deleteItem(@PathVariable ("id") String id){
		userService.deleteUser(id);
	}

	@RequestMapping("/findall")
	@ResponseBody
	public Map<String,User> findAll(){
		return userService.getAllUsers();
	}

}
