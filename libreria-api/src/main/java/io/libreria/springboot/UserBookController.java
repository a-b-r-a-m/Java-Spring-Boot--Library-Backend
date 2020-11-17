package io.libreria.springboot;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserBookController {

	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	
	@PostMapping("/users/{userId}/books/{bookId}")
	public String rentBook(@PathVariable int userId, @PathVariable int bookId, HttpServletResponse response) {
		User user = userService.getUser(userId);
		Book book = bookService.getBook(bookId);
		
		if((user == null) || (book == null)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return ("{\"message\":\"User/Book doesn't exist.\"}");
		}
		if(user.isValid() && (user.getNumOfBorrowedBooks() < 3) && book.isAvailable()) {
			user.getBooks().add(book);
			user.setNumOfBorrowedBooks((user.getNumOfBorrowedBooks()) + 1);
			
			//book.getUsers().add(user);			
			book.setAvailable((book.getAvailable()) - 1);
			
			userService.updateUser(user);
			bookService.updateBook(book);

			return ("{\"message\":\"You have successfully rented " + book.getName() + "\"}");
		}
		
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return "{\"message\":\"Sorry, You cannot rent that book atm for one or more of the following reasons:\n\n1)Membership expired\n2)Already borrowed 3 books (max)\n3)All copies are rented\"}";
	}
	
	@GetMapping("/users/{userId}/books")
	public List<Book> getRentedBooks(@PathVariable int userId, Principal principal, HttpServletResponse response) {
		User viewedUser = userService.getUser(userId);
		User loggedUser = userService.getUserByUsername(principal.getName());
		
		System.out.println(viewedUser.getRole().getName());
		System.out.println(principal.getName());
		System.out.println(loggedUser.getRole().getName());
		
		if((loggedUser.getRole().getName().equals("ROLE_ADMIN")) || (loggedUser.getUsername().equals(viewedUser.getUsername())))
			return viewedUser.getBooks();

		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return null;
	}
	
	@DeleteMapping("/users/{userId}/books/{bookId}")
	public String returnBook(@PathVariable int userId, @PathVariable int bookId) {
		User user = userService.getUser(userId);
		Book book = bookService.getBook(bookId);
		
		user.getBooks().remove(book);
		user.setNumOfBorrowedBooks((user.getNumOfBorrowedBooks()) - 1);
		
		book.setAvailable((book.getAvailable()) + 1);
		
		userService.updateUser(user);
		bookService.updateBook(book);
		
		return ("{\"message\":\"You have successfully returned " + book.getName() + "\"}");
	}
}
