package com.ciel.appoint;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合，junit启动时加载springIoC容器 spring-test,junit
 * @author CIEL
 *
 */
// SpringJUnit4ClassRunner:是指我们要在spring的管理下进行我们的测试工作
@RunWith(SpringJUnit4ClassRunner.class)

//ContextConfigurate:告诉junit我们的spring配置文件
@ContextConfiguration({"classpath*:spring/spring-dao.xml", "classpath*:spring/spring-service.xml"})

public class BaseTest {

}
