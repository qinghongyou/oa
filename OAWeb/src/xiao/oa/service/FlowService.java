package xiao.oa.service;

import java.util.Collection;
import java.util.List;

import xiao.oa.domain.Application;
import xiao.oa.domain.ApproveInfo;
import xiao.oa.domain.TaskView;
import xiao.oa.domain.User;


public interface FlowService {

	/**
	 * �ύ���룺
	 * 
	 * 1, ����������Ϣ.
	 * 
	 * 2, �������̿�ʼ��ת.
	 * 
	 * @param application
	 */
	void submit(Application application);

	/**
	 * ��ѯ�ҵ������б�
	 * 
	 * @param currentUser
	 * @return
	 */
	List<TaskView> findMyTaskViewList(User currentUser);

	/**
	 * ��������
	 * 
	 * 1, ����������Ϣ.
	 * 
	 * 2, ��ɵ�ǰ����.
	 * 
	 * 3, ά�������״̬.
	 * 
	 * @param approveInfo
	 * @param taskId
	 * @param outcome
	 *            ָ���뿪���Ҫʹ�õ�Transition�����ƣ����Ϊnull����ʹ��Ĭ�ϵ�Transition�뿪���
	 */
	void approve(ApproveInfo approveInfo, String taskId, String outcome);

	/**
	 * ��ȡ������Ϣ
	 * 
	 * @param applicationId
	 * @return
	 */
	Application getApplicationById(Long applicationId);

	/**
	 * ��ȡָ������������ָ����滷�ڵ�Transition������
	 * 
	 * @param taskId
	 * @return
	 */
	Collection<String> getOutcomesByTaskId(String taskId);

	/**
	 * ��ȡָ�������������е���ת��¼��������Ϣ��
	 * 
	 * @param applicationId
	 * @return
	 */
	List<ApproveInfo> getApproveInfosByApplicationId(Long applicationId);

}
