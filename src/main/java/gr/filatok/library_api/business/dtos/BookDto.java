package gr.filatok.library_api.business.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookDto {

	private Integer id;
	@NotNull
	@NotEmpty
	private String title;
	private String ISBN;
	private String category;

	@Column(name = "publicationYear")
	private Integer publicationYear;
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
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public BookDto(Integer id, @NotNull @NotEmpty String title, String isbn, String category, Integer publicationYear) {
		super();
		this.id = id;
		this.title = title;
		this.ISBN = isbn;
		this.category = category;
		this.publicationYear = publicationYear;
	}

	public BookDto() {

	}
}
