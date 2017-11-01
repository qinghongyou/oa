package xiao.oa.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.ModelDrivenBaseAction;
import xiao.oa.domain.Forum;
import xiao.oa.domain.PageBean;
import xiao.oa.domain.Reply;
import xiao.oa.domain.Topic;
import xiao.oa.util.QueryHelper;


import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TopicAction extends ModelDrivenBaseAction<Topic> {

	private Long forumId;

	/** ��ʾ�������� */
	public String show() throws Exception {
		// ׼������: topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// // ׼������: replyList
		// List<Reply> replyList = replyService.findByTopic(topic);
		// ActionContext.getContext().put("replyList", replyList);

		// // ׼����ҳ������ v1
		// PageBean pageBean = replyService.getPageBeanByTopic(pageNum, topic);
		// ActionContext.getContext().getValueStack().push(pageBean);

		// // ׼����ҳ������ v2
		// String hql = "FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC";
		// Object[] args = { topic };
		// PageBean pageBean = replyService.getPageBean(pageNum, hql, args);
		// ActionContext.getContext().getValueStack().push(pageBean);

		// ׼����ҳ������ �����հ棩
		new QueryHelper(Reply.class, "r")//
				.addWhereCondition("r.topic=?", topic)//
				.addOrderByProperty("r.postTime", true)//
				.preparePageBean(replyService, pageNum);
		
		return "show";
	}

	/** ������ҳ�� */
	public String addUI() throws Exception {
		// ׼������
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}

	/** ������ */
	public String add() throws Exception {
		// ��װ����
		Topic topic = new Topic();
		// >> a, ���еĲ���
		topic.setTitle(model.getTitle());
		topic.setContent(model.getContent());
		topic.setForum(forumService.getById(forumId));
		// >> b, ����ʾ����ܻ�õ�����
		topic.setAuthor(getCurrentUser()); // ��ǰ��¼���û�
		topic.setIpAddr(getRequestIP()); // �ͻ��˵�IP��ַ

		// ����ҵ�񷽷�
		topicService.save(topic);

		ActionContext.getContext().put("topicId", topic.getId());
		return "toShow"; // ת����ǰ������������ʾҳ��
	}

	// ---

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

}
