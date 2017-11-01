package xiao.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.base.DaoSupportImpl;
import xiao.oa.cfg.Configuration;
import xiao.oa.domain.Forum;
import xiao.oa.domain.PageBean;
import xiao.oa.domain.Topic;
import xiao.oa.service.TopicService;


@Service
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService {

	public List<Topic> findByForum(Forum forum) {
		return getSession().createQuery(//
				// ��������״̬���ŵ�ǰ�棬�ö����������档
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}

	@Override
	public void save(Topic topic) {
		// 1���������Բ�����
		topic.setType(Topic.TYPE_NORMAL); // ��ͨ��
		topic.setReplyCount(0);
		topic.setLastReply(null);
		topic.setPostTime(new Date()); // ��ǰʱ��
		topic.setLastUpdateTime(topic.getPostTime()); // Ĭ��Ϊ����ķ���ʱ��
		getSession().save(topic); // ����

		// 2��������ص���Ϣ
		Forum forum = topic.getForum();

		forum.setTopicCount(forum.getTopicCount() + 1); // ��������
		forum.setArticleCount(forum.getArticleCount() + 1); // ��������������+�ظ���
		forum.setLastTopic(topic); // ��󷢱������

		getSession().update(forum);
	}

	public PageBean getPageBeanByForum(int pageNum, Forum forum) {
		// ��ȡpageSize��Ϣ
		int pageSize = Configuration.getPageSize();

		// ��ѯһҳ�������б�
		List list = getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();

		// ��ѯ�ܼ�¼��
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
				.setParameter(0, forum)//
				.uniqueResult();

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

}
