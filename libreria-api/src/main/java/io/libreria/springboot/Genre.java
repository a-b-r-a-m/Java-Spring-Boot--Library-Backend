package io.libreria.springboot;

//import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
//import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
//@RequiredArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "genre")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	//@Basic(optional = false)
	@Column(name = "name", unique = true, nullable = false)
	@NotBlank(message = "Genre name cannot be empty. It wouldn't make sense, right?")
	private String name;
}
