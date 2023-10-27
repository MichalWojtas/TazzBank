package com.gmail.wojtass.michal.services;

import com.gmail.wojtass.michal.model.AccountBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountBankRepository extends JpaRepository<AccountBank,Long> {

    @Query("Select max(accountBankId) from AccountBank")
    long findMaxAccountBankId();

    AccountBank findByAccountBankId(long id);

    @Modifying
    @Query("update AccountBank s set s.accountValue=?1 where s.accountBankId=?2")
    void updateAccountValue(double accountValue,long accountBankId);

    @Query("SELECT ab.user.id FROM AccountBank ab WHERE ab.accountNumber = :accountNumber")
    long findUserIdByAccountNumber(@Param("accountNumber") String accountNumber);

}
