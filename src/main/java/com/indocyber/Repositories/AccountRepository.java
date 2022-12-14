package com.indocyber.Repositories;

import com.indocyber.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account,String> {

    Account findByUsername(String username);
    Account deleteByUsername(String username);


}
