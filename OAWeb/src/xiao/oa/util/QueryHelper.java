package xiao.oa.util;

import java.util.ArrayList;
import java.util.List;

import xiao.oa.base.DaoSupport;
import xiao.oa.domain.PageBean;


import com.opensymphony.xwork2.ActionContext;

/**
 * ����ƴ��HQL���Ĺ�����
 * 
 * @author Administrator
 * 
 */
public class QueryHelper {

	private String fromClause; // From�Ӿ�
	private String whereClause = ""; // Where�Ӿ�
	private String orderByClause = ""; // OrderBy�Ӿ�

	private List<Object> parameters = new ArrayList<Object>(); // �����б�

	/**
	 * ����From�Ӿ�
	 * 
	 * @param clazz
	 * @param alias
	 *            ����
	 */
	public QueryHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * ƴ��Where�Ӿ�
	 * 
	 * @param condition
	 * @param args
	 */
	public QueryHelper addWhereCondition(String condition, Object... args) {
		// ƴ��
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}
		// �������
		if (args != null && args.length > 0) {
			for (Object arg : args) {
				parameters.add(arg);
			}
		}
		return this;
	}

	/**
	 * �����һ��������ֵΪtrue����ƴ��Where�Ӿ�
	 * 
	 * @param append
	 * @param condition
	 * @param args
	 */
	public QueryHelper addWhereCondition(boolean append, String condition, Object... args) {
		if (append) {
			addWhereCondition(condition, args);
		}
		return this;
	}

	/**
	 * ƴ��OrderBy�Ӿ�
	 * 
	 * @param propertyName
	 * @param asc
	 *            true��ʾ����false��ʾ����
	 */
	public QueryHelper addOrderByProperty(String propertyName, boolean asc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName + (asc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (asc ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * �����һ��������ֵΪtrue����ƴ��OrderBy�Ӿ�
	 * 
	 * @param append
	 * @param propertyName
	 * @param asc
	 */
	public QueryHelper addOrderByProperty(boolean append, String propertyName, boolean asc) {
		if (append) {
			addOrderByProperty(propertyName, asc);
		}
		return this;
	}

	/**
	 * ��ȡ��ѯ�����б��HQL���
	 * 
	 * @return
	 */
	public String getQueryListHql() {
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * ��ȡ��ѯ�ܼ�¼����HQL��䣨û��OrderBy�Ӿ䣩
	 * 
	 * @return
	 */
	public String getQueryCountHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * ��ȡ�����б�
	 * 
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	/**
	 * ׼��PageBean����Struts2��ջ��
	 * @param service
	 * @param pageNum
	 */
	public void preparePageBean(DaoSupport<?> service, int pageNum){
		PageBean pageBean = service.getPageBean(pageNum, this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}
}
