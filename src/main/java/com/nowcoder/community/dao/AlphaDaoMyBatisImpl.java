package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author tlbstart
 * @create 2020-07-12-18:38
 */
@Repository
@Primary      //这个注解让这个实现类在被容器按类名获取时优先被调用
public class AlphaDaoMyBatisImpl implements AlphaDao {

    @Override
    public String select() {
        return "Mybatis";
    }
}
