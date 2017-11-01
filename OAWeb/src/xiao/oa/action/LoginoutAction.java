package xiao.oa.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.ModelDrivenBaseAction;
import xiao.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class LoginoutAction extends ModelDrivenBaseAction<User> {

	/** ��¼ҳ�� */
	public String loginUI() throws Exception {
		return "loginUI";
	}

	/** ��¼ */
	public String login() throws Exception {
		// ��֤�û��������룬�����ȷ�ͷ�������û������򷵻�null
		User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPassword());

		// �����¼�������벻��ȷ����ת�ص���¼ҳ�沢��ʾ������Ϣ
		if (user == null) {
			addFieldError("login", "��¼�������벻��ȷ��");
			return "loginUI";
		}
		// �����¼�������붼��ȷ���͵�¼�û�
		else {
			ActionContext.getContext().getSession().put("user", user);
			return "toHome";
		}
	}

	/** ע�� */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}

}
