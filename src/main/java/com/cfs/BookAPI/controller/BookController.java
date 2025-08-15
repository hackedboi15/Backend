package com.cfs.BookAPI.controller;

import com.cfs.BookAPI.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private Map<Long, Book> bookDB = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        return ResponseEntity.ok(new ArrayList<>(bookDB.values()));
    }
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        bookDB.put(book.getId(),book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookbyId(@PathVariable Long id){
        Book book = bookDB.get(id);
        if(book == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null );
        }
        return ResponseEntity.ok(book);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id,@RequestBody Book book){
        Book existingBook = bookDB.get(id);
        if(existingBook == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        bookDB.put(id,book);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<Book> updatePrice(@PathVariable Long id,@RequestBody Double newPrice){
        Book existingBook = bookDB.get(id);
        if(existingBook == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        existingBook.setPrice(newPrice);
        bookDB.put(id,existingBook);
        return ResponseEntity.ok(existingBook);

    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Book> updatePrice(@PathVariable Long id){
        Book existingBook = bookDB.remove(id);
        if(existingBook == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();

    }



}
