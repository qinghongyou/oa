package xiao.oa.service;

import xiao.oa.base.DaoSupport;
import xiao.oa.domain.Application;
import xiao.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {

	/**
	 * ���ƣ�������Ĳ�������
	 * 
	 * @param id
	 *            ��ǰҪ�ƶ��İ���id
	 */
	void moveUp(Long id);

	/**
	 * ���ƣ�������Ĳ�������
	 * 
	 * @param id
	 *            ��ǰҪ�ƶ��İ���id
	 */
	void moveDown(Long id);


}
