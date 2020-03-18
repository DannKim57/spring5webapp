package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;


@Component
public class BootStrapData implements CommandLineRunner{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setAddress("Newyork");
        publisher.setName("Johnsinna");
        
        publisherRepository.save(publisher);
        System.out.println("Publisher Count: " + publisherRepository.count());
        
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Dmain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);
        System.out.println("Author Count: " + authorRepository.count());




        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE", "33434224234");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);        
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);
        System.out.println("Books Count: " + bookRepository.count());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());


    }
}