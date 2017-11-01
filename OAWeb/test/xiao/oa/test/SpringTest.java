package xiao.oa.test;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 测试SessionFactory
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sf.openSession());
	}

	// 测试事务管理
	@Test
	public void testTx() throws Exception {
		TestService service = (TestService) ac.getBean("testService");
		// service.saveTwoUsers();
		service.saveTwoUser();
	}

	// 测试Action对象的管理
	@Test
	public void testAction() throws Exception {
		TestAction testAction = (TestAction) ac.getBean("testAction");
		System.out.println(testAction);
	}

	// 测试ProcessEngine
	@Test
	public void testProcessEngine() throws Exception {
		ProcessEngine processEngine = (ProcessEngine) ac.getBean("processEngine");
		System.out.println(processEngine);
	}
}
