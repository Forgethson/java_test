package com.wjd.book.service;

import com.wjd.book.pojo.OrderBean;
import com.wjd.book.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrderBean(OrderBean orderBean);
    List<OrderBean> getOrderList(User user);
}
