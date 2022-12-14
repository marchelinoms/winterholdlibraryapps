package com.indocyber.Controllers.RestControllers;

import com.indocyber.DTO.Account.AccountDto;
import com.indocyber.Entities.Account;
import com.indocyber.ExceptionHandling.ObjectNotFound;
import com.indocyber.Services.Interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccRestController {

    private AccountService accountService;

    @Autowired
    public AccRestController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> registerAccount(@Valid @RequestBody AccountDto accountDto){

        accountService.registerAccount(accountDto);

        return new ResponseEntity<>("Data has been inserted !", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Account>> getAllAccount (@RequestParam(defaultValue = "1") Integer page){

        Page<Account> accounts = accountService.findAllAccount(page);

        return new ResponseEntity<>(accounts,HttpStatus.FOUND);
    }

    @GetMapping(path = "{username}")
    public ResponseEntity<Object> getAccountByUsername (@PathVariable("username") String username){
        Account account = accountService.findAccountByUsername(username);

        if(account == null){
            throw new ObjectNotFound("Account with username : " + username + "not found/exist!");
        }

        return new ResponseEntity<>(account,HttpStatus.FOUND);
    }

    @DeleteMapping(path = "{username}")
    public ResponseEntity<String> deleteAccount(@PathVariable("username") String username){
        Account account = new Account();
        account.setUsername(username);

        accountService.deleteAccount(account);

        return new ResponseEntity<>("Data has been deleted",HttpStatus.ACCEPTED);
    }
}
