package com.hmdp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    private IShopTypeService typeService;

    // 练习：缓存店铺类型信息
    public Result queryTypeList() {
        // 1.从Redis查询店铺类型缓存
        String key = "cache:shopType";
        Long size = stringRedisTemplate.opsForList().size(key);

        // 2.判断是否存在
        if (size != null && size != 0) {
            // 3.存在则直接返回
            ArrayList<ShopType> typeList = new ArrayList<>();

//            List<String> range = stringRedisTemplate.opsForList().range(key, 0, size);
            for (int i = 0; i < size; i++) {
                typeList.add(JSONUtil.toBean(stringRedisTemplate.opsForList().index(key, i), ShopType.class));
            }
            return Result.ok(typeList);
        }

        // 4.不存在，查找数据库
        List<ShopType> typeList = typeService.query().orderByAsc("sort").list();
        // 4.1.不存在，返回错误
        if (typeList == null) {
            return Result.fail("店铺类型为空！");
        }
        // 4.2.存在，存入redis
        for (ShopType shopType : typeList) {
            stringRedisTemplate.opsForList().rightPush(key, JSONUtil.toJsonStr(shopType));
        }
        return Result.ok(typeList);

    }
}
