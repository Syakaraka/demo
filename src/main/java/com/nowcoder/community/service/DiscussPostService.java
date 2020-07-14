package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tlbstart
 * @create 2020-07-13-15:19
 */
@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;
    public List<DiscussPost> findDiscussPosts(int userId,int offset,int limit){
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }
    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }

    //一个问题，查询到的DiscussPost有userId，需要转换为username显示
    //解决方法一是写查询sql是一起关联查询，
    //解决方法二是得到数据后，再单独针对每个DiscussPost查询user，再组合返回给页面，
    //         这样将来使用redis缓存数据方便。这就需要再提供一个方法根据userId查询user

}
