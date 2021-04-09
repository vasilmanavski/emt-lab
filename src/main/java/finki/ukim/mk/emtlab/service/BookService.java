package finki.ukim.mk.emtlab.service;

import finki.ukim.mk.emtlab.model.Book;
import finki.ukim.mk.emtlab.model.dto.BookDto;
import finki.ukim.mk.emtlab.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, String categoryId, Long authorId, Integer availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, String categoryId, Long authorId, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    Optional<Book> minusAvailableCopies(Long id);
}
