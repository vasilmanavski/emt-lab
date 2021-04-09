package finki.ukim.mk.emtlab.service;

import finki.ukim.mk.emtlab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Long countryId);

    void deleteById(Long id);
}
