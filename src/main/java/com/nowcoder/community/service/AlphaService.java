package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author tlbstart
 * @create 2020-07-12-18:47
 */
@Service
@Scope//默认为"singleton"单例,可以在后面添加("prototype")表示多实例
public class AlphaService {

    @Autowired //演示service处理时依赖dao，dao属性自动注入
    private AlphaDao alphaDao;
    public String find(){
        return alphaDao.select();
    }


    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    @PostConstruct  //这个注解说明这个方法在构造器之后被调用，用于初始化某些数据
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy  //在销毁对象之前调用，可以释放某些资源
    public void destroy(){
        System.out.println("销毁AlphaService");
    }
}
