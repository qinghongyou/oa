package xiao.oa.service;

import java.util.List;

import xiao.oa.base.DaoSupport;
import xiao.oa.domain.Privilege;


public interface PrivilegeService extends DaoSupport<Privilege> {

	/**
	 * ��ѯ���ж�����Ȩ���б�
	 * 
	 * @return
	 */
	List<Privilege> findTopList();

	/**
	 * ��ѯ����Ȩ�޵�URL�ļ��ϣ�������nullֵ���ظ���ֵ��
	 * 
	 * @return
	 */
	List<String> getAllPrivilegeUrls();

}
