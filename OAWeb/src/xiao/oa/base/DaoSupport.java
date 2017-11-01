package xiao.oa.base;

import java.util.List;

import xiao.oa.domain.PageBean;
import xiao.oa.util.QueryHelper;


public interface DaoSupport<T> {

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * ����id��ѯ
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);

	/**
	 * ����id�����ѯ���
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * ��ѯ����
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * �����Ĳ�ѯ��ҳ��Ϣ�ķ���
	 * 
	 * @param pageNum
	 * @param hql
	 *            ��ѯ�����б��hql��䣬�ڷ����ڲ����Զ����ɲ�ѯ��������hql���
	 * @param args
	 * @return
	 */
	@Deprecated
	PageBean getPageBean(int pageNum, String hql, Object[] args);

	/**
	 * �����Ĳ�ѯ��ҳ��Ϣ�ķ��������հ棩
	 * 
	 * @param pageNum
	 * @param queryHelper
	 *            ��ѯ��� + �����б�
	 * @return
	 */
	PageBean getPageBean(int pageNum, QueryHelper queryHelper);
}
