package com.houserent.view;

import com.houserent.domain.House;
import com.houserent.service.HouseService;
import com.houserent.utils.Utility;

/**
 * 1. 显示界面
 * 2. 接受用户收入
 * 3. 调用 HouseServic 完成对房屋信息的各种操作
 */
public class HouseView {

    private boolean loop = true;  // 控制显示菜单
    private HouseService houseService = new HouseService(10);  // 设置数组大小为 10

    // 退出系统
    public void exit() {
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
        if (c == 'N') {
            System.out.println("放弃退出");
        }
    }

    // 接受输入，修改对应 id 的 House 对象
    public void modifyHouse() {
        System.out.println("=============修改房屋=============");
        System.out.println("请选择待修改房屋编号（-1退出）：");
        int modifyId = Utility.readInt();
        if (modifyId == -1) {
            System.out.println("=============放弃修改房屋信息=============");
            return;
        }
        int idx = houseService.searchHouseId(modifyId);
        if (idx == -1) {
            System.out.println("未能找到编号为" + modifyId + "的房屋");
        } else {
            System.out.println("=============请输入新的房屋信息=============");
            System.out.println("姓名（原：" + houseService.getHousesList()[idx].getName() + "）");
            String name = Utility.readString(8);
            System.out.println("电话（原：" + houseService.getHousesList()[idx].getPhone() + "）");
            String phone = Utility.readString(12);
            System.out.println("地址（原：" + houseService.getHousesList()[idx].getAddress() + "）");
            String address = Utility.readString(16);
            System.out.println("月租（原：" + houseService.getHousesList()[idx].getRent() + "）");
            int rent = Utility.readInt();
            System.out.println("状态（原：" + houseService.getHousesList()[idx].getState() + "）");
            String state = Utility.readString(3);
            House modifyedhouse = new House(modifyId, name, phone, address, rent, state);
            houseService.modifyHouseInfo(idx, modifyedhouse);
        }
    }

    // 接受输入，查找对应 id 的 House对象
    public void searchHouse() {
        System.out.println("=============查找房屋信息=============");
        System.out.println("请输入你要查找的房屋编号（-1退出）：");
        int searchId = Utility.readInt();
        if (searchId == -1) {
            System.out.println("=============放弃查找房屋信息=============");
            return;
        }
        int idx = houseService.searchHouseId(searchId);
        if (idx == -1) {
            System.out.println("未能找到编号为" + searchId + "的房屋");
        } else {
            System.out.println("已找到编号为" + searchId + "的房屋");
            System.out.println(houseService.getHousesList()[idx]);
        }
    }

    // 接受输入，删除对应 id 的 House对象
    public void delHouse() {
        System.out.println("=============删除房屋信息=============");
        System.out.println("请输入待删除房屋的编号（-1退出）：");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("=============放弃删除房屋信息=============");
            return;
        }
        // 注意该方法本身就具有循环判断的逻辑，必须输入Y/N
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y') {
            if (houseService.delHouseFromList(delId)) {
                System.out.println("=============删除房屋成功=============");
            } else {
                System.out.println("=============房屋编号不存在=============");
            }
        } else {
            System.out.println("=============放弃删除房屋信息=============");
        }
    }

    // 接受输入，创建House对象
    public void addHouse() {
        if (houseService.judgeListFull()) {
            System.out.println("=============房屋列表=============");
            System.out.println("姓名");
            String name = Utility.readString(8);
            System.out.println("电话");
            String phone = Utility.readString(12);
            System.out.println("地址");
            String address = Utility.readString(16);
            System.out.println("月租");
            int rent = Utility.readInt();
            System.out.println("状态：");
            String state = Utility.readString(3);

            // 创新新的House对象，id是系统分配的，用户不能输入
            House newhouse = new House(0, name, phone, address, rent, state);
            houseService.addHouseToList(newhouse);
            System.out.println("=============添加房屋成功=============");
        } else {
            System.out.println("=============添加房屋失败=============");
        }
    }

    // 显示房屋列表
    public void listHouses() {
        System.out.println("=============房屋列表=============");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
        House[] houses = houseService.getHousesList();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null) {
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("=============房屋列表现实完毕=============");
    }

    // 显示主菜单
    public void mainMenu() {
        do {
            System.out.println("==============房屋出租系统菜单=============");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删 除 房 屋 信 息");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t6 退 出 系 统");
            System.out.println("请输入你的选择（1-6）：");
            // 接受用户输入
            char key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    searchHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    modifyHouse();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while (loop);
    }
}
