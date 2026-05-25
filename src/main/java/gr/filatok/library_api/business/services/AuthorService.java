package gr.filatok.library_api.business.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import gr.filatok.library_api.business.dtos.AuthorDto;
import gr.filatok.library_api.business.dtos.BookDto;
import gr.filatok.library_api.business.dtos.exceptions.EntityNotFoundException;
import gr.filatok.library_api.domain.models.entities.Author;
import gr.filatok.library_api.domain.models.entities.Book;
import gr.filatok.library_api.domain.models.repositories.AuthorRepository;
import gr.filatok.library_api.domain.models.repositories.BookRepository;
import jakarta.validation.Valid;

@Service
@Validated
public class AuthorService {

	private final AuthorRepository authorRepo;
	private final BookRepository bookRepo;

	public AuthorService(AuthorRepository authorRepo, BookRepository bookRepo) {
		this.authorRepo = authorRepo;
		this.bookRepo = bookRepo;
	}

	public List<AuthorDto> getAuthors(){

		return authorRepo.findAll().stream().map(a -> new AuthorDto(a.getId(), a.getName(), a.getNationality(), a.getBirthdate()))
				.collect(Collectors.toList());
	}

	public AuthorDto getAuthorById(int id) {

		Author a = authorRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Author", id));

		AuthorDto dto = new AuthorDto(a.getId(), a.getName(), a.getNationality(), a.getBirthdate());

		return dto;
	}

	@Transactional
	public Integer createAuthor(@Valid AuthorDto a)
	{
		Author entity = new Author();
		entity.setName(a.getName());
		entity.setBirthdate(a.getBirthdate());
		entity.setNationality(a.getNationality());
		Author saved = authorRepo.save(entity);
		return saved.getId();
	}

	@Transactional
	public AuthorDto updateAuthor(@Valid AuthorDto a)
	{
		Author entity = authorRepo.findById(a.getId())
				.orElseThrow(() -> new EntityNotFoundException("Author", a.getId()));
		entity.setName(a.getName());
		entity.setBirthdate(a.getBirthdate());
		entity.setNationality(a.getNationality());
		authorRepo.save(entity);
		return a;
	}

	@Transactional
	public void deleteAuthor(int id) {
		
		Author author = authorRepo.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
		if (!author.getBooks().isEmpty()) {
	        throw new RuntimeException(
	                "Please delete books from author and try again"
	        );
	    }
		authorRepo.deleteById(id);
	}

	 public Set<BookDto> getBooksByAuthorId(int authorId) {
	        Author author = authorRepo.findById(authorId)
	            .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
	        Set<BookDto> booksDtos = author.getBooks().stream().map(book -> new BookDto(book.getId(), book.getTitle(),book.getISBN(), book.getCategory(), book.getPublicationYear()))
	        		.collect(Collectors.toSet());
	        return booksDtos;
	    }
	 
	 @Transactional
	 public void addBookToAuthor(int authorId, int bookId) {
		    Author author = authorRepo.findById(authorId)
		        .orElseThrow(() -> new RuntimeException("Author not found"));
		    Book book = bookRepo.findById(bookId)
		        .orElseThrow(() -> new RuntimeException("Book not found"));
		    author.getBooks().add(book);
		    authorRepo.save(author);
		}
	 
	 @Transactional
	 public void deleteBookFromAuthor(int authorId, int bookId) {
	     Author author = authorRepo.findById(authorId)
	         .orElseThrow(() -> new RuntimeException("Author not found"));
	     Book book = bookRepo.findById(bookId)
	         .orElseThrow(() -> new RuntimeException("Book not found"));
	     author.getBooks().remove(book);
	     authorRepo.save(author);
	 }
}
