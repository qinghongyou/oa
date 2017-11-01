package xiao.oa.service;

import java.util.List;

import xiao.oa.base.DaoSupport;
import xiao.oa.domain.Department;


public interface DepartmentService extends DaoSupport<Department> {
	//
	// List<Department> findAll();
	//
	// void delete(Long id);
	//
	// void save(Department department);
	//
	// Department getById(Long id);
	//
	// void update(Department department);

	/**
	 * ��ѯ���ж������ŵ��б�
	 * 
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * ��ѯ�����Ӳ��ŵ��б�
	 * 
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

}
