package xiao.oa.service;

import java.util.List;

import xiao.oa.base.DaoSupport;
import xiao.oa.domain.Forum;
import xiao.oa.domain.PageBean;
import xiao.oa.domain.Topic;


public interface TopicService extends DaoSupport<Topic> {

	/**
	 * ��ѯָ������е������б���������״̬���ŵ�ǰ�棬�ö����������档
	 * 
	 * @param forum
	 * @return
	 */
	List<Topic> findByForum(Forum forum);

	/**
	 * ��ѯ��ҳ�������б�����
	 * 
	 * @param pageNum
	 * @param forum
	 * @return
	 */
	@Deprecated
	PageBean getPageBeanByForum(int pageNum, Forum forum);

}
