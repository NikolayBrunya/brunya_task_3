package ru.haulmont.brunya_task_3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.haulmont.brunya_task_3.Service.BookRepository;
import ru.haulmont.brunya_task_3.Service.BookService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    private final BookRepository bookRepository;

    private List<Book> listBook;
    @Value("${custom.config.variable-non:Default}") // непонятно почему не подтягивается
    private String var_company;

    @Autowired
    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;

    }

    @GetMapping("/all")
    @Profile("DEV")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/all")
    @Profile("PROD")
    public ResponseEntity<List<Book>> getAllBooksLocal() {
        return new ResponseEntity<>(listBook, HttpStatus.OK);
    }
    @Profile("DEV")
    @GetMapping("/create")
        public ResponseEntity<Book> createBook(@RequestParam String name) {
        Book book = new Book();
        book.setName(name);
        book.setCompany(var_company);
        bookRepository.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @Profile("PROD")
    @GetMapping("/create")
    public ResponseEntity<Book> createBookLocal(@RequestParam String name) {
        Book book = new Book();
        book.setName(name);
        book.setCompany(var_company);
        listBook.add(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostConstruct
    public void initMethod(){
        listBook =  new ArrayList<Book>();
    }
}
