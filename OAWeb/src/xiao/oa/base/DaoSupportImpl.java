package xiao.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.cfg.Configuration;
import xiao.oa.domain.PageBean;
import xiao.oa.util.QueryHelper;


// ���@Transactionalע��������еķ���Ҳ��Ч��
@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz = null; // ����һ�����⣡

	// public BaseDaoImpl(Class<T> clazz) {
	// this.clazz = clazz;
	// }

	public DaoSupportImpl() {
		// ͨ�������ȡT����ʵ����
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];

		System.out.println("---> clazz = " + clazz);
	}

	/**
	 * ��ȡ��ǰ���õ�Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(Long id) {
		if (id == null) {
			return;
		}

		Object entity = getById(id);
		if (entity != null) {
			getSession().delete(entity);
		}
	}

	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}

	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}

		return getSession().createQuery(//
				// ע��ո�
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids)// ע��һ��Ҫʹ��setParameterList()������
				.list();
	}

	public List<T> findAll() {
		// ע��ո�
		return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
	}

	// �����Ĳ�ѯ��ҳ��Ϣ�ķ���
	public PageBean getPageBean(int pageNum, String hql, Object[] args) {
		System.out.println("------------> DaoSupportImpl.getPageBean()");

		// ��ȡpageSize��Ϣ
		int pageSize = Configuration.getPageSize();

		// ��ѯһҳ�������б�
		Query query = getSession().createQuery(hql);
		if (args != null && args.length > 0) { // ���ò���
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		List list = query.list(); // ��ѯ

		// ��ѯ�ܼ�¼��
		query = getSession().createQuery("SELECT COUNT(*) " + hql); // ע��ո�
		if (args != null && args.length > 0) { // ���ò���
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		Long count = (Long) query.uniqueResult(); // ��ѯ

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

	/**
	 * �����Ĳ�ѯ��ҳ��Ϣ�ķ��������հ棩
	 * 
	 * @param pageNum
	 * @param queryHelper
	 *            ��ѯ��� + �����б�
	 * @return
	 */
	public PageBean getPageBean(int pageNum, QueryHelper queryHelper) {
		System.out.println("------------> DaoSupportImpl.getPageBean( int pageNum, QueryHelper queryHelper )");

		// ��ȡpageSize����Ϣ
		int pageSize = Configuration.getPageSize();
		List<Object> parameters = queryHelper.getParameters();

		// ��ѯһҳ�������б�
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		if (parameters != null && parameters.size() > 0) { // ���ò���
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		List list = query.list(); // ��ѯ

		// ��ѯ�ܼ�¼��
		query = getSession().createQuery(queryHelper.getQueryCountHql()); // ע��ո�
		if (parameters != null && parameters.size() > 0) { // ���ò���
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) query.uniqueResult(); // ��ѯ

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

}
