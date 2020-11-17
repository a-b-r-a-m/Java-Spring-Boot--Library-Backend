package io.libreria.springboot;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping
	public List<User> getAllUsers() {		//nekako maknit lozinku, foreach?
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable int id, HttpServletResponse response, Principal principal) {	//nekako maknit lozinku
		
		
		User viewedUser = userService.getUser(id);
		User loggedUser = userService.getUserByUsername(principal.getName());
		
		System.out.println(viewedUser.getRole().getName());
		System.out.println(principal.getName());
		System.out.println(loggedUser.getRole().getName());
		
		if((loggedUser.getRole().getName().equals("ROLE_ADMIN")) || (loggedUser.getUsername().equals(viewedUser.getUsername())))
			return viewedUser;

		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return null;
	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		userService.addUser(user);
		
		return user;
	}
	
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable int id) {
		user.setId(id);
		userService.updateUser(user);
		
		return user;
	}
	
	//@Secured({"ROLE_USER", "ROLE_DEMO"})
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		
		return ("{\"message\":\"User successfully deleted.\"}");
	}
	
	
	@GetMapping("/firstname/{firstName}")
	public List<User> getUsersByFirstName(@PathVariable String firstName) {
		return userService.getUsersByFirstName(firstName);
	}

	@GetMapping("/lastname/{lastName}")
	public List<User> getUsersByLastName(@PathVariable String lastName) {
		return userService.getUsersByLastName(lastName);
	}
	
	@GetMapping("/username/{username}")
	public User getUserByUserName(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
}
