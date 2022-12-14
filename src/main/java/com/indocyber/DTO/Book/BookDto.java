package com.indocyber.DTO.Book;

import com.indocyber.Entities.Author;
import com.indocyber.Entities.Category;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class BookDto {

    private String code;

    private String title;

    private Boolean isBorrowed;

    private Category categoryName;
    private Author authorId;
    private String summary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private Integer totalPage;

    public BookDto() {
    }

    public BookDto(String code, String title, Boolean isBorrowed, Category categoryName, Author authorId, String summary, LocalDate releaseDate, Integer totalPage) {
        this.code = code;
        this.title = title;
        this.isBorrowed = isBorrowed;
        this.categoryName = categoryName;
        this.authorId = authorId;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.totalPage = totalPage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }

    public Category getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Category categoryName) {
        this.categoryName = categoryName;
    }

    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
