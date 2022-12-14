package com.indocyber.DTO.Loan;

import com.indocyber.Entities.Book;
import com.indocyber.Entities.Customer;

import java.time.LocalDate;

public class UpsertLoanDTO {

    private Integer id;

    private Customer customerNumber;

    private Book bookCode;

    private LocalDate loanDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private String note;

    public UpsertLoanDTO(Integer id, Customer customerNumber, Book bookCode, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, String note) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.bookCode = bookCode;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Customer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Book getBookCode() {
        return bookCode;
    }

    public void setBookCode(Book bookCode) {
        this.bookCode = bookCode;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
