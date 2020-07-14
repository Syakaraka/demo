package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @author tlbstart
 * @create 2020-07-12-18:34
 */
@Repository("alphaHibernate")  //为这个类取个名字
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
