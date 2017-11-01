package xiao.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ��ת�ı��ĵ�
 * 
 * @author Administrator
 * 
 */
public class Application {

	/** ״̬������������ */
	public static final String STATUS_RUNNING = "������";

	/** ״̬��������ͨ�� */
	public static final String STATUS_APPROVED = "��ͨ��";

	/** ״̬������δͨ�� */
	public static final String STATUS_REJECTED = "δͨ��";
	private Long id;
	private Template template;// ��ʹ�õ�����ģ��
	private Set<ApproveInfo> approveInfos = new HashSet<ApproveInfo>();
	private User applicant;// ������

	private String title;// ����
	private Date applyTime;// ����ʱ��
	private String path;// �ĵ��Ĵ洢·��
	private String status; // ��ǰ��״̬

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<ApproveInfo> getApproveInfos() {
		return approveInfos;
	}

	public void setApproveInfos(Set<ApproveInfo> approveInfos) {
		this.approveInfos = approveInfos;
	}

}
