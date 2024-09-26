package org.example.javacrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM book",
                BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Book getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE " + "id = ?",
                BeanPropertyRowMapper.newInstance(Book.class), id);
    }

    public int save(List<Book> books) {
        books.forEach(book -> jdbcTemplate
                .update("INSERT INTO book(title, author, genre, published_year) VALUES(?, ?, ?, ?)",
                        book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublished_year()
                ));

        return 1;
    }
}
