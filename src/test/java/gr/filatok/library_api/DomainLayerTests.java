package gr.filatok.library_api;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import gr.filatok.library_api.domain.models.entities.Author;
import gr.filatok.library_api.domain.models.entities.Book;
import gr.filatok.library_api.domain.models.repositories.AuthorRepository;
import gr.filatok.library_api.domain.models.repositories.BookRepository;

@SpringBootTest
public class DomainLayerTests {
	@Autowired
	private AuthorRepository authorRepo;

	@Autowired
	private BookRepository bookRepo;

	@Test
	void testFindAllAuthors(){
		List<Author> authors = authorRepo.findAll();
		Assertions.assertThat(authors).isNotNull();
		Assertions.assertThat(authors.size()).isGreaterThan(0);
		System.out.println(authors.size());
	}

	//@Test
	void testFindAuthorById() {

		Optional<Author> author = authorRepo.findById(1);
		Assertions.assertThat(author.isPresent());
		Assertions.assertThat(author.get().getName()).isNotEmpty();
	}

	//@Test
	void testFindAuthorByName() {

		List<Author> authors = authorRepo.findByName("Lev Tolstoy");
		Assertions.assertThat(authors).isNotNull();
	}

	@Test
	void testFindAllBooks(){
		List<Book> books = bookRepo.findAll();
		Assertions.assertThat(books).isNotNull();
		Assertions.assertThat(books.size()).isGreaterThan(0);
		System.out.println(books.size());
	}

	@Test
	void testFindBookByTitle() {

		List<Book> books = bookRepo.findByTitle("Good Omens");
		Assertions.assertThat(books).isNotNull();
	}

	@Test
	void testFindBookById() {
		Optional<Book> book = bookRepo.findById(1);
		Assertions.assertThat(book.isPresent());
		Assertions.assertThat(book.get().getTitle()).isNotEmpty();
	}
}

