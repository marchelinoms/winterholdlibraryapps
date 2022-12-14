package com.indocyber.Controllers.RestControllers;

import com.indocyber.DTO.Book.BookDto;
import com.indocyber.DTO.Book.UpsertBookDTO;
import com.indocyber.Entities.Book;
import com.indocyber.Services.Interfaces.AuthorService;
import com.indocyber.Services.Interfaces.BookService;
import com.indocyber.Services.Interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;

    @Autowired
    public BookRestController(BookService bookService, AuthorService authorService, CategoryService categoryService){
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<String> addBook (@Valid @RequestBody UpsertBookDTO bookDto){

        authorService.findById(bookDto.getAuthorId());
        categoryService.findById(bookDto.getCategoryName());

        bookService.addBook(bookDto);

        return new ResponseEntity<>("Data has been inserted!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBook (@RequestParam (defaultValue = "1") Integer page){

        Page<Book> books = bookService.findAllBook(page);

        return new ResponseEntity<>(books,HttpStatus.FOUND);
    }


    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpsertBookDTO dto, BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            bookService.addBook(dto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Data has been updated with following detail :" + dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Validation Failed, Http Request Body is not validated.");
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> deleteBook (@PathVariable String code){
        Book book = new Book();
        book.setCode(code);
        bookService.deleteBook(book);

        return ResponseEntity.status(HttpStatus.OK).body("The following book (book code) has been deleted : " + code);
    }
}

