package com.gmail.wojtass.michal.controllers;

import com.gmail.wojtass.michal.model.*;
import com.gmail.wojtass.michal.services.AccountBankRepository;
import com.gmail.wojtass.michal.services.DepositPayoutRepository;
import com.gmail.wojtass.michal.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@SessionAttributes("loggedUser")
public class HistoryController {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    DepositPayoutRepository depositPayoutRepository;

    @Autowired
    UserRepository repo;

    @Autowired
    AccountBankRepository accountBankRepository;

    private User getUser2(){
        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        String username2 = auth2.getName();
        User user2 = repo.findByUsername(username2);
        return user2;
    }

    @RequestMapping("/history")
    public String history(Model model, @SessionAttribute("loggedUser") User user){
        model.addAttribute("loggedUser",user);
        return "history";
    }

    @GetMapping("/history")
    public String getHistory(Model model,@ModelAttribute("history") @Validated History history){

        User user = getUser2();
        if (history.getItemGroupToShow() == History.ItemGroupToShow.ALL || history.getItemGroupToShow() == null){
            sortByAllSubmittedData(user,history);
            List<MergedDepositTransaction> sortedAll = history.getAllList();
            model.addAttribute("sortedAllList",sortedAll);
        }else if (history.getItemGroupToShow() == History.ItemGroupToShow.TRANSACTION){
            sortByAllSubmittedData(user,history);
            List<Transaction> sortedTransactionList = history.getTransactionList();
            model.addAttribute("sortedTransactionList",sortedTransactionList);
        }else if(history.getItemGroupToShow() == History.ItemGroupToShow.DEPOSIT || history.getItemGroupToShow() == History.ItemGroupToShow.PAYOUT) {
            sortByAllSubmittedData(user,history);
            List<DepositPayout> sortedDepositList = history.getDepositPayoutList();
            model.addAttribute("sortedDepositList",sortedDepositList);
        }

        return "history";
    }

    @PostMapping("/history")
    public String postHistory(@ModelAttribute("history") History history, RedirectAttributes redirectAttributes, BindingResult bindingResult){
        if (history.getMinValueString() == null || history.getMinValueString().trim().equals("")){
            bindingResult.rejectValue("minValueString","error_code","Can't be null or empty/blank.");
            return "history";
        }
        boolean onlyDigitsMin = true;
        String toCheckMin = history.getMinValueString();
        int point = 1;
        boolean wasPoint = false;
        for (int i = 0; i < toCheckMin.length(); i++) {
            if (i==0 && toCheckMin.charAt(i) == 45){
                i++;
                point++;
            }
            if(i >= point && toCheckMin.charAt(i) == 46 && !wasPoint && i < toCheckMin.length()-1) {
                wasPoint = true;
                i++;
            }
            if (!(toCheckMin.charAt(i) >= 48 && toCheckMin.charAt(i) <= 57)) {
                onlyDigitsMin = false;
                break;
            }
        }

        if (!onlyDigitsMin){
            bindingResult.rejectValue("minValueString","error_code","Must contain only digits.");
            return "history";
        }
        if (history.getMaxValueString() == null || history.getMaxValueString().trim().equals("")){
            bindingResult.rejectValue("maxValueString","error_code","Can't be null or empty/blank.");
            return "history";
        }
        boolean onlyDigitsMax = true;
        String toCheckMax = history.getMaxValueString();
        int pointMax = 1;
        boolean wasPointMax = false;
        for (int i = 0; i < toCheckMax.length(); i++) {
            if (i==0 && toCheckMax.charAt(i) == 45){
                i++;
                pointMax++;
            }
            if(i >= pointMax && toCheckMax.charAt(i) == 46 && !wasPointMax && i < toCheckMax.length()-1) {
                wasPointMax = true;
                i++;
            }
            if (!(toCheckMax.charAt(i) >= 48 && toCheckMax.charAt(i) <= 57)){
                onlyDigitsMax = false;
                break;
            }
        }
        if (!onlyDigitsMax){
            bindingResult.rejectValue("maxValueString","error_code","Must contain only digits.");
            return "history";
        }
        redirectAttributes.addFlashAttribute("history", history);
        return "redirect:/history";
    }

    @Transactional
    private List<DepositPayout> queryNullDefaultDeposit(User user){
        long userId = user.getUserId();

        String sqlQuery = "SELECT ab.*  " +
                "FROM deposit_payout ab " +
                "WHERE ab.user_user_id = :userId " +
                "AND " +
                "ab.deposit_date >= NOW() - INTERVAL '90 days' " +
                "ORDER BY ab.deposit_date DESC";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
        List<DepositPayout> results = namedParameterJdbcTemplate.query(sqlQuery, params, new BeanPropertyRowMapper<>(DepositPayout.class));

        for (DepositPayout dp :results) {
            AccountBank accountBank = depositPayoutRepository.findBySecondId(dp.getSecondId());
            dp.setAccountBank(accountBank);
        }

        return results;
    }

    @Transactional
    private List<Transaction> queryNullDefaultTransaction(User user){
        long userId = user.getUserId();

        String sqlQuery = "SELECT ta.*  " +
                "FROM transaction ta " +
                "JOIN transaction_users tt ON ta.transaction_id = tt.transactions_transaction_id " +
                "WHERE tt.users_user_id = :userId " +
                "AND " +
                "ta.date_transaction >= NOW() - INTERVAL '90 days' " +
                "ORDER BY ta.date_transaction DESC";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
        List<Transaction> results = namedParameterJdbcTemplate.query(sqlQuery, params, new BeanPropertyRowMapper<>(Transaction.class));

        for (Transaction ta :results) {
            AccountBank accountBankGiver = accountBankRepository.findAccountBankByAccountNumber(ta.getGiverAccountNumber());
            AccountBank accountBankRecipient = accountBankRepository.findAccountBankByAccountNumber(ta.getRecipientAccountNumber());
            if (accountBankGiver.getUser().getUserId() == userId){
                ta.getAccountBanks().add(accountBankGiver);
                ta.setAmountFoundAfterTransaction(ta.getAmountGiverAfterTransaction());
                ta.setAmountTransactionToString("-");
            }else {
                ta.getAccountBanks().add(accountBankRecipient);
                ta.setAmountFoundAfterTransaction(ta.getAmountRecipientAfterTransaction());
                ta.setAmountTransactionToString("+");
            }
        }

        return results;
    }

    @Transactional
    private List<DepositPayout> queryDeposit(User user,History history){
        long userId = user.getUserId();

        String sortByInside = "";
        if (history.getSortBy().toString().equals("DATE")){
            sortByInside = " WHEN :sortBy = 'DATE' THEN ab.deposit_date  ";
        }else if (history.getSortBy().toString().equals("VALUE")){
            sortByInside = " WHEN :sortBy = 'VALUE' THEN ab.deposit_value ";
        }

        String isDepositType = ""; //When doing sort for ALL we don't have it
        if (history.getItemGroupToShow() != History.ItemGroupToShow.ALL){
            isDepositType = "AND ab.deposit_type = :depositType ";
        }



        String sqlQuery = "SELECT ab.*  " +
                "FROM deposit_payout ab " +
                "WHERE ab.user_user_id = :userId " +
                isDepositType +
                "AND " +
                "CASE WHEN :dataRange = 'ALL' THEN TRUE " +
                "     WHEN :dataRange = 'THISMONTH' THEN ab.deposit_date >= NOW() - INTERVAL '1 month' " +
                "     WHEN :dataRange = 'PREMONTH' THEN DATE_TRUNC('month', ab.deposit_date) = DATE_TRUNC('month', current_date - INTERVAL '1 month') " +
                "     WHEN :dataRange = 'NINETYDAYS' THEN ab.deposit_date >= NOW() - INTERVAL '90 days' " +
                "     WHEN :dataRange = 'SIXMONTHS' THEN ab.deposit_date >= NOW() - INTERVAL '6 months' " +
                "     WHEN :dataRange = 'THISYEAR' THEN ab.deposit_date >= NOW() - INTERVAL '1 year' " +
                "     ELSE FALSE " +
                "END " +
                "AND " +
                "CASE WHEN :minValue = 0 AND :maxValue = 0 THEN TRUE " +
                "     WHEN :minValue > 0 AND :maxValue = 0 THEN ab.deposit_value > :minValue " +
                "     ELSE ab.deposit_value > :minValue AND ab.deposit_value < :maxValue " +
                "END " +
                "ORDER BY " +
                "CASE " +
                sortByInside +
                "END " +
                (history.getSortByAscDsc().toString().equals("ASC") ? "ASC" : "DESC");


        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("depositType", history.getItemGroupToShow().toString())
                .addValue("sortBy", history.getSortBy().toString())
                .addValue("dataRange", history.getDataRange().toString())
                .addValue("minValue", history.getMinValue())
                .addValue("maxValue", history.getMaxValue());

        List<DepositPayout> results = namedParameterJdbcTemplate.query(sqlQuery, params, new BeanPropertyRowMapper<>(DepositPayout.class));

        for (DepositPayout dp :results) {
            //I fetch data here by id using jpa, id in results is always 0 dont know why, so using getSecondId
            AccountBank accountBank = depositPayoutRepository.findBySecondId(dp.getSecondId());
            dp.setAccountBank(accountBank);
        }
        return results;
    }

    @Transactional
    private List<Transaction> queryTransaction(User user, History history){
        long userId = user.getUserId();

        String sqlQuery = "SELECT ta.*  " +
                "FROM transaction ta " +
                "JOIN transaction_users tt ON ta.transaction_id = tt.transactions_transaction_id " +
                "WHERE tt.users_user_id = :userId " +
                "AND " +
                "CASE WHEN :dataRange = 'ALL' THEN TRUE " +
                "     WHEN :dataRange = 'THISMONTH' THEN ta.date_transaction >= NOW() - INTERVAL '1 month' " +
                "     WHEN :dataRange = 'PREMONTH' THEN DATE_TRUNC('month', ta.date_transaction) = DATE_TRUNC('month', current_date - INTERVAL '1 month') " +
                "     WHEN :dataRange = 'NINETYDAYS' THEN ta.date_transaction >= NOW() - INTERVAL '90 days' " +
                "     WHEN :dataRange = 'SIXMONTHS' THEN ta.date_transaction >= NOW() - INTERVAL '6 months' " +
                "     WHEN :dataRange = 'THISYEAR' THEN ta.date_transaction >= NOW() - INTERVAL '1 year' " +
                "     ELSE FALSE " +
                "END " +
                "AND " +
                "CASE WHEN :minValue = 0 THEN TRUE " +
                "     WHEN :minValue != 0 THEN ta.amount_transaction > :minValue " +
                "     ELSE FALSE " +
                "END " +
                "ORDER BY " +
                "ta.date_transaction " +
                (history.getSortByAscDsc().toString().equals("ASC") ? "ASC" : "DESC");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("dataRange", history.getDataRange().toString())
                .addValue("minValue", history.getMinValue());

        List<Transaction> results = namedParameterJdbcTemplate.query(sqlQuery, params, new BeanPropertyRowMapper<>(Transaction.class));

        for (int i = 0; i < results.size(); i++) {
            AccountBank accountBankGiver = accountBankRepository.findAccountBankByAccountNumber(results.get(i).getGiverAccountNumber());
            AccountBank accountBankRecipient = accountBankRepository.findAccountBankByAccountNumber(results.get(i).getRecipientAccountNumber());
            if (accountBankGiver.getUser().getUserId() == userId){
                if (history.getMinValue() != 0 && history.getMinValue() > -results.get(i).getAmountTransaction()){
                    results.remove(results.get(i));
                    i--;
                }else {
                    if (history.getMaxValue() != 0 && -results.get(i).getAmountTransaction() > history.getMaxValue()){
                        results.remove(results.get(i));
                        i--;
                    }else {
                        results.get(i).getAccountBanks().add(accountBankGiver);
                        results.get(i).setAmountFoundAfterTransaction(results.get(i).getAmountGiverAfterTransaction());
                        results.get(i).setAccountNameForSortHistory(accountBankGiver.getAccountName());
                        results.get(i).setAmountTransactionToString("-");
                    }
                }
            }else {
                if (history.getMaxValue() != 0 && history.getMaxValue() < results.get(i).getAmountTransaction()){
                    results.remove(results.get(i));
                    i--;
                }else {
                    results.get(i).getAccountBanks().add(accountBankRecipient);
                    results.get(i).setAmountFoundAfterTransaction(results.get(i).getAmountRecipientAfterTransaction());
                    results.get(i).setAccountNameForSortHistory(accountBankRecipient.getAccountName());
                    results.get(i).setAmountTransactionToString("+");
                }
            }
        }

        return results;
    }

    private void sortByAllSubmittedData(User user,History history) {
        if(history.getItemGroupToShow() == null){
            history.getDepositPayoutList().clear();
            history.getTransactionList().clear();
            history.getAllList().clear();
            history.getDepositPayoutList().addAll(queryNullDefaultDeposit(user));
            history.getTransactionList().addAll(queryNullDefaultTransaction(user));
            long index = 0;
            for (DepositPayout dp :history.getDepositPayoutList()) {
                history.getAllList().add(new MergedDepositTransaction(index, MergedDepositTransaction.ObjectType.DEPOSIT,dp.getDepositDate()
                        ,dp.getDepositValue(),dp.getDepositValueAfterChange(),dp.getDepositType().toString(),dp.getAccountBank().getAccountName(),dp.getAccountBank().getAccountType().toString()));
                index++;
            }
            for (Transaction t :history.getTransactionList()) {
                Set<AccountBank> accountBanks = t.getAccountBanks();
                String accountName = "";
                String accountType = "";
                for (AccountBank ac :accountBanks) {
                    accountName = ac.getAccountName();
                    accountType = ac.getAccountType().toString();
                }
                history.getAllList().add(new MergedDepositTransaction(index, MergedDepositTransaction.ObjectType.TRANSACTION,t.getDateTransaction()
                        ,t.getAmountTransactionToString2(),t.getAmountFoundAfterTransaction(),accountName,accountType));
                index++;
            }
            history.getAllList().sort(new Comparator<MergedDepositTransaction>() {
                @Override
                public int compare(MergedDepositTransaction o1, MergedDepositTransaction o2) {
                    return -o1.getDate().compareTo(o2.getDate());
                }
            });
        }else if (history.getItemGroupToShow() == History.ItemGroupToShow.ALL) {
            history.getDepositPayoutList().clear();
            history.getTransactionList().clear();
            history.getAllList().clear();
            history.getDepositPayoutList().addAll(queryDeposit(user,history));
            history.getTransactionList().addAll(queryTransaction(user,history));
            long index = 0;
            for (DepositPayout dp :history.getDepositPayoutList()) {
                history.getAllList().add(new MergedDepositTransaction(index, MergedDepositTransaction.ObjectType.DEPOSIT,dp.getDepositDate()
                        ,dp.getDepositValue(),dp.getDepositValueAfterChange(),dp.getDepositType().toString(),dp.getAccountBank().getAccountName(),dp.getAccountBank().getAccountType().toString()));
                index++;
            }
            for (Transaction t :history.getTransactionList()) {
                Set<AccountBank> accountBanks = t.getAccountBanks();
                String accountName = "";
                String accountType = "";
                for (AccountBank ac :accountBanks) {
                    accountName = ac.getAccountName();
                    accountType = ac.getAccountType().toString();
                }
                history.getAllList().add(new MergedDepositTransaction(index, MergedDepositTransaction.ObjectType.TRANSACTION,t.getDateTransaction()
                        ,t.getAmountTransactionToString2(),t.getAmountFoundAfterTransaction(),accountName,accountType));
                index++;
            }
            if (history.getSortBy() == History.SortBy.DATE){
                if(history.getSortByAscDsc() == History.SortByAscDsc.DESC) {
                    history.getAllList().sort(new Comparator<MergedDepositTransaction>() {
                        @Override
                        public int compare(MergedDepositTransaction o1, MergedDepositTransaction o2) {
                            return -o1.getDate().compareTo(o2.getDate());
                        }
                    });
                }else if (history.getSortByAscDsc() == History.SortByAscDsc.ASC){
                    history.getAllList().sort(new Comparator<MergedDepositTransaction>() {
                        @Override
                        public int compare(MergedDepositTransaction o1, MergedDepositTransaction o2) {
                            return o1.getDate().compareTo(o2.getDate());
                        }
                    });
                }
            }else if (history.getSortBy() == History.SortBy.VALUE){
                if (history.getSortByAscDsc() == History.SortByAscDsc.DESC){
                    history.getAllList().sort(new Comparator<MergedDepositTransaction>() {
                        @Override
                        public int compare(MergedDepositTransaction o1, MergedDepositTransaction o2) {
                            return -Double.compare(o1.getValueChange(),o2.getValueChange());
                        }
                    });
                }else if (history.getSortByAscDsc() == History.SortByAscDsc.ASC){
                    history.getAllList().sort(new Comparator<MergedDepositTransaction>() {
                        @Override
                        public int compare(MergedDepositTransaction o1, MergedDepositTransaction o2) {
                            return Double.compare(o1.getValueChange(),o2.getValueChange());
                        }
                    });
                }
            }
        }else if(history.getItemGroupToShow() == History.ItemGroupToShow.TRANSACTION){
            history.getTransactionList().clear();
            history.getTransactionList().addAll(queryTransaction(user,history));
            if (history.getSortBy() == History.SortBy.VALUE){
                if (history.getSortByAscDsc() == History.SortByAscDsc.DESC){
                    System.out.println(history.getTransactionList().get(0).getAmountTransactionToString2());
                    history.getTransactionList().sort(new Comparator<Transaction>() {
                        @Override
                        public int compare(Transaction o1, Transaction o2) {
                            return -Double.compare(o1.getAmountTransactionToString2(),o2.getAmountTransactionToString2());
                        }
                    });
                    System.out.println(history.getTransactionList().get(0).getAmountTransactionToString2());
                }else if(history.getSortByAscDsc() == History.SortByAscDsc.ASC){
                    System.out.println(history.getTransactionList().get(0).getAmountTransactionToString2());
                    history.getTransactionList().sort(new Comparator<Transaction>() {
                        @Override
                        public int compare(Transaction o1, Transaction o2) {
                            return Double.compare(o1.getAmountTransactionToString2(),o2.getAmountTransactionToString2());
                        }
                    });
                    System.out.println(history.getTransactionList().get(0).getAmountTransactionToString2());
                }
            }

        }else if (history.getItemGroupToShow() == History.ItemGroupToShow.DEPOSIT || history.getItemGroupToShow() == History.ItemGroupToShow.PAYOUT){
            history.getDepositPayoutList().clear();
            history.getDepositPayoutList().addAll(queryDeposit(user,history));
        }
    }
}
