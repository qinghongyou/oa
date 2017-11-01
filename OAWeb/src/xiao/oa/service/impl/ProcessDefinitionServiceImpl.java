package xiao.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.service.ProcessDefinitionService;


@Service
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

	@Resource
	private SessionFactory sessionFactory;

	@Resource
	private ProcessEngine processEngine;

	public void deployByZip(File zipFile) {
		ZipInputStream zipInputStream = null;
		try {
			// ׼��
			zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
			// ����
			processEngine.getRepositoryService()//
					.createDeployment()//
					.addResourcesFromZipInputStream(zipInputStream)//
					.deploy();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (zipInputStream != null) {
				try {
					zipInputStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public void deleteByKey(String key) {
		// ��ѯָ��key�����а汾�����̶���
		List<ProcessDefinition> list = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.processDefinitionKey(key)// ��������
				.list();// ִ�в�ѯ
		// ѭ���h��
		for (ProcessDefinition pd : list) {
			processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itcast.oa.service.ProcessDefinitionService#findAllLatestVersionList()
	 */
	public List<ProcessDefinition> findAllLatestVersionList() {
		// ��ѯ���е����̶��壨�������еİ汾�������汾�������С�
		List<ProcessDefinition> all = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)//
				.list();// ִ�в�ѯ

		// ���˳��������µİ汾�����̶����б�һ��keyֻ��һ�����µİ汾��
		Map<String, ProcessDefinition> map = new LinkedHashMap<String, ProcessDefinition>();
		for (ProcessDefinition pd : all) {
			map.put(pd.getKey(), pd);
		}

		return new ArrayList<ProcessDefinition>(map.values());
	}

	public InputStream getImageResourceAsStreamByPdId(String pdId) {
		// ��ȡ��Ϣ
		ProcessDefinition pd = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.processDefinitionId(pdId)//
				.uniqueResult();
		String deploymentId = pd.getDeploymentId();
		String resourceName = pd.getImageResourceName();

		// �õ�������
		return processEngine.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
	}

}
