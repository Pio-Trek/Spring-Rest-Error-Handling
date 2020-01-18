package guru.springframework.errorhandling.controller;

import guru.springframework.errorhandling.exception.AuthorNotFoundException;
import guru.springframework.errorhandling.exception.BookNotFoundException;
import guru.springframework.errorhandling.exception.FetchAllInternalException;
import guru.springframework.errorhandling.model.Author;
import guru.springframework.errorhandling.model.Book;
import guru.springframework.errorhandling.repository.AuthorRepository;
import guru.springframework.errorhandling.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class AuthorBookController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorBookController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping(path = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Author>> fetchAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (!authors.isEmpty()) {
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } else {
            throw new FetchAllInternalException();
        }
    }

    @GetMapping(path = "/authors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Author> fetchAuthor(@PathVariable Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return new ResponseEntity<>(author.get(), HttpStatus.OK);
        } else {
            throw new AuthorNotFoundException("No author found here...");
        }
    }

    @GetMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Book>> fetchBooks() {
        List<Book> books = bookRepository.findAll();
        if (!books.isEmpty()) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            throw new FetchAllInternalException();
        }
    }

    @GetMapping(path = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Book> fetchBook(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            throw new BookNotFoundException("No nook found here...");
        }
    }


}