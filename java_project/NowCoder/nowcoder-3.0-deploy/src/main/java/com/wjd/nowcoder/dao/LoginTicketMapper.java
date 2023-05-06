package com.wjd.nowcoder.dao;

import com.wjd.nowcoder.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
@Deprecated // 优化之后，不推荐使用原始的方式了
public interface LoginTicketMapper {

    // 插入
//    @Insert({
//            "insert into login_ticket(user_id,ticket,status,expired) ",
//            "values(#{userId},#{ticket},#{status},#{expired})"
//    })
//    @Options(useGeneratedKeys = true, keyProperty = "id") // 自增主键
    int insertLoginTicket(LoginTicket loginTicket);

    // 查询
//    @Select({
//            "select id,user_id,ticket,status,expired ",
//            "from login_ticket where ticket=#{ticket}"
//    })
    LoginTicket selectByTicket(String ticket);

    // 更新
//    @Update({
//            "<script>",
//            "update login_ticket set status=#{status} where ticket=#{ticket} ",
//            "<if test=\"ticket!=null\"> ",
//            "and 1=1 ",
//            "</if>",
//            "</script>"
//    })
    int updateStatus(String ticket, int status);

}
