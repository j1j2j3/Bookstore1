package fi.haaga.spring.harjoitusprojekti.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haaga.spring.harjoitusprojekti.Bookstore.domain.Book;
import fi.haaga.spring.harjoitusprojekti.Bookstore.domain.BookRepository;
import fi.haaga.spring.harjoitusprojekti.Bookstore.domain.CategoryRepository;



@Controller
//@ResponseBody
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository; 
	
	@RequestMapping(value="/booklist", method=RequestMethod.GET)
	public String books(Model model) {
		model.addAttribute("book", repository.findAll());
		return "booklist";
	}
	
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    

	// RESTful service to get book by id
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findOne(bookId);
    }     
    
	
	@RequestMapping(value = "/add")
	public String addBook(Model model){
	model.addAttribute("book", new Book());
	model.addAttribute("categories", crepository.findAll());
	return "addbook";
	} 
	@RequestMapping(value="/addbook", method=RequestMethod.POST)
	public String addBook(Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}
	
	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findOne(bookId));
		model.addAttribute("categories", crepository.findAll());
	return "editbook";
	}
	
	@RequestMapping(value="/editbook/editbook", method=RequestMethod.POST)
	public String editBook(Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}
	

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	repository.delete(bookId);
	return "redirect:/booklist";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}  

}


