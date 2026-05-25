package gr.filatok.library_api.business.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AuthorDto {

	private Integer id;
	@NotNull
	@NotEmpty
	private String name;
	private String nationality;
	private LocalDate birthdate;
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

	public AuthorDto() {
		}

	public AuthorDto(Integer id, @NotNull @NotEmpty String name, String nationality, LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.birthdate = birthdate;
	}
}
