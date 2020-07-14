package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tlbstart
 * @create 2020-07-13-14:27
 */
@Mapper
public interface DiscussPostMapper {
    //查询帖子，返回列表类型
    //将来查询用户个人主页帖子，需要id。现在可以不传入id表示所有帖子
    //分页，offset起始行号，limit每页显示多少条数据
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    //查询一共有多少数据（帖子行数）
    //@Param 给参数取别名，若方法只一个参数，并且在<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);
}
