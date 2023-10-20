package com.gmail.wojtass.michal.components;

import com.gmail.wojtass.michal.model.ServerInfo;
import com.gmail.wojtass.michal.model.User;
import com.gmail.wojtass.michal.services.ServerInfoRepository;
import com.gmail.wojtass.michal.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ResetLimitTransactions {

    @Autowired
    UserRepository repo;

    @Autowired
    ServerInfoRepository serverInfoRepo;

    /**
     * Created only for first application use to create first and for now only object of server info which contain LocalDateTime when last time limit was set to 0.
     */
    public void createServerInfoObjectToDB(){
        if (serverInfoRepo.findById(73) == null){
            ServerInfo serverInfo = new ServerInfo(73,LocalDateTime.now(),LocalDateTime.now());
            serverInfoRepo.save(serverInfo);
        }
    }


    /**
     * Method is for secure, if automatic reset not turn on, then if pass 1 day for daily limit between your login to system and last reset it will reset all users limit to 0
     * To improve its should be in another place than in bankController, but should start when application starts
     * Not tested, i don't check it works, but it should
     */
    public void checkLastResetAndResetIfMissed(){
        ServerInfo serverInfo = serverInfoRepo.findById(73);
        LocalDateTime lastTimeResetDayLimitTransactions = serverInfo.getLastResetDayTransactionLimitTime();
        LocalDateTime lastTimeResetMonthLimitTransactions = serverInfo.getLastResetMonthTransactionLimitTime();
        Duration durationDayDifference = Duration.between(lastTimeResetDayLimitTransactions,LocalDateTime.now());
        long dayDifference = durationDayDifference.toDays();
        long monthDifference = ChronoUnit.MONTHS.between(lastTimeResetMonthLimitTransactions,LocalDateTime.now());
        if (dayDifference != 0){
            List<User> users = repo.findAll();
            for (User user : users) {
                user.setTempLimitTransactionForDay(0);
                repo.save(user);
            }
            serverInfo.setLastResetDayTransactionLimitTime(LocalDateTime.now());
            serverInfoRepo.save(serverInfo);
        }
        if (monthDifference != 0){
            List<User> users = repo.findAll();
            for (User user : users) {
                user.setTempLimitTransactionForMonth(0);
                repo.save(user);
            }
            serverInfo.setLastResetMonthTransactionLimitTime(LocalDateTime.now());
            serverInfoRepo.save(serverInfo);
        }
    }

    /**
     * Work, tested, it reset tempLimitTransactionForDay every day at 00:00:00
     */
    @Scheduled(cron = "0 0 0 * * ?")
    private void resetTempLimitTransactionForDay() {
        List<User> users = repo.findAll();
        for (User user : users) {
            user.setTempLimitTransactionForDay(0);
            repo.save(user);
        }
        ServerInfo serverInfo = serverInfoRepo.findById(73);
        serverInfo.setLastResetDayTransactionLimitTime(LocalDateTime.now());
        serverInfoRepo.save(serverInfo);
    }

    /**
     * Should work, its same as resetTempLimitTransactionForDay but for Month, and should change every first day of month
     * Its not tested
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    private void resetTempLimitTransactionForMonth() {
        List<User> users = repo.findAll();
        for (User user : users) {
            user.setTempLimitTransactionForMonth(0);
            repo.save(user);
        }
        ServerInfo serverInfo = serverInfoRepo.findById(73);
        serverInfo.setLastResetMonthTransactionLimitTime(LocalDateTime.now());
        serverInfoRepo.save(serverInfo);
    }
}
