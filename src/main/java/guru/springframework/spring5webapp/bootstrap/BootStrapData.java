package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in BootStrap.");
        Publisher penguin = new Publisher("Penguin Psychology", "UK", "myCity", "myState", "myZip");

        publisherRepository.save(penguin);
        System.out.println("Publisher count is: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        ddd.getAuthors().add(eric);
        eric.getBooks().add(ddd);

        ddd.setPublisher(penguin);
        penguin.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(penguin);

        Author rod = new Author("rod", "johnson");
        Book noEJB = new Book("J2EE development without EJB", "465789");

        noEJB.getAuthors().add(rod);
        rod.getBooks().add(noEJB);
        noEJB.setPublisher(penguin);
        penguin.getBooks().add(noEJB);




        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(penguin);


        System.out.println("Number of Books are: " + bookRepository.count());

        System.out.println("Publisher number of Books: " + penguin.getBooks().size());
    }
}
