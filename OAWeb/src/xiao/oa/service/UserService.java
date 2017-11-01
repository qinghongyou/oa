package xiao.oa.service;

import xiao.oa.base.DaoSupport;
import xiao.oa.domain.User;

public interface UserService extends DaoSupport<User> {

	/**
	 * ��֤�û��������룬�����ȷ�ͷ�������û������򷵻�null
	 * 
	 * @param loginName
	 * @param password ��������
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
