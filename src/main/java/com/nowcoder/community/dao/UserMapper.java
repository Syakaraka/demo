package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tlbstart
 * @create 2020-07-13-8:20
 */

//mybatis里操作数据库的一般叫***mapper
@Mapper  //这里也可以是@Repository
public interface UserMapper {

    //要想实现，需要提供配置文件（在mapper目录下），里面为每个方法提供所需sql，这样mybatis底层自动生成类
    User selectById(int id);
    User selectByName(String username);
    User selectByEmail(String email);
    int insertUser(User user);
    int updateStatus(int id,int status);
    int updateHeader(int id,String headerUrl);
    int updatePassword(int id,String password);
}
