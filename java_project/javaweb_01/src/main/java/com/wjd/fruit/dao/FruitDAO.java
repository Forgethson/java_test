package com.wjd.fruit.dao;

import com.wjd.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList();

    //获取指定页码上的库存列表信息 , 每页显示10条
    List<Fruit> getFruitList(Integer pageNo);

    List<Fruit> getFruitList(String keyword , Integer pageNo);

    //新增库存
    boolean addFruit(Fruit fruit);

    //修改库存
    void updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //根据fid查询特定水果库存
    Fruit getFruitByFid(Integer fid);

    //删除特定库存记录
    boolean delFruit(Integer fid);

    //查询库存总记录条数
    int getFruitCount(String keyword);
}
