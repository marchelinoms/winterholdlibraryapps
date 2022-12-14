package com.indocyber.Services.Implementations;

import com.indocyber.DTO.Account.AccountDto;
import com.indocyber.DTO.Account.RegisterDto;
import com.indocyber.Entities.Account;
import com.indocyber.Repositories.AccountRepository;
import com.indocyber.Services.Interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AccountServe implements AccountService {

   private AccountRepository accountRepository;
   private int ROWS_PER_PAGE = 5;


    @Autowired
   public AccountServe (AccountRepository accountRepository){
       this.accountRepository = accountRepository;
   }

    @Override
    public void registerAccount(AccountDto accountDto) {
        Account newAccount = new Account();
        newAccount.setUsername(accountDto.getUsername());
        newAccount.setPassword(accountDto.getPassword());

        accountRepository.save(newAccount);
    }

    @Override
    public void registerAccount(RegisterDto dto) {
        Account newAccount = new Account();
        newAccount.setUsername(dto.getUsername());
        newAccount.setPassword(dto.getPassword());

        accountRepository.save(newAccount);
    }

    @Override
    public Page<Account> findAllAccount(Integer page) {
        Pageable pageable = PageRequest.of(page - 1 , ROWS_PER_PAGE, Sort.by("username"));
        return accountRepository.findAll(pageable);
    }

    @Override
    public void deleteAccount(Account account) {
        this.accountRepository.deleteByUsername(account.getUsername());
    }

    @Override
    public Account findAccountByUsername(String username){
       return this.accountRepository.findByUsername(username);
    }

}
