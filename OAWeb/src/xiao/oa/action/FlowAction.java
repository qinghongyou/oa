package xiao.oa.action;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.BaseAction;
import xiao.oa.domain.Application;
import xiao.oa.domain.ApproveInfo;
import xiao.oa.domain.TaskView;
import xiao.oa.domain.Template;
import xiao.oa.util.QueryHelper;


import com.opensymphony.xwork2.ActionContext;

/**
 * ��ת����
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
public class FlowAction extends BaseAction {

	private Long templateId;
	private Long applicationId;
	private File upload; // �ϴ����ļ�
	private String status;
	private String taskId;

	private boolean approval;
	private String comment;
	private String outcome;

	// =========================== �����˵Ĺ��� =================================

	/** ������루����ģ���б� */
	public String templateList() throws Exception {
		List<Template> templateList = templateService.findAll();
		ActionContext.getContext().put("templateList", templateList);
		return "templateList";
	}

	/** �ύ����ҳ�� */
	public String submitUI() throws Exception {
		return "submitUI";
	}

	/** �ύ���� */
	public String submit() throws Exception {
		// ��װ����
		Application application = new Application();
		// >> 1, ���еĲ���
		application.setTemplate(templateService.getById(templateId)); // ������ģ��
		application.setPath(saveUploadFile(upload)); // �ϴ����ļ�
		// >> 2, ��ʾ�����Ϣ
		application.setApplicant(getCurrentUser()); // ��ǰ��¼���û�

		// ����ҵ�񷽷�������������Ϣ���������̿�ʼ��ת��
		flowService.submit(application);

		return "toMyApplicationList"; // ת���ҵ������ѯ
	}

	/** �ҵ������ѯ */
	public String myApplicationList() throws Exception {
		// ׼����ҳ����Ϣ
		new QueryHelper(Application.class, "a")//
				.addWhereCondition("a.applicant=?", getCurrentUser())// �������ǵ�ǰ��¼�û�
				.addWhereCondition((templateId != null), "a.template.id=?", templateId)//
				.addWhereCondition((status != null && status.trim().length() > 0), "a.status=?", status)//
				.addOrderByProperty("a.applyTime", false)//
				.preparePageBean(userService, pageNum); // ע��serviceҪ��һ���̳���DaoSupportImpl����

		// ׼��ģ���б�
		List<Template> templateList = templateService.findAll();
		ActionContext.getContext().put("templateList", templateList);

		return "myApplicationList";
	}

	// =========================== �����˵Ĺ��� =================================

	/** �������� */
	public String myTaskList() throws Exception {
		List<TaskView> taskViewList = flowService.findMyTaskViewList(getCurrentUser());
		ActionContext.getContext().put("taskViewList", taskViewList);
		return "myTaskList";
	}

	/** ��������ҳ�� */
	public String approveUI() throws Exception {
		// ׼������
		Collection<String> outcomes = flowService.getOutcomesByTaskId(taskId);
		ActionContext.getContext().put("outcomes", outcomes);
		return "approveUI";
	}

	/** �������� */
	public String approve() throws Exception {
		// ��װ����
		ApproveInfo approveInfo = new ApproveInfo();
		approveInfo.setApproval(approval); // 
		approveInfo.setComment(comment);
		approveInfo.setApplication(flowService.getApplicationById(applicationId)); // ����������
		approveInfo.setApprover(getCurrentUser()); // �����ˣ���ǰ��¼�û�
		approveInfo.setApproveTime(new Date()); // ����ʱ�䣬��ǰʱ��

		// ����ҵ�񷽷�������������Ϣ����ɵ�ǰ����ά�������״̬��
		flowService.approve(approveInfo, taskId, outcome);

		return "toMyTaskList"; // ת����������
	}

	/** �鿴��ת��¼ */
	public String approvedHistory() throws Exception {
		List<ApproveInfo> approveInfoList = flowService.getApproveInfosByApplicationId(applicationId);
		ActionContext.getContext().put("approveInfoList", approveInfoList);
		return "approvedHistory";
	}
 
	// ---

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

}
