package xiao.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.ModelDrivenBaseAction;
import xiao.oa.domain.Department;
import xiao.oa.util.DepartmentUtils;


import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DepartmentAction extends ModelDrivenBaseAction<Department> {

	private Long parentId;

	/**
	 * �б� �б�ҳ��ֻ��ʾһ��ģ�ͬ���ģ��������ݣ�Ĭ����ʾ����Ĳ����б�
	 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		if (parentId == null) {
			// Ĭ����ʾ���������б�
			departmentList = departmentService.findTopList();
		} else {
			// ��ʾָ�����ŵ��Ӳ����б�
			departmentList = departmentService.findChildren(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/** �б� */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** �б� */
	public String addUI() throws Exception {
		// ׼������
		// List<Department> departmentList = departmentService.findAll();
		// ActionContext.getContext().put("departmentList", departmentList);

		// ׼�����ݣ���״�ṹ��
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);

		return "saveUI";
	}

	/** �б� */
	public String add() throws Exception {
		// ��װ����
		// Department department = new Department();
		// department.setName(name);
		// department.setDescription(description);

		// �����ϼ�����
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);

		// ���浽���ݿ�
		departmentService.save(model);

		return "toList";
	}

	/** �б� */
	public String editUI() throws Exception {
		// ׼�����Ե�����
		Department department = departmentService.getById(model.getId()); // ��ǰҪ�޸ĵĲ���
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			this.parentId = department.getParent().getId();
		}

		// // ׼������
		// List<Department> departmentList = departmentService.findAll();
		// ActionContext.getContext().put("departmentList", departmentList);

		// ׼�����ݣ���״�ṹ��
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, department);
		ActionContext.getContext().put("departmentList", departmentList);

		return "saveUI";
	}

	/** �б� */
	public String edit() throws Exception {
		// 1�������ݿ���ȡ��Ҫ�޸ĵ�ԭʼ����
		Department department = departmentService.getById(model.getId());

		// 2������Ҫ�޸ĵ�����
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		// >> �����ϼ�����
		Department parent = departmentService.getById(parentId);
		department.setParent(parent);

		// 3�����µ����ݿ�
		departmentService.update(department);

		return "toList";
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
