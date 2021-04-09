package finki.ukim.mk.emtlab.repository;

import finki.ukim.mk.emtlab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);

    Optional<Book> deleteByName(String name);
}
