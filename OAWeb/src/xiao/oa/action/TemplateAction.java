package xiao.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.ModelDrivenBaseAction;
import xiao.oa.domain.Template;


import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TemplateAction extends ModelDrivenBaseAction<Template> {

	private File upload; // �ϴ����ļ�
	private InputStream inputStream; // �����õ�

	/** �б� */
	public String list() throws Exception {
		List<Template> templateList = templateService.findAll();
		ActionContext.getContext().put("templateList", templateList);
		return "list";
	}

	/** ɾ�� */
	public String delete() throws Exception {
		templateService.delete(model.getId());
		return "toList";
	}

	/** ���ҳ�� */
	public String addUI() throws Exception {
		// ׼�����ݣ�processDefinitionList
		List<ProcessDefinition> processDefinitionList = processDefinitionService.findAllLatestVersionList();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
		return "saveUI";
	}

	/** ��� */
	public String add() throws Exception {
		// ��װ����
		Template template = new Template();
		template.setName(model.getName());
		template.setProcessKey(model.getProcessKey());
		// >> �����ϴ����ļ�
		String path = saveUploadFile(upload);
		template.setPath(path);

		// ����ҵ�񷽷������棩
		templateService.save(template);

		return "toList";
	}

	/** �޸�ҳ�� */
	public String editUI() throws Exception {
		// ׼�����Ե�����
		Template template = templateService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(template);
		
		// ׼�����ݣ�processDefinitionList
		List<ProcessDefinition> processDefinitionList = processDefinitionService.findAllLatestVersionList();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
	
		return "saveUI";
	}

	/** �޸� */
	public String edit() throws Exception {
		// 1�������ݿ��л�ȡԭ����
		Template template = templateService.getById(model.getId());
		
		// 2������Ҫ�޸ĵ�����
		template.setName(model.getName());
		template.setProcessKey(model.getProcessKey());
		if(upload != null){ // ����ϴ����ļ����ű�ʾҪ�޸��ļ�ģ������
			// ɾ�����ļ�
			File file = new File(template.getPath());
			if(file.exists()){
				file.delete();
			}
			// �������ļ�
			String path = saveUploadFile(upload);
			template.setPath(path);
		}
		
		// 3�����µ����ݿ���
		templateService.update(template);
		
		return "toList";
	}

	/** ���� */
	public String download() throws Exception {
		// ��ȡģ��������Ϣ
		Template template = templateService.getById(model.getId());
		String path = template.getPath();

		// ׼��Ĭ�ϵ��ļ���
		String fileName = template.getName();
		fileName = URLEncoder.encode(fileName, "utf-8"); // ������ص��ļ�������������
		ActionContext.getContext().put("fileName", fileName);

		// ׼��Ҫ���ص�����
		inputStream = new FileInputStream(path);
		return "download";
	}

	// ---

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
