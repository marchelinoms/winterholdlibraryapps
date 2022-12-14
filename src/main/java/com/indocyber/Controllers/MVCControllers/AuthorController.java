package com.indocyber.Controllers.MVCControllers;

import com.indocyber.DTO.Author.AuthorDto;
import com.indocyber.DTO.Book.BookDto;
import com.indocyber.Entities.Author;
import com.indocyber.Services.Interfaces.AuthorService;
import com.indocyber.Services.Interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page, Model model){
        List<AuthorDto> dataAuthor = this.authorService.findAllAuthorDto(page);
        System.out.println(dataAuthor);
        model.addAttribute("author",dataAuthor);
        return "author-index";
    }

    @GetMapping("/addAuthor")
    public String addAuthor(Model model){
        Author author = new Author();
        model.addAttribute("author",author);
        return "author-register";
    }

    @PostMapping("/processRegisterAuthor")
    public String dataAuthorProcess(@ModelAttribute("author") AuthorDto author){
        this.authorService.addAuthor(author);
        return "redirect:/author/index";
    }

    @GetMapping("/editAuthor/{id}")
    public String editAuthor(@PathVariable("id") int id, Model model) {
        AuthorDto authorDto = authorService.findById(id);
        model.addAttribute("author", authorDto);
        System.out.println(authorDto.getId());
        return "author-edit";
    }

    @PostMapping("/updateAuthor")
    public String updateAuthor(@ModelAttribute("author") AuthorDto dto){
        this.authorService.addAuthor(dto);
        return "redirect:/author/index";
    }

    @GetMapping("/searchAuthor")
    public String searchAuthor(@RequestParam(defaultValue = "", name = "search") String name ,
                               Model model){
        List<AuthorDto> dataAuthor = this.authorService.findByName(name);
        System.out.println(dataAuthor);
        System.out.println(name);
        model.addAttribute("dataAuthor",dataAuthor);

        return "redirect:/author/index";
    }

    @GetMapping("/authorDetails/{id}")
    public String authorDetails(@PathVariable("id") Integer id , Model model){
       List<BookDto> bookDto = bookService.findBooksByAuthor(id);
       AuthorDto authorDto = authorService.findById(id);

       model.addAttribute("bookDto",bookDto);
       model.addAttribute("authorDto",authorDto);

       return "authorDetails";
    }

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor (@PathVariable("id") Integer id){
        AuthorDto authorDto = this.authorService.findById(id);
        Author author = new Author();
        author.setId(authorDto.getId());
        authorService.deleteAuthor(author);
        return "redirect:/author/index";
    }
}
