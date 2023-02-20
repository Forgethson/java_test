package com.mhl.domain;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/25
 * 一个javabean，和 menu 表对应
 * create table menu (
 * id int primary key auto_increment, #自增主键，作为菜谱编号(唯一)
 * name varchar(50) not null default '',#菜品名称
 * type varchar(50) not null default '', #菜品种类
 * price double not null default 0#价格
 * )charset=utf8;
 */
public class Menu {
    private Integer id;
    private String name;
    private String type;
    private double price;

    public Menu() {
    }

    public Menu(Integer id, String name, String type, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + "\t\t" + name + "\t\t" + type + "\t\t" + price;
    }
}
