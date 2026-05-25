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
import gr.filatok.library_api.business.services.AuthorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("authors")
public class AuthorController {

	private AuthorService as;

	public AuthorController(AuthorService as) {
		this.as = as;
	}

	@GetMapping
	public ResponseEntity<?> getAuthors()
	{
		List<AuthorDto> response = as.getAuthors();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAuthorById(@PathVariable Integer id) {
		AuthorDto response = as.getAuthorById(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}/books")
    public ResponseEntity<?> getBooksByAuthorId(@PathVariable int id) {
        Set<BookDto> response = as.getBooksByAuthorId(id);
        return ResponseEntity.ok(response);
    }

	@PostMapping
	public ResponseEntity<?> createAuthor(@Valid @RequestBody AuthorDto dto){
		Integer id = as.createAuthor(dto);
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateAuthor(@PathVariable Integer id, @Valid @RequestBody AuthorDto dto){
		dto.setId(id);
		AuthorDto updated = as.updateAuthor(dto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id){
		as.deleteAuthor(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{authorId}/books/{bookId}")
	public ResponseEntity<?> addBookToAuthor(@PathVariable int authorId, @PathVariable int bookId) {
	    as.addBookToAuthor(authorId, bookId);
	    return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{authorId}/books/{bookId}")
	public ResponseEntity<?> deleteBookFromAuthor(@PathVariable int authorId, @PathVariable int bookId) {
	    as.deleteBookFromAuthor(authorId, bookId);
	    return ResponseEntity.ok().build();
	}
}
