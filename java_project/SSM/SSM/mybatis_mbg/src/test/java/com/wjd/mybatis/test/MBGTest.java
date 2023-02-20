package com.wjd.mybatis.test;

import com.wjd.mybatis.mapper.EmpMapper;
import com.wjd.mybatis.pojo.Emp;
import com.wjd.mybatis.pojo.EmpExample;
import com.wjd.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Date:2022/6/30
 * Author:ybc
 * Description:
 */
public class MBGTest {

    @Test
    public void testMBG(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        //根据id查询数据
//        Emp emp = mapper.selectByPrimaryKey(1);
//        System.out.println(emp);
        //查询所有数据（无条件查询）
//        List<Emp> list = mapper.selectByExample(null);
//        list.forEach(System.out::println);
        //根据条件查询数据
//        EmpExample example = new EmpExample();
        // 要查询：姓名等于张三 and 年龄大于等于20 or 性别为男
//        example.createCriteria().andEmpNameEqualTo("张三").andAgeGreaterThanOrEqualTo(20);
//        example.or().andGenderEqualTo("男");
//        List<Emp> list2 = mapper.selectByExample(example);
//        list2.forEach(System.out::println);
        Emp emp2 = new Emp(1, "小黑子", null, "女");
        //测试普通修改功能
//        mapper.updateByPrimaryKey(emp2);
        //测试选择性修改
        mapper.updateByPrimaryKeySelective(emp2);
    }

}
