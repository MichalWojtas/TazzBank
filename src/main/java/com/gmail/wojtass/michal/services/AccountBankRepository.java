package com.gmail.wojtass.michal.services;

import com.gmail.wojtass.michal.model.AccountBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountBankRepository extends JpaRepository<AccountBank,Long> {

    @Query("Select max(accountBankId) from AccountBank")
    long findMaxAccountBankId();

    @Query("SELECT MIN(ab.accountBankId) FROM AccountBank ab WHERE ab.user.id = :userId")
    long findMinAccountBankIdByUserId(@Param("userId") long userId);

    AccountBank findByAccountBankId(long id);

    @Modifying
    @Query("update AccountBank s set s.accountValue=?1 where s.accountBankId=?2")
    void updateAccountValue(double accountValue,long accountBankId);

    @Query("SELECT ab.accountBankId FROM AccountBank ab WHERE ab.accountNumber = :accountNumber")
    long findAccountBankIdByAccountNumber(@Param("accountNumber") String accountNumber);

    @Query("SELECT ab.user.id FROM AccountBank ab WHERE ab.accountNumber = :accountNumber")
    long findUserIdByAccountNumber(@Param("accountNumber") String accountNumber);

    @Query("SELECT ab FROM AccountBank ab WHERE ab.accountNumber = :accountNumber")
    AccountBank findAccountBankByAccountNumber(@Param("accountNumber") String accountNumber);

}
