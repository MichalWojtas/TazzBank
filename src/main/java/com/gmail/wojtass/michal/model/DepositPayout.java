package com.gmail.wojtass.michal.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "deposit_payout")
@Entity
public class DepositPayout {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_id_seq")
    @SequenceGenerator(name = "deposit_id_seq", sequenceName = "deposit_id_seq", allocationSize = 1)
    @Column(name = "deposit_id")
    private long depositId;

    //I must did it because namedParameterJdbcTemplate().query(sqlQuery, params, new BeanPropertyRowMapper<>(DepositPayout.class)); when i getting result from
    // database by it, this return all id primaryKey as 0. When i check it by normal sql with JdbcTemplate is all ok, also by JPA, then i decide to copy it to this.
    // Don't know for now how to take data with lot parameters in a different way, maintaining the style I chose.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_id_seq")
    @Column(name = "second_id")
    private long secondId;

    @Column(name = "deposit_date")
    private LocalDateTime depositDate;

    @Column(name = "deposit_value")
    private double depositValue;

    @Column(name = "deposit_value_after_change")
    private double depositValueAfterChange;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "deposit_type")
    private DepositPayout.DepositType depositType;

    @Column(name = "name_and_location_atm")
    private String nameAndLocationATM;

    @ManyToOne
    @NotNull
    private AccountBank accountBank;

    @ManyToOne
    @NotNull
    private User user;

    public enum DepositType{
        DEPOSIT,PAYOUT,INTEREST,FEE
    }

    public DepositPayout(){}

    public long getDepositId() {
        return depositId;
    }

    public LocalDateTime getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDateTime depositDate) {
        this.depositDate = depositDate;
    }

    public double getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(double depositValue) {
        this.depositValue = depositValue;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public String getNameAndLocationATM() {
        return nameAndLocationATM;
    }

    public void setNameAndLocationATM(String nameAndLocationATM) {
        this.nameAndLocationATM = nameAndLocationATM;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDepositDateToString(){
        String dateInString = depositDate.getDayOfMonth() + "." + depositDate.getMonthValue() + "." + depositDate.getYear() + " " + depositDate.getHour() + ":" + depositDate.getMinute();
        return dateInString;
    }

    public AccountBank getAccountBank() {
        return accountBank;
    }

    public String getDepositValueToString(){
        String operator = "";
        if (depositValue < 0){
            operator = "-";
        }else{
            operator = "+";
        }
        return operator + " " + depositValue;
    }

    public double getDepositValueAfterChange() {
        return depositValueAfterChange;
    }

    public void setDepositValueAfterChange(double depositValueAfterChange) {
        this.depositValueAfterChange = depositValueAfterChange;
    }

    public void setAccountBank(AccountBank accountBank) {
        this.accountBank = accountBank;
    }

    public void setDepositId(long depositId) {
        this.depositId = depositId;
    }

    public long getSecondId() {
        return secondId;
    }

    public void setSecondId(long secondId) {
        this.secondId = secondId;
    }
}
