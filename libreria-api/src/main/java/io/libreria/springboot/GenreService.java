package io.libreria.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service	//stvori samo jednu instancu sta drugi mogu koristit, iskoristila se u kontroleru
public class GenreService {
	
	@Autowired
	private GenreRepository genreRepository;
	
	public List<Genre> getAllGenres() {
		return genreRepository.findAll();
	}
	
	public Genre getGenre(int id) {
		return genreRepository.findById(id).orElse(null);	//getOne ne
	}

	public void addGenre(Genre genre) {
		genreRepository.save(genre);
	}

	public void updateGenre(Genre genre) {
		genreRepository.save(genre);
	}

	public void deleteGenre(int id) {
		genreRepository.deleteById(id);
	}
	
	
	public Genre getGenreByName(@PathVariable String name) {
		return genreRepository.findByName(name);
	}
}
