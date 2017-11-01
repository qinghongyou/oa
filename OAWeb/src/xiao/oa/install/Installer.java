package xiao.oa.install;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.domain.Privilege;
import xiao.oa.domain.User;


/**
 * ��װ���򣨳�ʼ�����ݣ�
 * 
 * @author Administrator
 * 
 */
@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// =======================================================================
		// 1����������Ա
		User user = new User();
		user.setLoginName("admin");
		user.setName("��������Ա");
		user.setPassword(DigestUtils.md5Hex("admin")); // ����Ҫʹ��MD5ժҪ
		session.save(user); // ����

		// =======================================================================
		// 2��Ȩ������
		Privilege menu, menu1, menu2, menu3, menu4, menu5;

		menu = new Privilege("ϵͳ����", null, null);
		menu1 = new Privilege("��λ����", "/role_list", menu);
		menu2 = new Privilege("���Ź���", "/department_list", menu);
		menu3 = new Privilege("�û�����", "/user_list", menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

		session.save(new Privilege("��λ�б�", "/role_list", menu1));
		session.save(new Privilege("��λɾ��", "/role_delete", menu1));
		session.save(new Privilege("��λ���", "/role_add", menu1));
		session.save(new Privilege("��λ�޸�", "/role_edit", menu1));

		session.save(new Privilege("�����б�", "/department_list", menu2));
		session.save(new Privilege("����ɾ��", "/department_delete", menu2));
		session.save(new Privilege("�������", "/department_add", menu2));
		session.save(new Privilege("�����޸�", "/department_edit", menu2));

		session.save(new Privilege("�û��б�", "/user_list", menu3));
		session.save(new Privilege("�û�ɾ��", "/user_delete", menu3));
		session.save(new Privilege("�û����", "/user_add", menu3));
		session.save(new Privilege("�û��޸�", "/user_edit", menu3));
		session.save(new Privilege("�û���ʼ������", "/user_initPassword", menu3));

		// ------

		menu = new Privilege("���Ͻ���", null, null);
		menu1 = new Privilege("��̳����", "/forumManage_list", menu);
		menu2 = new Privilege("��̳", "/forum_list", menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);

		// ------

		menu = new Privilege("������ת", null, null);
		menu1 = new Privilege("�������̹���", "/processDefinition_list", menu);
		menu2 = new Privilege("����ģ�����", "/template_list", menu);
		menu3 = new Privilege("�������", "/flow_templateList", menu);
		menu4 = new Privilege("��������", "/flow_myTaskList", menu);
		menu5 = new Privilege("�ҵ������ѯ", "/flow_myApplicationList", menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		
		menu = new Privilege("��������", null, null);
		menu1 = new Privilege("������Ϣ", "/man_list", menu);
		menu2 = new Privilege("�޸�����", "/man_update", menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		
		menu = new Privilege("ϵͳ����", null, null);
		menu1 = new Privilege("ϵͳ��Ϣ", "/system_list", menu);
		menu2 = new Privilege("��ϵ����", "/system_link", menu);
		menu3 = new Privilege("��Ȩ����", "/system_copyright", menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

	}

	public static void main(String[] args) {
		System.out.println("���ڳ�ʼ������...");

		// һ��Ҫ��Spring������ȡ������
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		// ִ�а�װ
		installer.install();

		System.out.println("��ʼ��������ϣ�");
	}

}
