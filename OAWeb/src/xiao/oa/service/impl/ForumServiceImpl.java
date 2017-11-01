package xiao.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.base.DaoSupportImpl;
import xiao.oa.domain.Forum;
import xiao.oa.service.ForumService;


@Service
@Transactional
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService {

	/**
	 * �ڲ�ѯ�б�ʱҪ��position��ֵ����
	 */
	public List<Forum> findAll() {
		return getSession().createQuery(//
				"FROM Forum f ORDER BY f.position")//
				.list();
	}

	/**
	 * ��дsave()������������Ҫ����position��ֵ
	 */
	public void save(Forum forum) {
		// ���浽���ݿ�
		getSession().save(forum);

		// ����position��ֵ������ʹ��id��ֵ��
		forum.setPosition(forum.getId().intValue());
	}

	public void moveUp(Long id) {
		// 1���ҳ�Ҫ��λ�úŵ�Forum����
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(//
				"FROM Forum f WHERE f.position<? ORDER BY f.position DESC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)// �ӽ���б��е��ĸ�������ʼȡ����
				.setMaxResults(1)// ���ȡ��������
				.uniqueResult();

		// �����治������
		if (other == null) {
			return;
		}

		// 2������position��ֵ
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		// 3�����浽���ݿ���
		getSession().update(forum);
		getSession().update(other);
	}

	public void moveDown(Long id) {
		// 1���ҳ�Ҫ��λ�úŵ�Forum����
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(// 
				"FROM Forum f WHERE f.position>? ORDER BY f.position ASC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)// �ӽ���б��е��ĸ�������ʼȡ����
				.setMaxResults(1)// ���ȡ��������
				.uniqueResult();

		// �����治������
		if (other == null) {
			return;
		}

		// 2������position��ֵ
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		// 3�����浽���ݿ���
		getSession().update(forum);
		getSession().update(other);
	}
}
