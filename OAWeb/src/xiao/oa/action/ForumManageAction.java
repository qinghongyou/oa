package xiao.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.ModelDrivenBaseAction;
import xiao.oa.domain.Forum;


import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumManageAction extends ModelDrivenBaseAction<Forum> {

	/** �б� */
	public String list() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/** ɾ�� */
	public String delete() throws Exception {
		forumService.delete(model.getId());
		return "toList";
	}

	/** ���ҳ�� */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** ��� */
	public String add() throws Exception {
		forumService.save(model);
		return "toList";
	}

	/** �޸�ҳ�� */
	public String editUI() throws Exception {
		// ׼�����Ե�����
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}

	/** �޸� */
	public String edit() throws Exception {
		// 1�������ݿ��л�ȡԭ����
		Forum forum = forumService.getById(model.getId());
		
		// 2������Ҫ�޸ĵ�����
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());

		// 3�����µ����ݿ�
		forumService.update(forum);
		
		return "toList";
	}

	/** ���� */
	public String moveUp() throws Exception {
		forumService.moveUp(model.getId());
		return "toList";
	}

	/** ���� */
	public String moveDown() throws Exception {
		forumService.moveDown(model.getId());
		return "toList";
	}
}
