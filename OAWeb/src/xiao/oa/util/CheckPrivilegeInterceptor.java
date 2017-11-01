package xiao.oa.util;

import xiao.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		// System.out.println("============> ��������ǰ��  <============");
		// String result = invocation.invoke(); // ����
		// System.out.println("============> ����������  <============");
		// return result;

		// ׼������
		// a, ��ǰ��¼���û�
		User user = (User) ActionContext.getContext().getSession().get("user");

		// b, ��ǰ���ʵ�URL
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		if (null == namespace || "".equals(namespace)) {
			namespace = "/";
		}
		if (!namespace.endsWith("/")) {
			namespace += "/";
		}
		String url = namespace + actionName;

		// һ������û�δ��¼����ת����¼ҳ��
		if (user == null) {
			if (url.startsWith("/loginout_login")) { // ���� loginout_loginUI �� loginout_login
				// a, �����ǰ���ʵ��ǵ�¼���ܣ��ͷ���
				return invocation.invoke();
			} else {
				// b, �����ǰ���ʵĲ��ǵ�¼���ܣ���ת����¼ҳ��
				return "loginUI";
			}
		}
		// ��������û��Ѿ���¼�����ж�Ȩ��
		else {
			if (user.hasPrivilegeByUrl(url)) {
				// a, �����Ȩ�޷��ʵ�ǰ��URL�������
				return invocation.invoke();
			} else {
				// b, ���û��Ȩ�޷��ʵ�ǰ��URL����ת����ʾ��Ϣ��ҳ��
				return "noPrivilegeUI";
			}
		}
	}
}
