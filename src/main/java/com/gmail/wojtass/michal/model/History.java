package com.gmail.wojtass.michal.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class History {

    private List<DepositPayout> depositPayoutList = new ArrayList<>();

    private List<Transaction> transactionList = new ArrayList<>();

    private List<MergedDepositTransaction> allList = new ArrayList<>();

    private double minValue;

    private double maxValue;

    private String minValueString ="0";

    private String maxValueString ="0";

    private ItemGroupToShow itemGroupToShow;

    private SortBy sortBy;

    private SortByAscDsc sortByAscDsc;

    private DataRange dataRange;

    public enum ItemGroupToShow{
        ALL,TRANSACTION,DEPOSIT,PAYOUT
    }

    public enum SortBy{
        DATE,VALUE
    }

    public enum SortByAscDsc{
        DESC,ASC
    }

    public enum DataRange{
        ALL,THISMONTH,PREMONTH,NINETYDAYS,SIXMONTHS,THISYEAR
    }

    public List<DepositPayout> getDepositPayoutList() {
        return depositPayoutList;
    }

    public ItemGroupToShow getItemGroupToShow() {
        return itemGroupToShow;
    }

    public void setDepositPayoutList(List<DepositPayout> depositPayoutList) {
        this.depositPayoutList = depositPayoutList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public double getMinValue() {
        double minVal = BigDecimal.valueOf(Double.parseDouble(getMinValueString().trim())).doubleValue();
        return minVal;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        double maxVal = BigDecimal.valueOf(Double.parseDouble(getMaxValueString().trim())).doubleValue();
        return maxVal;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public void setItemGroupToShow(ItemGroupToShow itemGroupToShow) {
        this.itemGroupToShow = itemGroupToShow;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public SortByAscDsc getSortByAscDsc() {
        return sortByAscDsc;
    }

    public void setSortByAscDsc(SortByAscDsc sortByAscDsc) {
        this.sortByAscDsc = sortByAscDsc;
    }

    public DataRange getDataRange() {
        return dataRange;
    }

    public void setDataRange(DataRange dataRange) {
        this.dataRange = dataRange;
    }

    public List<MergedDepositTransaction> getAllList() {
        return allList;
    }

    public void setAllList(List<MergedDepositTransaction> allList) {
        this.allList = allList;
    }

    public String getMinValueString() {
        return minValueString;
    }

    public void setMinValueString(String minValueString) {
        this.minValueString = minValueString;
    }

    public String getMaxValueString() {
        return maxValueString;
    }

    public void setMaxValueString(String maxValueString) {
        this.maxValueString = maxValueString;
    }
}
