package gr.filatok.library_api.api.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.filatok.library_api.business.dtos.AuthorDto;
import gr.filatok.library_api.business.dtos.BookDto;
import gr.filatok.library_api.business.services.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("books")
public class BookController {

	private BookService bs;

	public BookController(BookService bs) {
		this.bs = bs;
	}

	@GetMapping
	public ResponseEntity<?> getBooks(){
		List<BookDto> response = bs.getBooks();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable Integer id){
		BookDto response = bs.getBookById(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}/authors")
    public ResponseEntity<?> getAuthorsByBookId(@PathVariable int id) {
        Set<AuthorDto> response = bs.getAuthorsByBookId(id);
        return ResponseEntity.ok(response);
    }

	@PostMapping
	public ResponseEntity<?> createBook(@Valid @RequestBody BookDto dto){
		Integer id = bs.createBook(dto);
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Integer id, @Valid @RequestBody BookDto dto){
		dto.setId(id);
		BookDto updated = bs.updateBook(dto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Integer id){
		bs.deleteBook(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{bookId}/authors/{authorId}")
	public ResponseEntity<?> addAuthorToBook(@PathVariable int bookId, @PathVariable int authorId) {
	    bs.addAuthorToBook(bookId, authorId);
	    return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping("/{bookId}/authors/{authorId}")
	public ResponseEntity<?> deleteAuthorFromBook(@PathVariable int bookId, @PathVariable int authorId) {
	    bs.deleteAuthorFromBook(bookId, authorId);
	    return ResponseEntity.ok().build();
	}
}
