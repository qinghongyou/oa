package xiao.oa.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;

import xiao.oa.domain.Template;
import xiao.oa.domain.User;
import xiao.oa.service.DepartmentService;
import xiao.oa.service.FlowService;
import xiao.oa.service.ForumService;
import xiao.oa.service.ManService;
import xiao.oa.service.PrivilegeService;
import xiao.oa.service.ProcessDefinitionService;
import xiao.oa.service.ReplyService;
import xiao.oa.service.RoleService;
import xiao.oa.service.TemplateService;
import xiao.oa.service.TopicService;
import xiao.oa.service.UserService;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	// ===================== ����Service ====================
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;

	@Resource
	protected ForumService forumService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;

	@Resource
	protected ProcessDefinitionService processDefinitionService;
	@Resource
	protected TemplateService templateService;
	@Resource
	protected FlowService flowService;
	@Resource
	protected ManService manService;

	// ========================= �Է�ҳ��֧�� =========================

	protected int pageNum = 1; // ��ǰҳ��Ĭ��Ϊ��1ҳ

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	// ========================== ���߷��� ==========================

	/**
	 * ��ȡ��ǰ��¼���û�
	 */
	public User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	/**
	 * ��ȡ�ͻ�����IP��ַ
	 * 
	 * @return
	 */
	public String getRequestIP() {
		return ServletActionContext.getRequest().getRemoteAddr();
	}
	
	/**
	 * �����ϴ����ļ����������ڷ���������ʵ�Ĵ洢·��
	 * @param upload
	 * @return
	 */
	protected String saveUploadFile(File upload) {
		// >> 1, �õ��ڱ�����ļ�·������ʵ��ַ
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload_files");
		String datePath = sdf.format(new Date());
		
		// >> 2, ����ļ��в����ڣ��ʹ���
		File dir = new File(basePath +datePath);
		if(!dir.exists()){
			dir.mkdirs();  
		}
		String path = basePath+ datePath + UUID.randomUUID().toString(); // ע��ͬ�������⣬����ʹ��uuid��Ϊ�ļ���
		File destFile = new File(path);
		
		// >> 3, �ƶ��ļ�
		upload.renameTo(destFile);
		return path;
	}
}
