package com.softuni.springdatalab.services;

import com.softuni.springdatalab.models.Account;
import com.softuni.springdatalab.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Account account = getAccount(id);

        BigDecimal newValue = account.getBalance().subtract(money);

        if (newValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Not enough balance");
        }

        account.setBalance(newValue);

        accountRepository.save(account);
    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot deposit negative value");
        }

        Account account = getAccount(id);

        BigDecimal newValue = account.getBalance().add(money);

        account.setBalance(newValue);

        accountRepository.save(account);
    }

    private Account getAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account doesnt exist"));

        if(account.getOwner() == null) {
            throw new IllegalArgumentException("Account doesnt have user");
        }
        return account;
    }
}
