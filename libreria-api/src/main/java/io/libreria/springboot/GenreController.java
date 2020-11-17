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

@RestController	//auto. konvertira returnvalue u json i salje ko http response
@RequestMapping("/genres")
public class GenreController {

	@Autowired
	private GenreService genreService;
	
	@GetMapping
	public List<Genre> getAllGenres() {
		return genreService.getAllGenres();
	}
	
	@GetMapping("/{id}")
	public Genre getGenre(@PathVariable int id, HttpServletResponse response) {
		Genre g = genreService.getGenre(id);
		if(g == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return g;
	}
	
	@PostMapping
	public void addGenre(@RequestBody Genre genre) {	//@ReqBody-zaautokonverzijuJSONauObjekt
		genreService.addGenre(genre);
	}
	
	@PutMapping("/{id}")	//da mos poslat i samo dio parametara(dausporedijeldifoltnavrijednostuzahtjevu.akojezadrzistaru)
	public Genre updateGenre(@RequestBody Genre genre, @PathVariable int id) {
//		Genre g = genreService.getGenre(id);
//		g.setName(genre.getName());
//		genreService.updateGenre(g);
		genre.setId(id);
		genreService.updateGenre(genre);
		
		return genre;
	}
	
	@DeleteMapping("/{id}")
	public String deleteGenre(@PathVariable int id) {
		genreService.deleteGenre(id);
		
		return ("{\"message\":\"Genre successfully deleted.\"}");
	}
	
	
	@GetMapping("/name/{name}")
	public Genre getGenreByName(@PathVariable String name) {
		return genreService.getGenreByName(name);
	}
}
