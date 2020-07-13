package com.digital.zone.services;

import com.digital.zone.entities.User;
import com.digital.zone.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class VisitService {

    private VisitRepository visitRepository;

    @Autowired
    public void setVisitRepository(VisitRepository visitRepository){
        this.visitRepository = visitRepository;
    }

    public void addVisitPage(Long userId, Long pageId){
        visitRepository.addVisitPage(userId, pageId);
        System.out.println("visit page " + visitRepository.getClass().getName());
    }

    public int showCountUsersForCurrentDay(){
        return visitRepository.showCountUsersForCurrentDay();
    }

    public int showUniqueUsersForCurrentDay(){
        return visitRepository.showUniqueUsersForCurrentDay();
    }

    public int showAllUserForPeriod(Date from, Date to){
        return visitRepository.showAllUserForPeriod(from, to);
    }

    public int showUniqueUsersForPeriod(Date from, Date to){
        return visitRepository.showUniqueUsersForPeriod(from, to);
    }

    public int showConstantUsersForPeriod(Date from, Date to){
        return visitRepository.showConstantUsersForPeriod(from, to);
    }

}
