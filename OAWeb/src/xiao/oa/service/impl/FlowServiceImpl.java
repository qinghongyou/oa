package xiao.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.domain.Application;
import xiao.oa.domain.ApproveInfo;
import xiao.oa.domain.TaskView;
import xiao.oa.domain.User;
import xiao.oa.service.FlowService;


@Service
@Transactional
public class FlowServiceImpl implements FlowService {

	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private ProcessEngine processEngine;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void submit(Application application) {
		// 1���������Բ�����������Ϣ
		application.setApplyTime(new Date()); // ����ʱ��
		application.setStatus(Application.STATUS_RUNNING); // ״̬��Ĭ��Ϊ�����������С�
		application.setTitle(application.getTemplate().getName()// �����ʽ��{ģ������}_{����������}_{��������}
				+ "_" + application.getApplicant().getName()//
				+ "_" + sdf.format(application.getApplyTime()));
		sessionFactory.getCurrentSession().save(application); // ����

		// 2���������̿�ʼ��ת
		// >> ��������ʵ�����������̱���application
		String processKey = application.getTemplate().getProcessKey(); // ���̶����key
		Map<String, Object> variables = new HashMap<String, Object>(); // ���̱���
		variables.put("application", application);
		ProcessInstance pi = processEngine.getExecutionService().startProcessInstanceByKey(processKey, variables);

		// >> �������һ�������ύ���룩
		Task task = processEngine.getTaskService()//
				.createTaskQuery()// ��ѯ��������ʵ���е�ǰ����½��е�һ������
				.processInstanceId(pi.getId())//
				.uniqueResult();
		processEngine.getTaskService().completeTask(task.getId());
	}

	public List<TaskView> findMyTaskViewList(User currentUser) {
		// ��ѯ�ҵ������б�
		String userId = currentUser.getLoginName(); // ʹ��loginName��ΪJBPM�е��û���ʶ��
		List<Task> taskList = processEngine.getTaskService().findPersonalTasks(userId);

		// ��ȡÿ�������Ӧ��������Ϣ
		List<TaskView> returnList = new ArrayList<TaskView>();
		for (Task task : taskList) {
			Application application = (Application) processEngine.getTaskService().getVariable(task.getId(), "application");
			TaskView tv = new TaskView(task, application);
			returnList.add(tv);
		}

		return returnList;
	}

	public Application getApplicationById(Long applicationId) {
		return (Application) sessionFactory//
				.getCurrentSession()//
				.get(Application.class, applicationId);
	}

	public void approve(ApproveInfo approveInfo, String taskId, String outcome) {
		// 1, ����������Ϣ
		sessionFactory.getCurrentSession().save(approveInfo);

		// 2, ��ɵ�ǰ����
		Task task = processEngine.getTaskService().getTask(taskId); // ��ȡ����Ĵ���һ��Ҫ��д���������ǰ����Ϊ����������ͱ������ʷ������Ϣ��
		if (outcome == null) {
			processEngine.getTaskService().completeTask(taskId); // ʹ��Ĭ�ϵ�Transition
		} else {
			processEngine.getTaskService().completeTask(taskId, outcome); // ʹ��ָ�����Ƶ�Transition
		}

		// >> ��ȡ��������������ʵ��������ִ�еı��е���Ϣ������������Ѿ������ˣ��򷵻�null.
		ProcessInstance pi = processEngine.getExecutionService().findProcessInstanceById(task.getExecutionId());

		// 3, ά�������״̬
		Application application = approveInfo.getApplication();
		if (!approveInfo.isApproval()) {
			// ��������ڲ�ͬ�⣬������ֱ�ӽ����������״̬Ϊ��δͨ����
			// ��������ڲ������һ�������Ǿ�Ҫ�ֹ������������ʵ��
			if (pi != null) {
				processEngine.getExecutionService().endProcessInstance(pi.getId(), ProcessInstance.STATE_ENDED);
			}
			application.setStatus(Application.STATUS_REJECTED);
		} else {
			if (pi == null) {
				// ���������ͬ���ˣ����ұ����������һ�����������������������������״̬Ϊ����ͨ����
				application.setStatus(Application.STATUS_APPROVED);
			}
		}
		sessionFactory.getCurrentSession().update(application);
	}

	public Collection<String> getOutcomesByTaskId(String taskId) {
		return processEngine.getTaskService().getOutcomes(taskId);
	}

	@SuppressWarnings("unchecked")
	public List<ApproveInfo> getApproveInfosByApplicationId(Long applicationId) {
		return sessionFactory.getCurrentSession()//
				.createQuery("FROM ApproveInfo a WHERE a.application.id=? ORDER BY a.approveTime ASC")//
				.setParameter(0, applicationId)//
				.list();
	}
}
