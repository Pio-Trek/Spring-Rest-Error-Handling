package guru.springframework.errorhandling;

import guru.springframework.errorhandling.model.Author;
import guru.springframework.errorhandling.model.Book;
import guru.springframework.errorhandling.model.Publisher;
import guru.springframework.errorhandling.repository.AuthorRepository;
import guru.springframework.errorhandling.repository.BookRepository;
import guru.springframework.errorhandling.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbTestInitializer implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    @Autowired
    public DbTestInitializer(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123");
        Publisher hc = new Publisher("Harper Collins", "541 New Street");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(hc);

        authorRepository.save(eric);
        publisherRepository.save(hc);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development Without EJB", "31222");
        Publisher wo = new Publisher("Worx", "4 Noname Rd.");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(wo);

        authorRepository.save(rod);
        publisherRepository.save(wo);
        bookRepository.save(noEJB);

    }
}
