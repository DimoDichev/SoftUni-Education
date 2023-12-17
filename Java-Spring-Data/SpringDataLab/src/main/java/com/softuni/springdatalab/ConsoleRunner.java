package com.softuni.springdatalab;

import com.softuni.springdatalab.models.Account;
import com.softuni.springdatalab.models.User;
import com.softuni.springdatalab.services.AccountService;
import com.softuni.springdatalab.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    public void run(String... args) throws Exception {
    }
}
