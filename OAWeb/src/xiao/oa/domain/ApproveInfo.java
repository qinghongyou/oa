package xiao.oa.domain;

import java.util.Date;

/**
 * ������Ϣ
 * 
 * @author Administrator
 * 
 */
public class ApproveInfo {
	private Long id;
	private Application application; // 
	private User approver;// ������

	private Date approveTime;// ����ʱ��
	private boolean approval; // �Ƿ�ͨ��
	private String comment; // �������

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
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

}
