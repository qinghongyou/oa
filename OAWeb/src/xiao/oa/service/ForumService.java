package xiao.oa.service;

import xiao.oa.base.DaoSupport;
import xiao.oa.domain.Application;
import xiao.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {

	/**
	 * 上移，最上面的不能上移
	 * 
	 * @param id
	 *            当前要移动的版块的id
	 */
	void moveUp(Long id);

	/**
	 * 下移，最下面的不能下移
	 * 
	 * @param id
	 *            当前要移动的版块的id
	 */
	void moveDown(Long id);


}
