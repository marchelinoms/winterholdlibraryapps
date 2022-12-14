package com.indocyber.Repositories;

import com.indocyber.DTO.Category.CategoryDto;
import com.indocyber.Entities.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,String> {
    @Query("""
			SELECT new com.indocyber.DTO.Category.UpsertCategoryDTO(cat.name,cat.floor,cat.isle,cat.bay) 
			FROM Category AS cat
			WHERE cat.name LIKE %:name%
				""")
    List<CategoryDto> findByName(@Param("name") String name, Pageable pageable);

}
