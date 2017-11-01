package xiao.oa.service;

import java.util.List;

import xiao.oa.base.DaoSupport;
import xiao.oa.domain.PageBean;
import xiao.oa.domain.Reply;
import xiao.oa.domain.Topic;


public interface ReplyService extends DaoSupport<Reply> {

	/**
	 * ��ѯָ�������еĻظ��б��������µĻظ��ŵ�����档
	 * 
	 * @param topic
	 * @return
	 */
	List<Reply> findByTopic(Topic topic);

	/**
	 * ��ѯ��ҳ�Ļظ��б�����
	 * 
	 * @param pageNum
	 * @param topic
	 * @return
	 */
	@Deprecated
	PageBean getPageBeanByTopic(int pageNum, Topic topic);

}
