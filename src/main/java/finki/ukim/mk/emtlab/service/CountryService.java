package finki.ukim.mk.emtlab.service;

import finki.ukim.mk.emtlab.model.Author;
import finki.ukim.mk.emtlab.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> save(String name, String continent);

    void deleteById(Long id);
}
