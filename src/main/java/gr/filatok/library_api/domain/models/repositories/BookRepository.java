package gr.filatok.library_api.domain.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.filatok.library_api.domain.models.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	// findAll()    -> inherited from JpaRepository (returns List<Book>)
	// findById(id) -> inherited from JpaRepository (returns Optional<Book>)
	// save(entity) -> inherited from JpaRepository (insert or update)
	// deleteById() -> inherited from JpaRepository
	List<Book> findByTitle(String title);
}
