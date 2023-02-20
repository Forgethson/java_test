package com.wjd.mybatis.mapper;

import com.wjd.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Date:2022/6/28
 * Author:ybc
 * Description:
 * MyBatis获取参数值的两种方式：#{}和${}
 * #{}的本质是占位符赋值，${}的本质是字符串拼接
 * 1、若mapper接口方法的参数为单个的字面量类型
 * 此时可以通过#{}和${}以任意的内容获取参数值（如#{abc}），一定要注意${}的单引号问题
 * 2、若mapper接口方法的参数为多个的字面量类型（多个参数mybatis会自动将其放到一个map里面再传入）
 * 此时MyBatis会将参数放在map集合中，以两种方式存储数据
 * a>以arg0,arg1...为键，以参数为值
 * b>以param1,param2...为键，以参数为值（混合使用也可以）
 * 因此，只需要通过#{}和${}访问map集合的键，就可以获取相对应的值,一定要注意${}的单引号问题
 * 3、若mapper接口方法的参数为map集合类型的参数
 * 只需要通过#{}和${}访问map集合的键，就可以获取相对应的值,一定要注意${}的单引号问题
 * 4、若mapper接口方法的参数为实体类类型的参数（如User类）
 * mybatis底层对于pojo属性的访问本质上是通过get、set方法实现的，与是不是真的定义了某个属性无关
 * 只需要通过#{}和${}访问实体类中的属性名，就可以获取相对应的属性值，一定要注意${}的单引号问题
 * 5、可以在mapper接口方法的参数上设置@Param注解（手动放入map方法的简化）
 * 此时MyBatis会将这些参数放在map中，以两种方式进行存储
 * a>以@Param注解的value属性值为键，以参数为值（将arg0，arg1...替换掉了）
 * b>以param1,param2...为键，以参数为值
 * 只需要通过#{}和${}访问map集合的键，就可以获取相对应的值,一定要注意${}的单引号问题
 * 一般都是使用#{}-占位符的形式，只有少数只能使用${}的情况才会使用字符串拼接
 * 两种方式的区别很小，一般只需要注意单引号问题
 * 其实一般只用访问实体类以及@param这两种方法
 */

public interface UserMapper {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 验证登录
     * @param username
     * @param password
     * @return
     */
    User checkLogin(String username, String password);

    /**
     * 验证登录（以map集合作为参数）
     * @param map
     * @return
     */
    User checkLoginByMap(Map<String, Object> map);

    /**
     * 添加用户信息
     * @param user
     */
    void insertUser(User user);

    /**
     * 验证登录（使用@Param）
     * @param username
     * @param password
     * @return
     */
    User checkLoginByParam(@Param("username") String username, @Param("password") String password);
}
