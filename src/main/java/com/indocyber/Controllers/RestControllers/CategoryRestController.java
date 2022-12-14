package com.indocyber.Controllers.RestControllers;

import com.indocyber.DTO.Category.UpsertCategoryDTO;
import com.indocyber.Entities.Category;
import com.indocyber.Services.Interfaces.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> addCategory(@Valid @RequestBody UpsertCategoryDTO dto){

        categoryService.addCategory(dto);

        return new ResponseEntity<>("Data has been inserted!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Category>> getAllCategory (@RequestParam (defaultValue = "1") int page){

        Page<Category> categories = categoryService.findAllCategory(page);

        return new ResponseEntity<>(categories,HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<Object> put (@Valid @RequestBody UpsertCategoryDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            categoryService.addCategory(dto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        }else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Validation Failed, Http Request Body is not validated.");
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteCategory(@PathVariable String name){
        Category category = new Category();
        category.setName(name);
        categoryService.deleteCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body("The following category has been deleted : " + name);
    }
}
