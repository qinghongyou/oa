package xiao.oa.service;

import java.util.List;

import xiao.oa.base.DaoSupport;
import xiao.oa.domain.User;

public interface ManService  extends DaoSupport<User> {

	/**
	 * ��ѯ������Ϣ
	 * @param currentUser
	 * @return
	 */
	User findMyList(User currentUser);

}
