package com.gmail.wojtass.michal.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmail.wojtass.michal.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{

}
