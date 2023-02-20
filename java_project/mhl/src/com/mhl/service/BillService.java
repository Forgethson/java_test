package com.mhl.service;

import com.mhl.dao.BillDAO;
import com.mhl.dao.MultiTableDAO;
import com.mhl.domain.Bill;
import com.mhl.domain.Menu;
import com.mhl.domain.MultiTableBean;

import java.util.List;
import java.util.UUID;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/25
 * 处理和账单相关的业务逻辑
 */
public class BillService {

    private BillDAO billDAO = new BillDAO();
    // 定义一个 MenuService 属性（需要使用其中的方法）
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();
    private MultiTableDAO multiTableDAO = new MultiTableDAO();

    // 完成点餐，并生成一个账单，插入表中
    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        // 生成一个账单号，UUID
        String billID = UUID.randomUUID().toString();  // 返回一个随机的字符串
        // 将账单插入 bill 表
        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')",
                billID, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);
        if (update <= 0) {
            return false;
        }
        // 将餐桌信息修改为就餐中（无论是已预订，还是空的餐桌，都是可以点菜的！）
        return diningTableService.updateDiningTableState(diningTableId, "就餐中");
    }

    // 返回所有的账单，提供给view调用
    public List<Bill> list() {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }

    // 返回所有的带菜名的账单（多表查询）
    public List<MultiTableBean> list2() {
        return multiTableDAO.queryMulti("select bill.*, menu.`name` as menuName, menu.price from bill, " +
                "menu where bill.menuId = menu.id", MultiTableBean.class);
    }

    // 查看某张餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTabled(int diningTableId) {
        // 因为用的single，因此需要用 limit 限制，只返回1条信息
        Bill bill = billDAO.querySingle("select * from bill where diningTableId=? and state ='未结账' limit 0, 1", Bill.class, diningTableId);
        return bill != null;
    }

    // 完成结账[如果餐桌存在，并且有未结账的账单]
    public boolean payBill(int diningTabledId, String payMode) {
        // 更改状态
        int update = billDAO.update("update bill set state=? where diningTableId=? and state='未结账'", payMode, diningTabledId);
        if (update <= 0) {
            return false;
        }
        // 结账后状态设置为空闲
        return diningTableService.updateDiningTableToFree(diningTabledId, "空");
    }

}
