package com.indocyber.DTO.Book;

import com.indocyber.Entities.Author;
import com.indocyber.Entities.Book;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;

public class BookShowDTO {
    private String code;
    private String bookTitle;
    private String author;
    private String category;
    private String isBorrowed;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String releaseDate;
    private Integer totalPage;

    public BookShowDTO() {
    }

    public BookShowDTO(Book book) {
        this.code = book.getCode();
        this.bookTitle = book.getTitle();
        Author authorObject = book.getAuthorId();
        this.author = authorObject.getTitle() + " " + authorObject.getFirstName() + " " + authorObject.getLastName();
        this.category = book.getCategoryName().getName();
        this.isBorrowed = book.isBorrowed() ? "Borrowed" : "Available";
        this.releaseDate = book.getReleaseDate() != null ? book.getReleaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "-";
        this.totalPage = book.getTotalPage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(String isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
