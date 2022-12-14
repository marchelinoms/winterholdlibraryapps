package com.indocyber.Repositories;

import com.indocyber.DTO.Author.AuthorDto;
import com.indocyber.DTO.Author.UpsertAuthorDTO;
import com.indocyber.Entities.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query("""
			SELECT new com.indocyber.DTO.Author.UpsertAuthorDTO(auth.id, auth.title, auth.firstName, auth.lastName, auth.birthDate, auth.deceasedDate, auth.education , auth.summary) 
			FROM Author AS auth
			WHERE auth.firstName LIKE %:firstName%
				OR auth.lastName LIKE %:lastName%
				""")
    public List<AuthorDto> findByName(@Param("firstName") String nameCategory, Pageable pageable);
	@Query("""
			SELECT author FROM Author as author
			WHERE CONCAT(author.title,' ',author.firstName,' ',author.lastName) LIKE %:nameCategory%
				""")
	public List<Author> findByNameSingle(@Param("nameCategory") String nameCategory);
}
