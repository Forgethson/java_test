package com.houserent.service;

import com.houserent.domain.House;

/**
 * 业务层
 * 定义 House[] ，保存 House 对象
 * 1. 响应 HouseView 调用
 * 2. 完成对房屋信息的各种操作（增删改查，crud）
 */
public class HouseService {

    private House[] houses;  // 保存House对象
    private int houseNum = 3;  // 记录当前有多少个房屋信息
    private int idCounter = houseNum - 1;  // 记录当前id自增长到哪了
    private int Maxsize;

    // 构造函数
    public HouseService(int Maxsize) {
        houses = new House[Maxsize];
        this.Maxsize = Maxsize;
        // 测试数据
        houses[0] = new House(0, "jack", "112", "海淀区", 2000, "未出租");
        houses[1] = new House(1, "jack1", "134", "海淀区", 1500, "未出租");
        houses[2] = new House(2, "jac2k", "111", "海淀区", 4000, "未出租");
    }

    // 修改对应 id 的房屋信息
    public void modifyHouseInfo(int modifyId, House modifyedhouse) {
        houses[modifyId] = modifyedhouse;
    }

    // 查找对应 id 的房屋，返回对应的数组索引
    public int searchHouseId(int searchId) {
        int index = -1;
        for (int i = 0; i < houseNum; i++) {
            if (searchId == houses[i].getId()) {
                index = i;
            }
        }
        return index;
    }

    // 删除对应 id 的房屋
    public boolean delHouseFromList(int delId) {
        // 先找到对应ID的房屋下标
        int index = -1;
        for (int i = 0; i < houseNum; i++) {
            if (delId == houses[i].getId()) {
                index = i;
            }
        }
        if (index == -1) {  // 没找到
            return false;
        }

        for (int i = index; i < houseNum - 1; i++) {
            houses[i] = houses[i + 1];  // 除最后一个元素 所有元素后移
        }
        houses[--houseNum] = null;  // 最后一个元素（刚刚for循环i没有达到的那个元素）置空
        return true;
    }

    // 判断房屋列表是否已满
    public boolean judgeListFull() {
        if (houseNum >= Maxsize) {
            System.out.println("数组已满。不能再添加了！");
            return false;
        }
        return true;
    }

    // 添加新对象，无返回值
    public void addHouseToList(House newHouse) {
        houses[houseNum++] = newHouse;
        // 设置 id 自增长机制
        newHouse.setId(++idCounter);
    }

    // 添加新对象，返回boolean
//    public boolean addHouseToList(House newHouse) {
//        if (houseNum >= houses.length) {
//            System.out.println("数组已满。不能再添加了！");
//            return false;
//        }
//        houses[houseNum++] = newHouse;
//        // 设置 id 自增长机制
//        newHouse.setId(++idCounter);
//        return true;
//    }

    // 返回 houses
    public House[] getHousesList() {
        return houses;
    }
}
