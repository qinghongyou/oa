package xiao.oa.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.domain.User;

@Service
public class TestService {
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Transactional
	public void saveTwoUser(){
		Session session =sessionFactory.getCurrentSession();
		session.save(new User());
		//int i=10/0; //Å×³öÒì³£
		session.save(new User());
	}
	

}
