package xiao.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.base.DaoSupportImpl;
import xiao.oa.cfg.Configuration;
import xiao.oa.domain.Forum;
import xiao.oa.domain.PageBean;
import xiao.oa.domain.Reply;
import xiao.oa.domain.Topic;
import xiao.oa.service.ReplyService;


@Service
@Transactional
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService {

	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
				// �������µĻظ��ŵ������
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.list();
	}

	@Override
	public void save(Reply reply) {
		// 1���������Բ�����
		reply.setDeleted(false); // Ĭ��Ϊδɾ��
		reply.setPostTime(new Date()); // ��ǰʱ��
		getSession().save(reply);

		// 2��������ص���Ϣ
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();

		forum.setArticleCount(forum.getArticleCount() + 1); // ������������������+�ظ���
		topic.setReplyCount(topic.getReplyCount() + 1); // ����Ļظ�����
		topic.setLastUpdateTime(reply.getPostTime()); // �����������ʱ�䣨���ⷢ���ʱ������ظ���ʱ�䣩
		topic.setLastReply(reply); // �������󷢱�Ļظ�

		getSession().update(topic);
		getSession().update(forum);
	}

	public PageBean getPageBeanByTopic(int pageNum, Topic topic) {
		// ��ȡpageSize��Ϣ
		int pageSize = Configuration.getPageSize();

		// ��ѯһҳ�������б�
		List list = getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();

		// ��ѯ�ܼ�¼��
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Reply r WHERE r.topic=?")//
				.setParameter(0, topic)//
				.uniqueResult();

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

}
