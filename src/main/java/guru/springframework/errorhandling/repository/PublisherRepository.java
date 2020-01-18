package guru.springframework.errorhandling.repository;

import guru.springframework.errorhandling.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
