package com.indocyber.Controllers.MVCControllers;

import com.indocyber.DTO.Book.BookShowDTO;
import com.indocyber.DTO.Book.UpsertBookDtoV2;
import com.indocyber.DTO.Category.CategoryDto;
import com.indocyber.DTO.Category.UpsertCategoryDTO;
import com.indocyber.Entities.Category;
import com.indocyber.Services.Interfaces.AuthorService;
import com.indocyber.Services.Interfaces.BookService;
import com.indocyber.Services.Interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthorService authorService;


    //Tampilan Buku berdasarkan kategori
    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page, Model model){
        List<CategoryDto> categories = categoryService.findAllCategory(page);
        model.addAttribute("categories",categories);
        return "book-index";
    }

    //All About Category Data Handling
    @GetMapping("/categoryForm")
    public String categoryForm(@RequestParam(required = false) String name, Model model){
        if(name == null){
            model.addAttribute("category", new UpsertCategoryDTO());
        } else{
            model.addAttribute("category", categoryService.updateCategory(name));
        }
        return "category-register";
    }

    @PostMapping("/processCategoryForm")
    public String processCategoryForm(@ModelAttribute("category") UpsertCategoryDTO dto){
        categoryService.addCategory(dto);

        return "redirect:/book/index";
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("name") String name){
        Category category = new Category();
        category.setName(name);
        categoryService.deleteCategory(category);
        return "redirect:/book/index";
    }

    //Tampilan seluruh buku
    @GetMapping("/books")
    public String showBooks(@RequestParam("category") String category, Model model) {
        List<BookShowDTO> bookTableDtos = bookService.findByCategory(category);
        model.addAttribute("books", bookTableDtos);
        model.addAttribute("category", category);
        return "books";
    }

    //All About Book Data Handling
    @GetMapping("/bookForm")
    public String bookForm(@RequestParam("category") String category, @RequestParam(required = false) String code, Model model) {
        model.addAttribute("authors", authorService.findAll());
        if (code == null) {
            UpsertBookDtoV2 dto = new UpsertBookDtoV2();
            dto.setCategory(category);
            model.addAttribute("book", dto);
        } else {
            model.addAttribute("book", bookService.getUpsertById(code));
        }
        return "book-register";
    }

    @PostMapping("/processBookForm")
    public String processBook(@ModelAttribute("book") UpsertBookDtoV2 dto, RedirectAttributes redirectAttributes) {
        bookService.save(dto);
        redirectAttributes.addAttribute("category", dto.getCategory());
        return "redirect:/book/books";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("code") String code, @RequestParam("category") String category, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("category", category);
        bookService.deleteBookById(code);
        return "redirect:/book/books";
    }
}

