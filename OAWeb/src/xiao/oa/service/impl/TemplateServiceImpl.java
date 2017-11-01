package xiao.oa.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiao.oa.base.DaoSupportImpl;
import xiao.oa.domain.Template;
import xiao.oa.service.TemplateService;


@Service
@Transactional
public class TemplateServiceImpl extends DaoSupportImpl<Template> implements TemplateService {

	@Override
	public void delete(Long id) {
		// 删除数据库记录
		Template template = getById(id);
		getSession().delete(template);

		// 删除文件
		File file = new File(template.getPath());
		if (file.exists()) {
			file.delete();
		}
	}
	
}
