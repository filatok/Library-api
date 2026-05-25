package gr.filatok.library_api.domain.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.filatok.library_api.domain.models.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
	// findAll()    -> inherited from JpaRepository (returns List<Author>)
	// findById(id) -> inherited from JpaRepository (returns Optional<Author>)
	// save(entity) -> inherited from JpaRepository (insert or update)
	// deleteById() -> inherited from JpaRepository
	List<Author> findByName(String name);


}
