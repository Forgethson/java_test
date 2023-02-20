package com.homework_and_exercise.chapter25.DAO_comprehensive;

import com.homework_and_exercise.chapter25.DAO_comprehensive.dao.ActorDAO;
import com.homework_and_exercise.chapter25.DAO_comprehensive.domain.Actor;

import java.util.List;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/25
 */
public class Test {

    public static void main(String[] args) {
        ActorDAO actorDAO = new ActorDAO();

        // 查询多行多列记录
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id >= ?", Actor.class, 1);
        System.out.println("=====多行多列查询结果=====");
        for (Actor actor : actors) {
            System.out.println(actor);
        }

        // 查询单行多列记录
        Actor actor = actorDAO.querySingle("select * from actor where id = ?", Actor.class, 9);
        System.out.println("=====单行多列查询结果=====");
        System.out.println(actor);

        // 查询单行单列记录
        Object o = actorDAO.queryScalar("select name from actor where id = ?", 5);
        System.out.println("=====单行单列查询结果=====");
        System.out.println(o);

        // dml 操作 insert, update, delete
//        int update = actorDAO.update("insert into actor values(null,?,?,?,?)", "张无忌", "男", "2000-11-11", "999");
//        System.out.println(update > 0 ? "执行成功" : "执行失败");

        int update = actorDAO.update("update actor set borndate=? where name=?", "1335-11-11", "张无忌");
        System.out.println(update > 0 ? "执行成功" : "执行失败");

    }
}
