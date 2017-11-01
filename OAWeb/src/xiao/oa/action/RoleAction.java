package xiao.oa.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.ModelDrivenBaseAction;
import xiao.oa.domain.Privilege;
import xiao.oa.domain.Role;


import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends ModelDrivenBaseAction<Role> {

	private Long[] privilegeIds;

	/** �б� */
	public String list() throws Exception {
		// ׼������
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/** ɾ�� */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/** ���ҳ�� */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** ��� */
	public String add() throws Exception {
		// ��װ����
		// Role role = new Role();
		// role.setName(model.getName());
		// role.setDescription(model.getDescription());

		// ���浽���ݿ�
		roleService.save(model);

		return "toList";
	}

	/** �޸�ҳ�� */
	public String editUI() throws Exception {
		// ׼��Ҫ���Ե�����
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}

	/** �޸� */
	public String edit() throws Exception {
		// 1�������ݿ��л�ȡҪ�޸ĵ�ԭʼ����
		Role role = roleService.getById(model.getId());

		// 2, ����Ҫ�޸ĵ�����
		role.setName(model.getName());
		role.setDescription(model.getDescription());

		// 3�����µ����ݿ�
		roleService.update(role);

		return "toList";
	}

	/** ����Ȩ��ҳ�� */
	public String setPrivilegeUI() throws Exception {
		// ׼������
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);

		// ׼��Ҫ���Ե�����
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);

		// ׼�����Ե�Ȩ������
		privilegeIds = new Long[role.getPrivileges().size()];
		int index = 0;
		for (Privilege p : role.getPrivileges()) {
			privilegeIds[index++] = p.getId();
		}

		return "setPrivilegeUI";
	}

	/** ����Ȩ�� */
	public String setPrivilege() throws Exception {
		// 1�������ݿ��л�ȡҪ�޸ĵ�ԭʼ����
		Role role = roleService.getById(model.getId());

		// 2, ����Ҫ�޸ĵ�����
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));

		// 3�����µ����ݿ�
		roleService.update(role);

		return "toList";
	}

	// ---

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
