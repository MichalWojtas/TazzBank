package com.gmail.wojtass.michal.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "server_info")
@Entity
public class ServerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "server_info_id_seq")
    @SequenceGenerator(name = "server_info_id_seq", sequenceName = "server_info_id_seq", allocationSize = 1)
    @Column(name = "server_info_id")
    private long serverInfoId;

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

    public ServerInfo(LocalDateTime lastResetDayTransactionLimitTime, LocalDateTime lastResetMonthTransactionLimitTime) {
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

    public long getServerInfoId() {
        return serverInfoId;
    }

    public void setServerInfoId(long serverInfoId) {
        this.serverInfoId = serverInfoId;
    }
}
