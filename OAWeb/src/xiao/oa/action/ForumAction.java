package xiao.oa.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.ModelDrivenBaseAction;
import xiao.oa.domain.Forum;
import xiao.oa.domain.PageBean;
import xiao.oa.domain.Topic;
import xiao.oa.util.QueryHelper;


import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumAction extends ModelDrivenBaseAction<Forum> {

	/**
	 * 0 ��ʾȫ������<br>
	 * 1 ��ʾȫ��������
	 */
	private int viewType;

	/**
	 * 0 ��ʾĬ������(�����ö�����ǰ�棬����������ʱ�併������)<br>
	 * 1 ��ʾֻ��������ʱ������<br>
	 * 2 ��ʾֻ�����ⷢ��ʱ������<br>
	 * 3 ��ʾֻ���ظ���������
	 */
	private int orderBy;

	/**
	 * true��ʾ����<br>
	 * false��ʾ����
	 */
	private boolean asc;

	/** ����б� */
	public String list() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/** ��ʾ������� */
	public String show() throws Exception {
		// ׼�����ݣ�Forum
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);

		// // ׼�����ݣ�topicList
		// List<Topic> topicList = topicService.findByForum(forum);
		// ActionContext.getContext().put("topicList", topicList);

		// // ׼����ҳ������ v1
		// PageBean pageBean = topicService.getPageBeanByForum(pageNum, forum);
		// ActionContext.getContext().getValueStack().push(pageBean); // �ŵ�ջ��

		// // ׼����ҳ������ v2 ʹ�ù����ķ�ҳ����
		// String hql = "FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC";
		// Object[] args = { forum };
		// PageBean pageBean = topicService.getPageBean(pageNum, hql, args);
		// ActionContext.getContext().getValueStack().push(pageBean); // �ŵ�ջ��

		// // ׼����ҳ������ v3 ����������������������
		// List<Object> argsList = new ArrayList<Object>();
		// String hql = "FROM Topic t WHERE t.forum=? ";
		// argsList.add(forum);
		//
		// if (viewType == 1) { // 1 ��ʾֻ��������
		// hql += "AND t.type=? ";
		// argsList.add(Topic.TYPE_BEST);
		// }
		//
		// if (orderBy == 1) {
		// // 1 ��ʾֻ��������ʱ������
		// hql += " ORDER BY t.lastUpdateTime " + (asc ? "ASC" : "DESC");
		// } else if (orderBy == 2) {
		// // ��ʾֻ�����ⷢ��ʱ������
		// hql += " ORDER BY t.postTime " + (asc ? "ASC" : "DESC");
		// } else if (orderBy == 3) {
		// // ��ʾֻ���ظ���������
		// hql += " ORDER BY t.replyCount " + (asc ? "ASC" : "DESC");
		// } else {
		// // 0 ��ʾĬ������(�����ö�����ǰ�棬����������ʱ�併������)
		// hql += " ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC";
		// }
		//
		// Object[] args = argsList.toArray();
		// PageBean pageBean = topicService.getPageBean(pageNum, hql, args);
		// ActionContext.getContext().getValueStack().push(pageBean); // �ŵ�ջ��

		// ׼����ҳ������ v4 �����հ棩-- ʹ��QueryHelper
		new QueryHelper(Topic.class, "t")//
				.addWhereCondition("t.forum=?", forum)//
				.addWhereCondition((viewType == 1), "t.type=?", Topic.TYPE_BEST) // 1 ��ʾֻ��������
				.addOrderByProperty((orderBy == 1), "t.lastUpdateTime", asc) // 1 ��ʾֻ��������ʱ������
				.addOrderByProperty((orderBy == 2), "t.postTime", asc) // ��ʾֻ�����ⷢ��ʱ������
				.addOrderByProperty((orderBy == 3), "t.replyCount", asc) // ��ʾֻ���ظ���������
				.addOrderByProperty((orderBy == 0), "(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)//
				.addOrderByProperty((orderBy == 0), "t.lastUpdateTime", false) // 0 ��ʾĬ������(�����ö�����ǰ�棬����������ʱ�併������)
				.preparePageBean(topicService, pageNum);

		return "show";
	}

	// ---

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}
}
