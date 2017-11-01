package xiao.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transaction;

import org.hibernate.SessionFactory;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.base.DaoSupportImpl;
import xiao.oa.domain.Application;
import xiao.oa.domain.User;
import xiao.oa.service.FlowService;
import xiao.oa.service.ManService;

@Service
@Transactional
public class ManServiceImpl extends DaoSupportImpl<User> implements ManService {
	@Resource
	private SessionFactory sessionFactory;

	public User findMyList(User currentUser) {
		// 查询我的信息列表	
		Long userId = currentUser.getId(); // 使用userId作为用户标识符
		return (User) sessionFactory//
				.getCurrentSession()//
				.get(User.class, userId);
	}


}
