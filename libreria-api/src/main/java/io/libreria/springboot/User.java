package io.libreria.springboot;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@NotNull
	@Column(unique = true, nullable = false)
	private String username;
	private String password = "";
	
	private String firstName;
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@NotNull
    private java.util.Date  membershipValidUntil;
	
	@Max(3)
	private int numOfBorrowedBooks = 0;	//mozda maknit
	
	@ManyToMany	//owner side, doesnt matter though
	@JoinTable(
			name = "user_book", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> books;
	
	@ManyToOne
	private Role role;
	
	public boolean isValid() {	//implicitno doda valid polje, kewl
        if(this.getMembershipValidUntil().after(Calendar.getInstance().getTime()))
			return true;
		return false;
	}

	public void setPassword(String rawPassword) {
		this.password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(rawPassword);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", membershipValidUntil=" + membershipValidUntil + ", numOfBorrowedBooks=" + numOfBorrowedBooks
				+ ", role=" + role + "]";
	}	
}
