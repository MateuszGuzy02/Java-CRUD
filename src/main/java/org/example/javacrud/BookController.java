package org.example.javacrud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;


    @GetMapping("/books")
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @GetMapping("/books/{id}")
    public Book getById(@PathVariable("id") int id) {
        return bookRepository.getById(id);
    }

    @PostMapping("/books")
    public int add(@RequestBody List<Book> books) {
        return bookRepository.save(books);
    }

    @PutMapping("/books/{id}")       // Put podmienia cały obiekt
    public int update(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.getById(id);

        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setGenre(updatedBook.getGenre());
            book.setPublished_year(updatedBook.getPublished_year());

            bookRepository.update(book);

            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/books/{id}")        // Podmiana wybranych pól
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.getById(id);

        if (book != null) {
            if(updatedBook.getTitle() != null) book.setTitle(updatedBook.getTitle());
            if(updatedBook.getAuthor() != null) book.setAuthor(updatedBook.getAuthor());
            if(updatedBook.getGenre() != null) book.setGenre(updatedBook.getGenre());
            if(updatedBook.getPublished_year() > 0) book.setPublished_year(updatedBook.getPublished_year());

            bookRepository.update(book);

            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/books/{id}")
    public int delete(@PathVariable("id") int id) {
        return bookRepository.delete(id);
    }
}
