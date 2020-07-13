package com.digital.zone.controllers;

import com.digital.zone.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class VisitController {

    private VisitService visitService;

    @Autowired
    public void setVisitService(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping(value = "/visit", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map userVisitPage(@RequestParam Long id, @RequestParam Long page) {
        visitService.addVisitPage(id, page);
        Map<String, Integer> map = new HashMap<>();
        map.put("allUsersForCurrentDay", visitService.showCountUsersForCurrentDay());
        map.put("uniqueUsersForCurrentDay", visitService.showUniqueUsersForCurrentDay());
        return map;
    }

    @GetMapping(value = "/period", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map showStatisticForPeriod(@RequestParam Date from, @RequestParam Date to) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("countAllUsersForPeriod", visitService.showAllUserForPeriod(from, to));
        map.put("countUniqueUsersForPeriod", visitService.showUniqueUsersForPeriod(from, to));
        map.put("countConstantUsersForPeriod", visitService.showConstantUsersForPeriod(from, to));
        return map;
    }
}
