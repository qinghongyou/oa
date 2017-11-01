package xiao.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xiao.oa.base.BaseAction;


import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends BaseAction {

	private File upload; // �ϴ����ļ�
	private InputStream inputStream; // �����õ�������
	private String id;
	private String key;

	
	/** �б���ʾ�����������°汾�����̶��� */
	public String list() throws Exception {
		List<ProcessDefinition> pdList = processDefinitionService.findAllLatestVersionList();
		ActionContext.getContext().put("pdList", pdList);
		return "list";
	}

	/** ɾ����ɾ������ָ��key�����а汾�����̶��� */
	public String delete() throws Exception {
		key = new String(key.getBytes("iso8859-1"), "utf-8"); // ���GET��ʽ���ݵ��������������
		processDefinitionService.deleteByKey(key);
		return "toList";
	}

	/** ���ҳ�棨����ҳ�棩 */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** ��ӣ����� */
	public String add() throws Exception {
		processDefinitionService.deployByZip(upload);
		return "toList";
	}

	/** �鿴����ͼ��xxx.png�� */
	public String showProcessImage() throws Exception {
		id = new String(id.getBytes("iso8859-1"), "utf-8"); // ���GET��ʽ���ݵ��������������
		inputStream = processDefinitionService.getImageResourceAsStreamByPdId(id);
		return "showProcessImage";
	}

	// ---

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
