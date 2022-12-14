package com.indocyber.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "Code",unique = true)
    private String code;

    @Column(name = "Title")
    private String title;

    @Column(name = "IsBorrowed")
    private Boolean isBorrowed;

    @ManyToOne
    @JoinColumn(name = "CategoryName")
    private Category categoryName;

    @ManyToOne
    @JoinColumn(name = "AuthorId")
    private Author authorId;

    @Column(name = "Summary")
    private String summary;

    @Column(name = "ReleaseDate")
    private LocalDate releaseDate;

    @Column(name = "TotalPage")
    private Integer totalPage;

    @OneToMany(mappedBy = "bookCode")
    private List<Loan> loans;

    public Book(){}

    public Book(String code, String title, Boolean isBorrowed, Category categoryName, Author authorId, String summary, LocalDate releaseDate, Integer totalPage) {
        this.code = code;
        this.title = title;
        this.isBorrowed = isBorrowed;
        this.categoryName = categoryName;
        this.authorId = authorId;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.totalPage = totalPage;
    }

    public Book(String code, String title, Boolean isBorrowed, Category categoryName, Author authorId, String summary, LocalDate releaseDate, Integer totalPage, List<Loan> loans) {
        this.code = code;
        this.title = title;
        this.isBorrowed = isBorrowed;
        this.categoryName = categoryName;
        this.authorId = authorId;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.totalPage = totalPage;
        this.loans = loans;
    }

    public Book(String code, String title, boolean isBorrowed, String summary, LocalDate releaseDate, Integer totalPage) {
        this.code = code;
        this.title = title;
        this.isBorrowed = isBorrowed;
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
    public Boolean isBorrowed() {
        return isBorrowed;
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
