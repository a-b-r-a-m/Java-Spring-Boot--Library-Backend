package io.libreria.springboot;

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
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	
	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public Book getBook(@PathVariable int id, HttpServletResponse response) {
		Book b = bookService.getBook(id);
		if(b == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return b;
	}
	
	@PostMapping
	public Book addBook(@RequestBody Book book) {
		bookService.addBook(book);
		
		return book;
	}
	
	@PutMapping("/{id}")
	public Book updateBook(@RequestBody Book book, @PathVariable int id) {
		book.setId(id);
		bookService.updateBook(book);
		
		return book;
	}
	
	//@Secured({"ROLE_USER", "ROLE_DEMO"}) //valjda umisto u konfiguraciji mos ovako
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
		
		return ("{\"message\":\"Book successfully deleted.\"}");
	}
	
	

	@GetMapping("/genre/{genreId}")
	public List<Book> getBooksByGenre(@PathVariable int genreId) {
		return bookService.getBooksByGenre(genreId);
	}
	
	@GetMapping("/name/{name}")	// ' ' = '%20'
	public List<Book> getBooksByName(@PathVariable String name) {
		return bookService.getBooksByName(name);
	}
	
	@GetMapping("/author/{author}")
	public List<Book> getBooksByAuthor(@PathVariable String author) {
		return bookService.getBooksByAuthor(author);
	}
	
	@GetMapping("/isbn/{isbn}")
	public Book getBookByIsbn(@PathVariable String isbn) {
		return bookService.getBookByIsbn(isbn);
	}
	
	
	@GetMapping("/findname/{findName}")
	public List<Book> getBooksByPartialName(@PathVariable String findName) {
		return bookService.getBooksByFindName(findName);
	}
	
	@GetMapping("/findauthor/{findAuthor}")
	public List<Book> getBooksByFindAuthor(@PathVariable String findAuthor) {
		return bookService.getBooksByFindAuthor(findAuthor);
	}
}
