package com.wjd.mybatisplus.service.impl;

import com.wjd.mybatisplus.mapper.ProductMapper;
import com.wjd.mybatisplus.pojo.Product;
import com.wjd.mybatisplus.service.ProductService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Date:2022/2/15
 * Author:ybc
 * Description:
 */
@Service
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
