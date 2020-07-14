package com.nowcoder.community;

import com.nowcoder.community.CommunityApplication;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
//import org.springframework.context.ApplicationContext;

/**
 * @author tlbstart
 * @create 2020-07-13-8:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= CommunityApplication.class)   //测试类以CommunityApplication为配置类
public class MapperTest implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    //ApplicationContext就是一个Spring容器，是个接口。Spring容器扫描主键时会检测到这样的bean
    //并调用下面实现的set方法把自身容器传进来
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test//查询
    public void testSelectUser(){
        User user =userMapper.selectById(14);
        System.out.println(user);

        user=userMapper.selectByName("nowcoder13");
        System.out.println(user);

        user=userMapper.selectByEmail("nowcoder14@sina.com");
        System.out.println(user);
    }

    @Test  //增加
    public void testInsertUser(){
        User user=new User();  //id不用传，数据库自动生成
        user.setUsername("liubei");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("123456@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/105.png");
        user.setCreateTime(new Date());

        int rows=userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test   //修改
    public void updateUser(){
        int rows=userMapper.updateStatus(16,1);
        System.out.println(rows);

        rows=userMapper.updateHeader(16,"http://www.nowcoder.com/106.png");
        System.out.println(rows);

        rows=userMapper.updatePassword(16,"hello");
        System.out.println(rows);
    }

    @Test
    public void testSelectPosts(){
        List<DiscussPost> list=discussPostMapper.selectDiscussPosts(15,0,10);
        for(DiscussPost post:list){
            System.out.println(post);
        }

        int rows=discussPostMapper.selectDiscussPostRows(16);
        System.out.println(rows);
    }


    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;
    @Test
    public void mytwst(){
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, 0, 10);
        List<Map<String,Object>> discussPosts=new ArrayList<>();
        if(list!=null){
            for(DiscussPost post:list)
            {
//                System.out.println(post.getId());
                Map<String,Object> map=new HashMap<>();
                map.put("post",post);
                User user=userService.findUserById(post.getUserId());
//                System.out.println(user);
                map.put("user",user);
                discussPosts.add(map);
            }
        }
    }



}
