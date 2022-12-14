package com.indocyber.DTO.Book;

import com.indocyber.Entities.Author;
import com.indocyber.Entities.Category;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UpsertBookDTO {

    private String code;
    private String title;
    private Boolean isBorrowed;
    private Category name;
    private String categoryName;
    private Integer authorId;
    private Author author;
    private String summary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private Integer totalPage;

    public UpsertBookDTO(String code, String title, Boolean isBorrowed, String categoryName, Integer authorId, String summary, LocalDate releaseDate, Integer totalPage) {
        this.code = code;
        this.title = title;
        this.isBorrowed = isBorrowed;
        this.categoryName = categoryName;
        this.authorId = authorId;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.totalPage = totalPage;
    }

    public Category getName() {
        return name;
    }

    public void setName(Category name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
