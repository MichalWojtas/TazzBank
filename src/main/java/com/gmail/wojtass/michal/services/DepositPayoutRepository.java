package com.gmail.wojtass.michal.services;

import com.gmail.wojtass.michal.model.AccountBank;
import com.gmail.wojtass.michal.model.DepositPayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepositPayoutRepository extends JpaRepository<DepositPayout,Long> {


    @Query("SELECT ab.accountBank FROM DepositPayout ab WHERE ab.secondId = :secondId")
    AccountBank findBySecondId(@Param("secondId") long secondId);

}
