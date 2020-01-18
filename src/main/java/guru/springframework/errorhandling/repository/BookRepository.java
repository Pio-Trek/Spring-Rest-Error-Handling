package guru.springframework.errorhandling.repository;

import guru.springframework.errorhandling.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
