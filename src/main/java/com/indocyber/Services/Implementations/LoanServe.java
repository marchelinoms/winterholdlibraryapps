package com.indocyber.Services.Implementations;

import com.indocyber.DTO.Loan.LoanDto;
import com.indocyber.DTO.Loan.UpsertLoanDTO;
import com.indocyber.Entities.Loan;
import com.indocyber.Repositories.LoanRepository;
import com.indocyber.Services.Interfaces.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoanServe implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    private int ROWS_PER_PAGE = 5;

    @Override
    public void addLoan(LoanDto dto) {
        Loan newLoan = new Loan();
        newLoan.setCustomerNumber(dto.getCustomerNumber());
        newLoan.setBookCode(dto.getBookCode());
        newLoan.setLoanDate(dto.getLoanDate());
        dto.setDueDate(LocalDate.now().plusDays(5));
        newLoan.setDueDate(dto.getDueDate());
        newLoan.setNote(dto.getNote());

        loanRepository.save(newLoan);
    }

    @Override
    public void addLoan(UpsertLoanDTO dto) {
        Loan newLoan = new Loan();
        newLoan.setCustomerNumber(dto.getCustomerNumber());
        newLoan.setBookCode(dto.getBookCode());
        newLoan.setLoanDate(dto.getLoanDate());
        dto.setDueDate(LocalDate.now().plusDays(5));
        newLoan.setDueDate(dto.getDueDate());
        newLoan.setNote(dto.getNote());

        loanRepository.save(newLoan);
    }

    @Override
    public Page<Loan> findAllLoan(Integer page) {

        Pageable pageable = PageRequest.of(page - 1 , ROWS_PER_PAGE , Sort.by("id"));

        return loanRepository.findAll(pageable);
    }

    @Override
    public UpsertLoanDTO getupdateLoan(Integer id) {
        Optional<Loan> nullEntity = loanRepository.findById(id);
        Loan entity = nullEntity.get();
        UpsertLoanDTO updateDto = new UpsertLoanDTO(
                entity.getId(),
                entity.getCustomerNumber(),
                entity.getBookCode(),
                entity.getLoanDate(),
                entity.getDueDate(),
                entity.getReturnDate(),
                entity.getNote()
        );

        return updateDto;
    }

    @Override
    public void deleteLoan(Loan loan) {
        loanRepository.deleteById(loan.getId());
    }


}
