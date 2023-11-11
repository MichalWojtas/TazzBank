package com.gmail.wojtass.michal.services;

import com.gmail.wojtass.michal.model.AccountBank;
import com.gmail.wojtass.michal.model.DepositPayout;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gmail.wojtass.michal.model.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{


    List<Transaction> findAllByUsers_UserId(long userId);

    @Modifying
    @Query("UPDATE Transaction t SET t.dateTransaction = :time WHERE t.transactionId = :id")
    void updateDateTransactionByTransactionIdAndDateTransaction(@Param("id") long id, @Param("time") LocalDateTime time);
}
