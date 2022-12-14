package com.indocyber.Services.Implementations;

import com.indocyber.DTO.Category.CategoryDto;
import com.indocyber.DTO.Category.UpsertCategoryDTO;
import com.indocyber.Entities.Category;
import com.indocyber.ExceptionHandling.ObjectNotFound;
import com.indocyber.Repositories.BookRepository;
import com.indocyber.Repositories.CategoryRepository;
import com.indocyber.Services.Interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServe implements CategoryService {

    private int ROWS_PER_PAGE = 5;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;


    @Override
    public void addCategory(UpsertCategoryDTO upsertCategoryDTO) {
        Category newCategory = new Category();
        newCategory.setName(upsertCategoryDTO.getName());
        newCategory.setFloor(upsertCategoryDTO.getFloor());
        newCategory.setIsle(upsertCategoryDTO.getIsle());
        newCategory.setBay(upsertCategoryDTO.getBay());

        categoryRepository.save(newCategory);
    }

    @Override
    public Page<Category> findAllCategory(int page) {
       Pageable pageable = PageRequest.of(page - 1,ROWS_PER_PAGE, Sort.by("name")  );

        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<CategoryDto> findAllCategory(Integer page) {
        List<Category> categories = categoryRepository.findAll(PageRequest.of(page - 1, 10)).getContent();
        List<CategoryDto> categoryTableDtos = new ArrayList<>();
        categories.forEach(category -> {
            CategoryDto categoryTableDto = new CategoryDto(category);
            categoryTableDto.setTotalBooks(bookRepository.countBooksByCategoryName(category.getName()));
            categoryTableDtos.add(categoryTableDto);
        });
        return categoryTableDtos;
    }

    @Override
    public List<CategoryDto> findByName(String name, Integer page) {
        Pageable pageable = PageRequest.of(page - 1,ROWS_PER_PAGE, Sort.by("name")  );

        return categoryRepository.findByName(name,pageable);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepository.deleteById(category.getName());
    }

    @Override
    public UpsertCategoryDTO updateCategory(String name) {
        Optional<Category> nullEntity = categoryRepository.findById(name);
        Category entity = nullEntity.get();
        UpsertCategoryDTO updateDto = new UpsertCategoryDTO(
                entity.getName(),
                entity.getFloor(),
                entity.getIsle(),
                entity.getBay()
        );

        return updateDto;
    }

    @Override
    public Category findById(String categoryName) {
        Category category = categoryRepository.findById(categoryName)
                .orElseThrow(()->{
                    throw new ObjectNotFound("Category Name : " + categoryName + "Not Found/ not exist!");
                });
        return category;
    }
}
