package pl.akademiakodu.ksiazki.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.ksiazki.model.Book;
import pl.akademiakodu.ksiazki.repository.BookRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/*
@CrossOrigin sprawia, że to nasze api dostepne bedzie dla innych :)
 */
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class BookController {
    private BookRepository bookRepository;

    @Autowired /* niewymagane */
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("books")
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @PostMapping("books")
    public Book createBook(@RequestParam String title,
                           @RequestParam String author,
                           @RequestParam(required = false) Integer categoryId) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategoryId(categoryId);
        return bookRepository.save(book);
    }

    @PutMapping("books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id,
                           @RequestParam String title,
                           @RequestParam String author,
                           @RequestParam(required = false) Integer categoryId) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategoryId(categoryId);
            return new ResponseEntity<>(bookRepository.save(book),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // save zapisuje do bazy jesli book nie istnieje (nie ma id) a jesli istnieje to robi update

    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}