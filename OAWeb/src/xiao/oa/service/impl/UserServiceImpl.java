package xiao.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.base.DaoSupportImpl;
import xiao.oa.domain.User;
import xiao.oa.service.UserService;


@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

	@Override
	public void save(User user) {
		// Ĭ��������1234
		String md5 = DigestUtils.md5Hex("1234"); // ����Ҫʹ��MD5ժҪ
		user.setPassword(md5);

		// ���浽���ݿ�
		getSession().save(user);
	}

	public User findByLoginNameAndPassword(String loginName, String password) {
		String md5 = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=? AND u.password=?")//
				.setParameter(0, loginName)//
				.setParameter(1, md5)// ����Ҫʹ��MD5ժҪ
				.uniqueResult();
	}

}
