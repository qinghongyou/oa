package xiao.oa.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.ModelDrivenBaseAction;
import xiao.oa.domain.Department;
import xiao.oa.domain.Role;
import xiao.oa.domain.User;
import xiao.oa.util.DepartmentUtils;
import xiao.oa.util.QueryHelper;


import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends ModelDrivenBaseAction<User> {

	private Long departmentId;
	private Long[] roleIds;

	/** �б� */
	public String list() throws Exception {
		// List<User> userList = userService.findAll();
		// ActionContext.getContext().put("userList", userList);

		// ��ҳ����
		new QueryHelper(User.class, "u").preparePageBean(userService, pageNum);

		return "list";
	}

	/** ɾ�� */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** ���ҳ�� */
	public String addUI() throws Exception {
		// ׼�����ݣ�departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);

		// ׼�����ݣ�roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/** ��� */
	public String add() throws Exception {
		// ��װ����
		// >> ���������һ������
		model.setDepartment(departmentService.getById(departmentId));
		// >> ��������Ķ����λ
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));

		// ���浽���ݿ�
		userService.save(model);

		return "toList";
	}

	/** �޸�ҳ�� */
	public String editUI() throws Exception {
		// ׼�����Ե�����
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		// >> ������
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		// >> �����λ
		roleIds = new Long[user.getRoles().size()];
		int index = 0;
		for (Role role : user.getRoles()) {
			roleIds[index++] = role.getId();
		}

		// ׼�����ݣ�departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);

		// ׼�����ݣ�roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/** �޸� */
	public String edit() throws Exception {
		// 1�������ݿ���ȡ��ԭ����
		User user = userService.getById(model.getId());

		// 2������Ҫ�޸ĵ�����
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> ���������һ������
		user.setDepartment(departmentService.getById(departmentId));
		// >> ��������Ķ����λ
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 3�����µ����ݿ�
		userService.update(user);

		return "toList";
	}

	/** ��ʼ������Ϊ1234 */
	public String initPassword() throws Exception {
		// 1�������ݿ���ȡ��ԭ����
		User user = userService.getById(model.getId());

		// 2������Ҫ�޸ĵ�����
		String md5 = DigestUtils.md5Hex("1234"); // ����Ҫʹ��MD5ժҪ
		user.setPassword(md5);

		// 3�����µ����ݿ�
		userService.update(user);

		return "toList";
	}

	// ---

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
