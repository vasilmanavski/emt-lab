package finki.ukim.mk.emtlab.repository;

import finki.ukim.mk.emtlab.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    void deleteByName(String name);
}
