package com.mhl.service;

import com.mhl.dao.DiningTableDAO;
import com.mhl.domain.DiningTable;
import com.sun.xml.internal.stream.StaxErrorReporter;

import java.util.List;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/25
 */
public class DiningTableService {

    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    // 返回餐桌信息方法
    public List<DiningTable> list() {
        return diningTableDAO.queryMulti("select id, state from diningTable", DiningTable.class);
    }

    // 根据id，查询对应的餐桌 DiningTable 对象
    // 如果返回null，表示该id餐桌不存在
    public DiningTable getDiningTableById(int id) {
        return diningTableDAO.querySingle("select * from diningTable where id = ?", DiningTable.class, id);
    }

    // 如果餐桌可以预定，调用方法对 预订人的名字和电话 进行更新
    // 返回是否更新成功
    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        int update = diningTableDAO.update("update diningTable set state='已预订的', orderName=?, orderTel=? where id=?", orderName, orderTel, id);
        return update > 0;
    }

    // 更新指定id的餐桌状态
    public boolean updateDiningTableState(int id, String state){
        int update = diningTableDAO.update("update diningTable set state=? where id=?", state, id);
        return update > 0;
    }

    // 提供方法，将指定的id的餐桌设置为空闲状态（结账后）
    public boolean updateDiningTableToFree(int id, String state){
        int update = diningTableDAO.update("update diningTable set state=?, orderName='', orderTel='' where id=?", state, id);
        return update > 0;
    }

}
