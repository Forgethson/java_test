package com.mhl.domain;

import javax.naming.InsufficientResourcesException;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/25
 * 这是一个javabean 和 diningTable 表对应
 * create table diningTable (
 * id int primary key auto_increment, #自增, 表示餐桌编号
 * state varchar(20) not null default '',#餐桌的状态
 * orderName varchar(50) not null default '',#预订人的名字
 * orderTel varchar(20) not null default ''
 * )charset=utf8;
 */
public class DiningTable {
    private Integer id;
    private String name;
    private String state;
    private String orderTel;

    public DiningTable() {
    }

    public DiningTable(Integer id, String name, String state, String orderTel) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.orderTel = orderTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" + state;
    }
}
