package com.indocyber.Services.Interfaces;

import com.indocyber.DTO.Account.AccountDto;
import com.indocyber.DTO.Account.RegisterDto;
import com.indocyber.Entities.Account;
import org.springframework.data.domain.Page;

public interface AccountService {

    void registerAccount(AccountDto accountDto);
    void registerAccount(RegisterDto dto);
    Page<Account> findAllAccount(Integer page);
    void deleteAccount(Account account);
    Account findAccountByUsername(String username);
}
