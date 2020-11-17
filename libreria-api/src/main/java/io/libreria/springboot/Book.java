package io.libreria.springboot;

//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	//@org.hibernate.validator.constraints.ISBN
	@Column(unique = true)
	@Size(min = 10, max = 13)
	private String isbn;
	
	@NotNull
	private String name;
	private String author;
	
	private int total = 0;
	private int available = 0;
	
	@ManyToOne
	private Genre genre;

	//@ManyToMany(mappedBy = "books")
	//private List<User> users;
	
	//	public Book() {}//za performanse i lazy loading za hibernate, FetchType.LAZY -default, || .EAGER
	
	public boolean isAvailable() {
		if(this.getAvailable()> 0)
			return true;
		return false;
	}
}
