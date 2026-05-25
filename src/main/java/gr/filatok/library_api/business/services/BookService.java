package gr.filatok.library_api.business.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import gr.filatok.library_api.business.dtos.AuthorDto;
import gr.filatok.library_api.business.dtos.BookDto;
import gr.filatok.library_api.business.dtos.exceptions.EntityNotFoundException;
import gr.filatok.library_api.domain.models.entities.Author;
import gr.filatok.library_api.domain.models.entities.Book;
import gr.filatok.library_api.domain.models.repositories.BookRepository;
import gr.filatok.library_api.domain.models.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Validated
public class BookService {

	private final BookRepository bookRepo;
	private final AuthorRepository authorRepo;

	public BookService(BookRepository bookRepo, AuthorRepository authorRepo) {
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
	}

	public List<BookDto> getBooks(){
		return bookRepo.findAll().stream().map(b -> {BookDto dto = new BookDto();
		dto.setId(b.getId());
		dto.setTitle(b.getTitle());
		dto.setISBN(b.getISBN());
		dto.setCategory(b.getCategory());
		dto.setPublicationYear(b.getPublicationYear());
		return dto;
		}).collect(Collectors.toList());
	}

	public BookDto getBookById(int id) {

		Book b = bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Book", id));

		BookDto dto = new BookDto();
		dto.setId(b.getId());
		dto.setTitle(b.getTitle());
		dto.setISBN(b.getISBN());
		dto.setCategory(b.getCategory());
		dto.setPublicationYear(b.getPublicationYear());
		return dto;
	}

	@Transactional
	public Integer createBook(@Valid BookDto b) {
		Book entity = new Book();
		entity.setTitle(b.getTitle());
		entity.setCategory(b.getCategory());
		entity.setISBN(b.getISBN());
		entity.setPublicationYear(b.getPublicationYear());
		Book saved = bookRepo.save(entity);
		return saved.getId();
	}

	@Transactional
	public BookDto updateBook(@Valid BookDto b) {
		Book entity = bookRepo.findById(b.getId()).orElseThrow(() -> new EntityNotFoundException("Book", b.getId()));
		entity.setTitle(b.getTitle());
		entity.setCategory(b.getCategory());
		entity.setISBN(b.getISBN());
		entity.setPublicationYear(b.getPublicationYear());
		bookRepo.save(entity);
		return b;
	}

	@Transactional
	public void deleteBook(int id) {
		bookRepo.deleteById(id);
	}
	
	@Transactional
	 public void addAuthorToBook(int bookId, int authorId) {
		    Book book = bookRepo.findById(bookId)
		        .orElseThrow(() -> new RuntimeException("Book not found"));
		    Author author = authorRepo.findById(authorId)
		        .orElseThrow(() -> new RuntimeException("Author not found"));
		    //book.getAuthors().add(author);
		    //bookRepo.save(book);
		    author.getBooks().add(book);  // ← owning side!
		    authorRepo.save(author);      // ← save author, not book
		}
	
	 @Transactional
	 public void deleteAuthorFromBook(int bookId, int authorId) {
	     Author author = authorRepo.findById(authorId)
	         .orElseThrow(() -> new RuntimeException("Author not found"));
		author.getBooks().removeIf(b -> b.getId() == bookId);
		authorRepo.save(author);
	 }
	 
	public Set<AuthorDto> getAuthorsByBookId(int bookId) {
        Book book = bookRepo.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        Set<AuthorDto> authorDtos = book.getAuthors().stream().map(a -> new AuthorDto(a.getId(), a.getName(), a.getNationality(), a.getBirthdate()))
        		.collect(Collectors.toSet());

        return authorDtos;
    }
	
}
