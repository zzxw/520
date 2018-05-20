package cn.bs.test;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTestCase {
	protected ClassPathXmlApplicationContext ctx;
	//初始化Spring容器
	@Before 
	public void init(){
		ctx=new ClassPathXmlApplicationContext(
				"conf/spring-web.xml",
				"conf/spring-mybatis.xml",	
				"conf/spring-service.xml"
				);
	}
}




