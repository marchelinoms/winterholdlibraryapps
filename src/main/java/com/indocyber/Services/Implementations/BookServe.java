package com.indocyber.Services.Implementations;

import com.indocyber.DTO.Book.BookDto;
import com.indocyber.DTO.Book.BookShowDTO;
import com.indocyber.DTO.Book.UpsertBookDTO;
import com.indocyber.DTO.Book.UpsertBookDtoV2;
import com.indocyber.Entities.Author;
import com.indocyber.Entities.Book;
import com.indocyber.ExceptionHandling.ObjectNotFound;
import com.indocyber.Repositories.AuthorRepository;
import com.indocyber.Repositories.BookRepository;
import com.indocyber.Repositories.CategoryRepository;
import com.indocyber.Services.Interfaces.BookService;
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
public class BookServe implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private int ROWS_PER_PAGE = 5;

    @Override
    public void addBook(UpsertBookDTO dto) {
        Book newBook = new Book();
        newBook.setCode(dto.getCode());
        newBook.setTitle(dto.getTitle());
        newBook.setBorrowed(dto.getBorrowed());
        newBook.setCategoryName(dto.getName());
        newBook.setAuthorId(dto.getAuthor());
        newBook.setSummary(dto.getSummary());
        newBook.setReleaseDate(dto.getReleaseDate());
        newBook.setTotalPage(dto.getTotalPage());

        bookRepository.save(newBook);
    }

    @Override
    public void addBook(BookDto dto) {
        Book newBook = new Book();
        newBook.setCode(dto.getCode());
        newBook.setTitle(dto.getTitle());
        newBook.setBorrowed(dto.getBorrowed());
        newBook.setCategoryName(dto.getCategoryName());
        newBook.setAuthorId(dto.getAuthorId());
        newBook.setSummary(dto.getSummary());
        newBook.setReleaseDate(dto.getReleaseDate());
        newBook.setTotalPage(dto.getTotalPage());

        bookRepository.save(newBook);
    }

    @Override
    public Page<Book> findAllBook(Integer page) {

        Pageable pageable = PageRequest.of(page-1,ROWS_PER_PAGE, Sort.by("code"));

        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findByTitle(String title) {
        Book book = bookRepository.findById(title).get();
        return bookRepository.findByTitle(book);
    }

    @Override
    public List<BookDto> findBooksByAuthor(Integer id) {
        BookDto bookDto = new BookDto();
        Author author = new Author();
        author.setId(id);
        bookDto.setAuthorId(author);
        return bookRepository.findBooksByAuthorId(bookDto);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.deleteById(book.getCode());
    }

    @Override
    public UpsertBookDTO updateBook(String code) {
        Optional<Book> nullEntity = bookRepository.findById(code);
        Book entity = nullEntity.get();
        UpsertBookDTO updateDto = new UpsertBookDTO(
                entity.getCode(),
                entity.getTitle(),
                entity.getBorrowed(),
                entity.getCategoryName().getName(),
                entity.getAuthorId().getId(),
                entity.getSummary(),
                entity.getReleaseDate(),
                entity.getTotalPage()
        );

        return updateDto ;
    }

    @Override
    public Book findByCode(String code) {

        Book book = bookRepository.findById(code)
                .orElseThrow(()->{
                    throw new ObjectNotFound("Book with code : " + code + "Not Found!");
                });
        return book;
    }
    @Override
    public List<Book> findByIsBorrowedFalse() {
        return bookRepository.findByIsBorrowedFalse();
    }

    @Override
    public List<BookShowDTO> findByCategory(String categoryName) {
        List<Book> books = bookRepository.findByCategoryName(categoryName);
        List<BookShowDTO> bookTableDtos = new ArrayList<>();
        books.forEach(book -> bookTableDtos.add(new BookShowDTO(book)));
        return bookTableDtos;}

    @Override
    public void save(UpsertBookDtoV2 dto) {
        Book book = new Book(
                dto.getCode(),
                dto.getTitle(),
                false,
                dto.getSummary(),
                dto.getReleaseDate(),
                dto.getTotalPage());
        book.setAuthorId(authorRepository.findById(dto.getAuthorId()).get());
        book.setCategoryName(categoryRepository.findById(dto.getCategory()).get());
        bookRepository.save(book);

    }

    @Override
    public UpsertBookDtoV2 getUpsertById(String code) {
        Book book = bookRepository.findById(code).get();

        return new UpsertBookDtoV2(book);
    }

    @Override
    public void deleteBookById(String code) {
        bookRepository.deleteById(code);
    }
}
