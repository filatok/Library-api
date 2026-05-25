package gr.filatok.library_api.domain.models.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import gr.filatok.library_api.domain.models.enums.DatabaseTables;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity(name = DatabaseTables.Authors)
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String nationality;
	private LocalDate birthdate;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "author_book", joinColumns = {@JoinColumn(name = "author_id")}, inverseJoinColumns = {@JoinColumn(name = "book_id")})
	private Set<Book> books = new HashSet<>();

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Set<Book> getBooks() {
	    return books;
	}
}
