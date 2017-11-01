package xiao.oa.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import xiao.oa.base.BaseAction;
import xiao.oa.base.ModelDrivenBaseAction;
import xiao.oa.domain.Role;
import xiao.oa.domain.TaskView;
import xiao.oa.domain.User;


/**
 * ��������
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
public class ManAction extends BaseAction {
	
	private String password;
	
	/** ��ʾ��Ϣҳ�� */
	public String list() throws Exception {
		User myList = manService.findMyList(getCurrentUser());
		ActionContext.getContext().put("myList", myList);
		return "list";
	}

	/** �޸�ҳ�� */
	public String updateUI() throws Exception {
		return "updateUI";
	}

	/** �޸� */
	public String update() throws Exception {
		// 1�������ݿ���ȡ��ԭ����
		User user = manService.getById(getCurrentUser().getId());
		// 2������Ҫ�޸ĵ�����
		String md5 = DigestUtils.md5Hex(getPassword()); // ����Ҫʹ��MD5ժҪ
		user.setPassword(md5);
		// 3�����µ����ݿ�
		manService.update(user);
		return "toList";
	}

	//----
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
