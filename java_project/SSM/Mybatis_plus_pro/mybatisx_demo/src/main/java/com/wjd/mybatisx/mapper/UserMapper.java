package com.wjd.mybatisx.mapper;

import com.wjd.mybatisx.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Forgethson
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2023-02-18 22:20:29
 * @Entity com.wjd.mybatisx.pojo.User
 */
public interface UserMapper extends BaseMapper<User> {
    
    int insertSelective(User user);

    int deleteByUidAndUserName(@Param("uid") Long uid, @Param("userName") String userName);

    int updateAgeAndSexByUid(@Param("age") Integer age, @Param("sex") Integer sex, @Param("uid") Long uid);

    List<User> selectAgeAndSexByAgeBetween(@Param("beginAge") Integer beginAge, @Param("endAge") Integer endAge);

    List<User> selectAllOrderByAgeDesc();
}




