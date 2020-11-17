package io.libreria.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	public Book getBook(int id) {
		return bookRepository.findById(id).orElse(null);
	}

	public void addBook(Book book) {
		bookRepository.save(book);
	}

	public void updateBook(Book book) {
		bookRepository.save(book);
	}

	public void deleteBook(int id) {
		bookRepository.deleteById(id);
	}
	
	
	
	public List<Book> getBooksByGenre(int genreId) {
		return bookRepository.findByGenreId(genreId);
	}
	
	public List<Book> getBooksByName(String name) {
		return bookRepository.findByName(name);
	}
	
	public List<Book> getBooksByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}
	
	public Book getBookByIsbn(String isbn) {
		return bookRepository.findByIsbn(isbn);
	}
	
	
	public List<Book> getBooksByFindName(String findName) {
		return bookRepository.findByNameIgnoreCaseContaining(findName);
	}
	
	public List<Book> getBooksByFindAuthor(String findAuthor) {
		return bookRepository.findByAuthorIgnoreCaseContaining(findAuthor);
	}
}
