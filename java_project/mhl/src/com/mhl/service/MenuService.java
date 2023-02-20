package com.mhl.service;

import com.mhl.dao.MenuDAO;
import com.mhl.domain.Menu;

import java.util.List;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/25
 * 完成对menu表的各种操作（通过调用MenuDAO）
 */
public class MenuService {

    private MenuDAO menuDAO = new MenuDAO();

    // 返回所有的菜品
    public List<Menu> list() {
        return menuDAO.queryMulti("select * from menu", Menu.class);
    }

    // 根据id，返回Menu对象
    public Menu getMenuById(int id) {
        return menuDAO.querySingle("select * from menu where id = ?", Menu.class, id);
    }
}
