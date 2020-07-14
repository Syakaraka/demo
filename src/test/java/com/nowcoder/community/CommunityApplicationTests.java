package com.nowcoder.community;

import com.nowcoder.community.CommunityApplication;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)   //测试类以CommunityApplication为配置类
//哪个类想得到Spring容器就实现ApplicationContextAware接口
public class CommunityApplicationTests implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	@Override
	//ApplicationContext就是一个Spring容器，是个接口。Spring容器扫描主键时会检测到这样的bean
	//并调用下面实现的set方法把自身容器传进来
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
	@Test
	public void testApplicationContext() {
		System.out.println(applicationContext);
		AlphaDao alphaDao=applicationContext.getBean(AlphaDao.class);//这里按照类型获取
		System.out.println(alphaDao.select());

		alphaDao=applicationContext.getBean("alphaHibernate",AlphaDao.class);//这里按照名字获取,返回Obj类型需要强转或者加AlphaDao.class参数
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement() {
		AlphaService alphaService=applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
		alphaService=applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat=applicationContext.getBean((SimpleDateFormat.class));
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired  //Sping容器将AlphaDao自动注入给这个属性，通常都这么用。也可以将这些注解加到构造方法前或者set方法前
	@Qualifier("alphaHibernate")  //按名称注入，不使用默认优先
	private AlphaDao alphaDao;
	@Test
	public void testDI(){
		System.out.println(alphaDao);
	}

}
