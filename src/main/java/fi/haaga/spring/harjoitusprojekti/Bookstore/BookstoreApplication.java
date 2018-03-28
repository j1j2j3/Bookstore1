package fi.haaga.spring.harjoitusprojekti.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haaga.spring.harjoitusprojekti.Bookstore.domain.Book;
import fi.haaga.spring.harjoitusprojekti.Bookstore.domain.BookRepository;
import fi.haaga.spring.harjoitusprojekti.Bookstore.domain.Category;
import fi.haaga.spring.harjoitusprojekti.Bookstore.domain.CategoryRepository;
import fi.haaga.spring.harjoitusprojekti.Bookstore.domain.User;
import fi.haaga.spring.harjoitusprojekti.Bookstore.domain.UserRepository;



@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository,  UserRepository urepository){
		return args -> {
			log.info("save a couple of books");
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Science"));
			crepository.save(new Category("Fantasy"));
			
			
			repository.save(new Book(1, "A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", crepository.findByName("Horror").get(0)));
			repository.save(new Book(2, "Animal Farm", "George Orwell", 1945, "2212343-5", crepository.findByName("Horror").get(0)));
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6","heyy@gmail.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C","hey@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
	
	}



