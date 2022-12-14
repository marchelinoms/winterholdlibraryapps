package com.indocyber.Controllers.RestControllers;

import com.indocyber.DTO.Author.AuthorDto;
import com.indocyber.DTO.Author.UpsertAuthorDTO;
import com.indocyber.Entities.Author;
import com.indocyber.Services.Interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/author")
public class AuthorRestController {

    private AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<String> addAuthor(@Valid @RequestBody AuthorDto authorDto){

        authorService.addAuthor(authorDto);

        return new ResponseEntity<>("Data has been inserted!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Author>> getAllAuthor (@RequestParam(defaultValue = "1") Integer page){

        Page<Author> authors = authorService.findAllAuthor(page);

        return new ResponseEntity<>(authors,HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpsertAuthorDTO dto, BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            authorService.addAuthor(dto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Validation Failed, Http Request Body is not validated.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor (@PathVariable Integer id){
        Author author = new Author();
        author.setId(id);
        authorService.deleteAuthor(author);

        return ResponseEntity.status(HttpStatus.OK).body("The following id has been deleted : " + id);
    }
}
