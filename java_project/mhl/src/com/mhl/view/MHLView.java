package com.mhl.view;

import com.mhl.domain.*;
import com.mhl.domain.Menu;
import com.mhl.service.BillService;
import com.mhl.service.DiningTableService;
import com.mhl.service.EmployeeService;
import com.mhl.service.MenuService;
import com.mhl.utils.Utility;
import com.sun.org.apache.xpath.internal.operations.Neg;

import java.awt.*;
import java.util.List;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/25
 * 满汉楼的主界面
 */
public class MHLView {

    private boolean loop = true;
    private String key = "";
    private EmployeeService employeeService = new EmployeeService();
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    // 显示主菜单
    public void mainMenu() {

        while (loop) {
            System.out.println("=============满汉楼=============");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.println("登录满汉楼");
                    System.out.print("请输入员工号：");
                    String empId = Utility.readString(50);
                    System.out.print("请输入密码：");
                    String pwd = Utility.readString(50);
                    Employee employee = employeeService.getEmployeeByIdAndPwd(empId, pwd);

                    if (employee != null) {
                        System.out.println("=============登录成功，[" + employee.getName() + "]=============");
                        // 显示二级菜单
                        while (loop) {
                            System.out.println("=============满汉楼（二级菜单）=============");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出满汉楼");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    listDiningTable();
                                    break;
                                case "2":
                                    orderDiningTable();
                                    break;
                                case "3":
                                    listMenu();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    listBill();
                                    break;
                                case "6":
                                    payBill();
                                    break;
                                case "9":
                                    System.out.println("退出满汉楼");
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("你输入有误，请重新输入");
                                    break;
                            }
                        }

                    } else {
                        System.out.println("=============登录失败=============");
                    }

                    break;
                case "2":
                    System.out.println("退出满汉楼");
                    loop = false;
                    break;
                default:
                    System.out.println("你输入有误，请重新输入");
                    break;
            }
        }
    }

    // 显示所有餐桌信息
    public void listDiningTable() {
        System.out.println("=============显示餐桌状态=============");
        System.out.println("餐桌编号\t\t餐桌状态");
        List<DiningTable> list = diningTableService.list();
        if (list != null) {
            for (DiningTable diningTable : list) {
                System.out.println(diningTable);
            }
            System.out.println("=============显示完毕=============");
        } else {
            System.out.println("餐桌列表为空");
        }
    }

    // 订座
    public void orderDiningTable() {
        System.out.println("=============预定餐桌=============");
        System.out.println("请选择要预定的餐桌编号（-1退出）：");
        int orderId = Utility.readInt();
        if (orderId == -1) {
            System.out.println("=============取消预定餐桌=============");
            return;
        }
        char key = Utility.readConfirmSelection();
        if (key == 'Y') { // 确认预定
            // 根据orderId 返回对应DiningTable对象，如果为null，说明对象不存在
            DiningTable diningTableById = diningTableService.getDiningTableById(orderId);
            if (diningTableById == null) {
                System.out.println("=============" + orderId + "号餐桌不存在=============");
                return;
            }
            if (!"空".equals(diningTableById.getState())) {  // 当前餐桌非空
                System.out.println("=============" + orderId + "号餐桌已被预订或已被就餐中=============");
                return;
            }
            System.out.print("预订人的名字：");
            String orderName = Utility.readString(50);
            System.out.print("预订人的电话：");
            String orderTel = Utility.readString(50);
            // 可以预定，更新餐桌状态
            if (diningTableService.orderDiningTable(orderId, orderName, orderTel)) {
                System.out.println("=============已为你预定" + orderId + "号餐桌=============");
            } else {
                System.out.println("=============" + orderId + "号餐桌预定失败=============");
            }

        } else {
            System.out.println("=============取消预定餐桌=============");
        }
    }

    // 显示所有菜品
    public void listMenu() {
        System.out.println("=============显示所有菜品=============");
        System.out.println("\n菜品编号\t\t菜品名\t\t类别\t\t价格");
        List<Menu> list = menuService.list();
        for (Menu menu : list) {
            System.out.println(menu);
        }
        System.out.println("=============显示完毕=============");
    }

    // 点餐服务
    public void orderMenu() {
        System.out.println("=============点餐服务=============");
        System.out.print("请输入点餐的桌号（-1退出）：");
        int orderDiningTableId = Utility.readInt();
        if (orderDiningTableId == -1) {
            System.out.println("=============取消点餐=============");
            return;
        } else if (orderDiningTableId < -1) {
            System.out.println("你输入有误，请重新输入");
        }

        System.out.print("请输入点餐的菜品号（-1退出）：");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {
            System.out.println("=============取消点餐=============");
            return;
        } else if (orderMenuId < -1) {
            System.out.println("你输入有误，请重新输入");
        }

        System.out.print("请输入点餐的菜品量（-1退出）：");
        int orderNums = Utility.readInt();
        if (orderNums == -1) {
            System.out.println("=============取消点餐=============");
            return;
        } else if (orderNums < -1) {
            System.out.println("你输入有误，请重新输入");
        }

        // 验证餐桌号是否存在
        DiningTable diningTable = diningTableService.getDiningTableById(orderDiningTableId);
        if (diningTable == null) {
            System.out.println("=============餐桌号" + orderDiningTableId + "不存在=============");
            return;
        }

        // 验证菜品编号
        Menu menu = menuService.getMenuById(orderMenuId);
        if (menu == null) {
            System.out.println("=============菜品号" + orderMenuId + "不存在=============");
            return;
        }

        // 点餐
        if (billService.orderMenu(orderMenuId, orderNums, orderDiningTableId)) {
            System.out.println("=============点餐成功=============");
        } else {
            System.out.println("=============点餐失败=============");
        }
    }

    // 显示账单信息
    public void listBill() {
        System.out.println("=============查看账单=============");
//        List<Bill> Bills = billService.list();
        List<MultiTableBean> Bills = billService.list2();
        System.out.println("\n编号\t\t菜品号\t菜品名\t\t单价\t\t菜品量\t金额\t\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
        for (MultiTableBean bill : Bills) {
            System.out.println(bill);
        }
        System.out.println("=============显示完毕=============");
    }

    // 完成结账
    public void payBill() {
        System.out.println("=============结账服务=============");
        System.out.print("请选择要结账的餐桌编号（-1退出）：");
        int diningTableId = Utility.readInt();
        if (diningTableId == -1) {
            System.out.println("=============取消结账=============");
            return;
        } else if (diningTableId < -1) {
            System.out.println("你输入有误，请重新输入");
        }

        // 验证餐桌号是否存在
        DiningTable diningTable = diningTableService.getDiningTableById(diningTableId);
        if (diningTable == null) {
            System.out.println("=============餐桌号" + diningTableId + "不存在=============");
            return;
        }

        // 验证餐桌是否有需要结账的账单
        if (!billService.hasPayBillByDiningTabled(diningTableId)) {
            System.out.println("=============该餐桌号" + diningTableId + "不存在未结账的账单=============");
            return;
        }

        System.out.print("结账方式（现金/支付宝/微信）回车表示退出：");
        String payMode = Utility.readString(20, "");
        if ("".equals(payMode)) {
            System.out.println("=============取消结账=============");
            return;
        }

        char key = Utility.readConfirmSelection();
        if (key == 'Y') {
            if (billService.payBill(diningTableId, payMode)) {
                System.out.println("=============完成结账=============");
            } else {
                System.out.println("=============结账失败=============");
            }
        } else {
            System.out.println("=============取消结账=============");
        }
    }
}


