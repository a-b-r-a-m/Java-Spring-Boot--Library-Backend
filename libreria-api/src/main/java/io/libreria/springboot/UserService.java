package io.libreria.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	
	public List<User> getAllUsers() {
		return userRepository.findAll(new Sort(Sort.Direction.ASC, "lastName"));
	}
	
	public User getUser(int id) {
		return userRepository.findById(id).orElse(null);
	}

	public void addUser(User user) {
		userRepository.save(user);
	}

	public void updateUser(User user) {
		userRepository.save(user);
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
	
	
	public List<User> getUsersByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName, new Sort(Sort.Direction.ASC, "lastName"));
	}
	
	public List<User> getUsersByLastName(String lastName) {
		return userRepository.findByLastName(lastName, new Sort(Sort.Direction.ASC, "firstName"));	//By//OrderByFirstNameAsc
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
