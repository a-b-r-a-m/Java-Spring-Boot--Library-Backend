package io.libreria.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {	//Crud vraca Iterable<>, Jpa List<>
	public List<Book> findByGenreId(int genreId);
	public List<Book> findByName(String name);
	public List<Book> findByAuthor(String author);
	public Book findByIsbn(String isbn);
	
	public List<Book> findByNameIgnoreCaseContaining(String findName);
	public List<Book> findByAuthorIgnoreCaseContaining(String findAuthor); 
}
