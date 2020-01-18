package guru.springframework.errorhandling.repository;

import guru.springframework.errorhandling.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
