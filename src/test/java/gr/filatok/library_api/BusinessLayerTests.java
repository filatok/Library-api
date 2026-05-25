package gr.filatok.library_api;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import gr.filatok.library_api.business.dtos.AuthorDto;
import gr.filatok.library_api.business.dtos.BookDto;
import gr.filatok.library_api.business.dtos.exceptions.EntityNotFoundException;
import gr.filatok.library_api.business.services.AuthorService;
import gr.filatok.library_api.business.services.BookService;
import jakarta.validation.ConstraintViolationException;

@SpringBootTest
@Transactional
public class BusinessLayerTests {

	@Autowired
	private AuthorService as;

	@Autowired
	private BookService bs;

	// --Author service--

	@Test
	void testCreateAndGetAuthor() {
		AuthorDto author = new AuthorDto();
		author.setName("TestAuthor");
		author.setNationality("greek");
		author.setBirthdate(LocalDate.parse("1990-02-06"));
		int id = as.createAuthor(author);
		AuthorDto fetched = as.getAuthorById(id);
		Assertions.assertThat(fetched.getName()).isEqualTo("TestAuthor");
	}

	@Test
	void testGetAllAuthors() {
		List<AuthorDto> authors = as.getAuthors();
		Assertions.assertThat(authors).isNotEmpty();
	}

	@Test
	void testUpdateAuthor() {

		AuthorDto author = new AuthorDto();
		author.setName("Original");
		author.setNationality("greek");
		author.setBirthdate(LocalDate.parse("1990-02-06"));
		int id = as.createAuthor(author);

		AuthorDto update = new AuthorDto();
		update.setId(id);
		update.setName("Updated");
		as.updateAuthor(update);

		AuthorDto fetched = as.getAuthorById(id);
		Assertions.assertThat(fetched.getName()).isEqualTo("Updated");
	}

	@Test
	void testDeleteAuthor() {
		AuthorDto author = new AuthorDto();
		author.setName("ToDelete");
		author.setNationality("african");
		author.setBirthdate(LocalDate.parse("1977-02-06"));
		int id = as.createAuthor(author);
		as.deleteAuthor(id);
		Assertions.assertThatThrownBy(() -> as.getAuthorById(id))
		.isInstanceOf(EntityNotFoundException.class);
	}

	@Test
	void testGetAuthorByIdNotFound() {
		Assertions.assertThatThrownBy(() -> as.getAuthorById(999999))
		.isInstanceOf(EntityNotFoundException.class)
		.hasMessageContaining("Author was not found");
	}

	@Test
	void testCreateAuthorWithNullName() {
		AuthorDto author = new AuthorDto();
		author.setName(null);
		Assertions.assertThatThrownBy(() -> as.createAuthor(author))
		.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void testCreateAuthorWithEmptyName() {
		AuthorDto author = new AuthorDto();
		author.setName("");
		Assertions.assertThatThrownBy(() -> as.createAuthor(author))
		.isInstanceOf(ConstraintViolationException.class);
	}

	// --Book service--

	@Test
	void testCreateAndGetBook() {
		BookDto book = new BookDto();
		book.setTitle("TestBook");
		book.setCategory("novel");
		book.setISBN("9780552171893");
		book.setPublicationYear(1899);
		int id = bs.createBook(book);
		BookDto fetched = bs.getBookById(id);
		Assertions.assertThat(fetched.getTitle()).isEqualTo("TestBook");
	}

	@Test
	void getAllBooks() {
		List<BookDto> books = bs.getBooks();
		Assertions.assertThat(books).isNotEmpty();
	}

	@Test
	void testUpdateBook() {
		BookDto book = new BookDto();
		book.setTitle("original");
		book.setCategory("novel");
		book.setISBN("9780552171893");
		book.setPublicationYear(1899);
		int id = bs.createBook(book);

		BookDto update = new BookDto();
		update.setTitle("Updated");
		update.setId(id);
		bs.updateBook(update);

		BookDto fetched= bs.getBookById(id);
		Assertions.assertThat(fetched.getTitle()).isEqualTo("Updated");
	}

	@Test
	void testDeleteBook() {
		BookDto book = new BookDto();
		book.setTitle("ToDelete");
		book.setCategory("novel");
		book.setISBN("9780552171893");
		book.setPublicationYear(1899);
		int id = bs.createBook(book);
		bs.deleteBook(id);
		Assertions.assertThatThrownBy(() -> bs.getBookById(id)).isInstanceOf(EntityNotFoundException.class);
	}

	@Test
	void testGetBookByIdNotFound() {
		Assertions.assertThatThrownBy(() -> bs.getBookById(999999))
		.isInstanceOf(EntityNotFoundException.class).hasMessageContaining("Book was not found");
	}

	@Test
	void testCreateBookWithNullName() {
		BookDto book = new BookDto();
		book.setTitle(null);
		book.setCategory("novel");
		book.setISBN("9780552171893");
		book.setPublicationYear(1899);
		Assertions.assertThatThrownBy(() -> bs.createBook(book))
		.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void testCreateBookWithEmptyName() {
		BookDto book = new BookDto();
		book.setTitle("");
		book.setCategory("novel");
		book.setISBN("9780552171893");
		book.setPublicationYear(1899);
		Assertions.assertThatThrownBy(() -> bs.createBook(book))
		.isInstanceOf(ConstraintViolationException.class);
	}
}
