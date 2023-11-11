package com.gmail.wojtass.michal.model;

import java.time.LocalDateTime;

public class MergedDepositTransaction {

    //Maybe i should do abstract or parent class for Transaction and DepositPayout instead of this

    private long id;

    private ObjectType objectType;

    private LocalDateTime date;

    private double valueChange;

    private double valueAfterChange;

    private String depositType;

    private String accountName;

    private String accountType;

    public enum ObjectType{
        DEPOSIT,TRANSACTION
    }

    public MergedDepositTransaction() {
    }

    public MergedDepositTransaction(long id, ObjectType objectType, LocalDateTime date, double valueChange, double valueAfterChange, String accountName, String accountType) {
        this.id = id;
        this.objectType = objectType;
        this.date = date;
        this.valueChange = valueChange;
        this.valueAfterChange = valueAfterChange;
        this.accountName = accountName;
        this.accountType = accountType;
    }

    public MergedDepositTransaction(long id, ObjectType objectType, LocalDateTime date, double valueChange, double valueAfterChange, String depositType, String accountName, String accountType) {
        this.id = id;
        this.objectType = objectType;
        this.date = date;
        this.valueChange = valueChange;
        this.valueAfterChange = valueAfterChange;
        this.depositType = depositType;
        this.accountName = accountName;
        this.accountType = accountType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getValueChange() {
        return valueChange;
    }

    public void setValueChange(double valueChange) {
        this.valueChange = valueChange;
    }

    public double getValueAfterChange() {
        return valueAfterChange;
    }

    public void setValueAfterChange(double valueAfterChange) {
        this.valueAfterChange = valueAfterChange;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDateToString(){
        String dateInString = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear() + " " + date.getHour() + ":" + date.getMinute();
        return dateInString;
    }

    public String getValueChangeToString(){
        if (valueChange > 0){
            return "+ " + valueChange;
        }else {
            return String.valueOf(valueChange);
        }
    }
}
