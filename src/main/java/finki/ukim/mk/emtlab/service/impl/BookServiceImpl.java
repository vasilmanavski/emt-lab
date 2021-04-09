package finki.ukim.mk.emtlab.service.impl;

import finki.ukim.mk.emtlab.model.Author;
import finki.ukim.mk.emtlab.model.Book;
import finki.ukim.mk.emtlab.model.dto.BookDto;
import finki.ukim.mk.emtlab.model.enums.Category;
import finki.ukim.mk.emtlab.model.exceptions.IdNotFoundException;
import finki.ukim.mk.emtlab.repository.AuthorRepository;
import finki.ukim.mk.emtlab.repository.BookRepository;
import finki.ukim.mk.emtlab.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, String categoryId, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new IdNotFoundException(authorId));

        Category category = Category.valueOf(categoryId);

        this.bookRepository.deleteByName(name);
        Book book = new Book(name, category, author, availableCopies);
        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new IdNotFoundException(bookDto.getAuthor()));

        Category category = Category.valueOf(bookDto.getCategory());

        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), category, author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, String categoryId, Long authorId, Integer availableCopies) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        Category category = Category.valueOf(categoryId);

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new IdNotFoundException(bookDto.getAuthor()));

        Category category = Category.valueOf(bookDto.getCategory());

        book.setName(bookDto.getName());
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        this.bookRepository.save(book);

        return Optional.of(book);
    }


    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> minusAvailableCopies(Long id) {

        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        int minusAvailableCopies = book.getAvailableCopies() - 1;
        book.setAvailableCopies(minusAvailableCopies);

        return Optional.of(this.bookRepository.save(book));

    }
}
