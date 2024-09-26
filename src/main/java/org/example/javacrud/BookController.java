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
}
