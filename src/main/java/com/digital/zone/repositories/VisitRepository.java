package com.digital.zone.repositories;

import com.digital.zone.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;


@Repository
public interface VisitRepository extends CrudRepository<User, Long> {

    //Добавляет запись в таблицу о посещении страницы пользователем
    @Modifying
    @Transactional
    @Query(value = "insert into users_pages (user_id, page_id, current_day) values (?1, ?2, current_date)", nativeQuery = true)
    void addVisitPage(Long userId, Long pageId);

    //Возвращает количество посещений за текущий день (повторное посещение считается как новое)
    @Query(value = "select count(*) from users_pages up where up.current_day = current_date", nativeQuery = true)
    int showCountUsersForCurrentDay();

    //Возвращает количество пользователей, которые посетили сайт сегодня впервые
    @Query(value = "select count(distinct user_id) from users_pages where user_id in (select distinct user_id from users_pages where current_day = current_date\n" +
            "except select distinct user_id from users_pages where current_day < current_Date)", nativeQuery = true)
    int showUniqueUsersForCurrentDay();

    //Возвращает количество посещений сайта за указанный период (повторное посещение считается как новое)
    @Query(value = "select count(*) from users_pages up where up.current_day >= ?1 and up.current_day <= ?2", nativeQuery = true)
    int showAllUserForPeriod(Date from, Date to);

    //Возвращает количество пользователей, которые посетили сайт только в указанный период
    @Query(value = "select count(distinct user_id) from users_pages where user_id in (select distinct user_id from users_pages where current_day >= ?1 and current_day <= ?2 except select distinct user_id from users_pages where current_day < ?1\n" +
            "except select distinct user_id from users_pages where current_day > ?2)", nativeQuery = true)
    int showUniqueUsersForPeriod(Date from, Date to);

    //Возвращает количество пользователей, которые посетили в указанный период не менее 10 различных страниц
    @Query(value = "select count(table2.user_id) from (select table1.user_id from (select user_id, page_id from users_pages where current_day >= ?1 and current_day <= ?2 group by user_id, page_id) as table1 group by table1.user_id having count(user_id) >= 10) as table2", nativeQuery = true)
    int showConstantUsersForPeriod(Date from, Date to);

}
