package xiao.oa.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import xiao.oa.domain.Privilege;
import xiao.oa.service.PrivilegeService;


public class OAInitListener implements ServletContextListener {

	private Log log = LogFactory.getLog(OAInitListener.class);

	// ��ʼ��
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();

		// ��Spring��������ȡ��PrivilegeService�Ķ���ʵ��.
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application); // ��ȡSpring�ļ������д�����Spring��������
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");

		// 1����ѯ���ж�����Ȩ���б��ŵ�application��������
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		log.info("====== topPrivilegeList�Ѿ��ŵ�application���������ˣ� ======");
		
		// 2����ѯ�����е�Ȩ�޵�URL���ϲ��ŵ�application��������
		List<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		log.info("====== allPrivilegeUrls�Ѿ��ŵ�application���������ˣ� ======");
	}

	// ����
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
