package xiao.oa.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.jbpm.api.ProcessDefinition;

public interface ProcessDefinitionService {

	/**
	 * ��ѯ�������°汾�����̶����б�
	 * 
	 * @return
	 */
	List<ProcessDefinition> findAllLatestVersionList();

	/**
	 * ɾ��ָ��key�����а汾�����̶���
	 * 
	 * @param key
	 */
	void deleteByKey(String key);

	/**
	 * �������̶��壨ʹ��zip���ķ�ʽ��
	 * 
	 * @param zipFile
	 */
	void deployByZip(File zipFile);

	/**
	 * ��ȡָ�����̶����е�����ͼƬ�ļ���������
	 * 
	 * @param id
	 * @return
	 */
	InputStream getImageResourceAsStreamByPdId(String id);

}
