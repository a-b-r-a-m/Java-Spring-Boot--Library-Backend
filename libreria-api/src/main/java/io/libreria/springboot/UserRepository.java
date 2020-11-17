package io.libreria.springboot;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

	public interface UserRepository extends JpaRepository<User, Integer> {
		public User findByUsername(String username);
		public List<User> findByFirstName(String firstName, Sort lname);
		public List<User> findByLastName(String lastName, Sort fname);	//By//OrderByFirstNameAsc	//samo za tvoje metode?
	}
