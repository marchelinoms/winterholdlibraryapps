package com.indocyber.Controllers.RestControllers;

import com.indocyber.DTO.Loan.LoanDto;
import com.indocyber.DTO.Loan.UpsertLoanDTO;
import com.indocyber.Entities.Loan;
import com.indocyber.Services.Interfaces.BookService;
import com.indocyber.Services.Interfaces.CustomerService;
import com.indocyber.Services.Interfaces.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/loan")
public class LoanRestController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<String> addLoan(@Valid @RequestBody LoanDto dto){

        bookService.findByCode(dto.getBookCode().getCode());
        customerService.findByCustomerId(dto.getCustomerNumber().getMembershipNumber());

        loanService.addLoan(dto);

        return new ResponseEntity<>("Data has been inserted", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Loan>> getAllLoan(@RequestParam(defaultValue = "1") Integer page){

        Page<Loan> loanPage = loanService.findAllLoan(page);

        return new ResponseEntity<>(loanPage,HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<Object>put (@Valid @RequestBody UpsertLoanDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            loanService.addLoan(dto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Validation Failed, Http Request Body is not validated.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLoan (@PathVariable Integer id){
        Loan loan = new Loan();
        loan.setId(id);
        loanService.deleteLoan(loan);

        return ResponseEntity.status(HttpStatus.OK).body("The following loan has been deleted : " + loan + " with id :" + id);

    }
}
