package com.softuni.springdatalab.repositories;

import com.softuni.springdatalab.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
