package com.gmail.wojtass.michal.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "server_info")
@Entity
public class ServerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "limit_transaction_for_day")
    private LocalDateTime lastResetDayTransactionLimitTime = LocalDateTime.now();

    @Column(name = "limit_transaction_for_month")
    private LocalDateTime lastResetMonthTransactionLimitTime = LocalDateTime.now();

    @PrePersist
    protected void prePersist() {
        if (lastResetDayTransactionLimitTime == null) {
            lastResetDayTransactionLimitTime = LocalDateTime.now();
        }
        if (lastResetMonthTransactionLimitTime == null) {
            lastResetMonthTransactionLimitTime = LocalDateTime.now();
        }
    }

    public ServerInfo() {
    }

    public ServerInfo(long id, LocalDateTime lastResetDayTransactionLimitTime, LocalDateTime lastResetMonthTransactionLimitTime) {
        this.id = id;
        this.lastResetDayTransactionLimitTime = lastResetDayTransactionLimitTime;
        this.lastResetMonthTransactionLimitTime = lastResetMonthTransactionLimitTime;
    }

    public LocalDateTime getLastResetDayTransactionLimitTime() {
        return lastResetDayTransactionLimitTime;
    }

    public void setLastResetDayTransactionLimitTime(LocalDateTime lastResetDayTransactionLimitTime) {
        this.lastResetDayTransactionLimitTime = lastResetDayTransactionLimitTime;
    }

    public LocalDateTime getLastResetMonthTransactionLimitTime() {
        return lastResetMonthTransactionLimitTime;
    }

    public void setLastResetMonthTransactionLimitTime(LocalDateTime lastResetMonthTransactionLimitTime) {
        this.lastResetMonthTransactionLimitTime = lastResetMonthTransactionLimitTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
