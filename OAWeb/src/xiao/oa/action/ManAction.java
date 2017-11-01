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
 * 个人设置
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
public class ManAction extends BaseAction {
	
	private String password;
	
	/** 显示信息页面 */
	public String list() throws Exception {
		User myList = manService.findMyList(getCurrentUser());
		ActionContext.getContext().put("myList", myList);
		return "list";
	}

	/** 修改页面 */
	public String updateUI() throws Exception {
		return "updateUI";
	}

	/** 修改 */
	public String update() throws Exception {
		// 1，从数据库中取出原对象
		User user = manService.getById(getCurrentUser().getId());
		// 2，设置要修改的属性
		String md5 = DigestUtils.md5Hex(getPassword()); // 密码要使用MD5摘要
		user.setPassword(md5);
		// 3，更新到数据库
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
