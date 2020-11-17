package io.libreria.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {	//2. arg je tip id-a
	public Genre findByName(String name);
}
