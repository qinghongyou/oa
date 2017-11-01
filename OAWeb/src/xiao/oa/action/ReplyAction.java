package xiao.oa.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.ModelDrivenBaseAction;
import xiao.oa.domain.Reply;
import xiao.oa.domain.Topic;


import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ReplyAction extends ModelDrivenBaseAction<Reply> {

	private Long topicId;

	/** ����ظ�ҳ�� */
	public String addUI() throws Exception {
		// ׼������
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}

	/** ����ظ� */
	public String add() throws Exception {
		// ��װ����
		Reply reply = new Reply();
		// a, ���еĲ���
		reply.setContent(model.getContent());
		reply.setTopic(topicService.getById(topicId));
		// b, ����ʾ����ܻ�õ�����
		reply.setAuthor(getCurrentUser()); // ��ǰ��¼���û�
		reply.setIpAddr(getRequestIP()); // �ͻ��˵�IP��ַ

		// ����ҵ�񷽷�
		replyService.save(reply);

		return "toTopicShow"; // ת����ǰ����»ظ�������������ʾҳ��
	}

	// ---

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
