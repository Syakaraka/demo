package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author tlbstart
 * @create 2020-07-12-19:02
 */
@Configuration  //表示这是个配置类
public class AlphaConfig {

    @Bean  //定义第三方bean在方法前加bean注解. 这个方法返回的对象将装进容器，bean名字为方法名
    public SimpleDateFormat simpleDateFormat(){//方法名simpleDateFormat即为这个bean的名字
        return new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    }
}
