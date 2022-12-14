package com.indocyber.DTO.Book;

import com.indocyber.Entities.Book;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UpsertBookDtoV2 {
    private String code;
    private String title;
    private String category;
    private Integer authorId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private Integer totalPage;
    private String summary;

    public UpsertBookDtoV2() {
    }

    public UpsertBookDtoV2(Book book) {
        this.code = book.getCode();
        this.title = book.getTitle();
        this.category = book.getCategoryName().getName();
        this.authorId = book.getAuthorId().getId();
        this.releaseDate = book.getReleaseDate();
        this.totalPage = book.getTotalPage();
        this.summary = book.getSummary();
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
