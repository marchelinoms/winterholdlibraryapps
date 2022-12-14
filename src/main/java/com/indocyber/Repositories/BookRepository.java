package com.indocyber.Repositories;

import com.indocyber.DTO.Book.BookDto;
import com.indocyber.Entities.Author;
import com.indocyber.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface BookRepository extends JpaRepository<Book,String> {
    @Query("""
            SELECT COUNT(*)
            FROM Book AS book
            WHERE book.categoryName=:categoryName""")
    int countBooksByCategoryName(String categoryName);
    List<BookDto> findBooksByAuthorId(BookDto bookDto);
    Book findByTitle(Book book);
    List<Book> findByCategoryName(String categoryName);
    List<Book> findByIsBorrowedFalse();
}
