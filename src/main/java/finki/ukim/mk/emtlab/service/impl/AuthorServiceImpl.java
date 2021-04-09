package finki.ukim.mk.emtlab.service.impl;

import finki.ukim.mk.emtlab.model.Author;
import finki.ukim.mk.emtlab.model.Country;
import finki.ukim.mk.emtlab.model.exceptions.IdNotFoundException;
import finki.ukim.mk.emtlab.repository.AuthorRepository;
import finki.ukim.mk.emtlab.repository.CountryRepository;
import finki.ukim.mk.emtlab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new IdNotFoundException(countryId));

        Author author = new Author(name, surname, country);
        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
