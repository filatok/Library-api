package gr.filatok.library_api.domain.models.entities;

import java.util.HashSet;
import java.util.Set;

import gr.filatok.library_api.domain.models.enums.DatabaseTables;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity(name = DatabaseTables.Books)
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String ISBN;
	private String category;
	private Integer publicationYear;

	@ManyToMany(mappedBy = "books")
	private Set<Author> authors = new HashSet<>();

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}
	public Set<Author> getAuthors() {
	    return authors;
	}
}
