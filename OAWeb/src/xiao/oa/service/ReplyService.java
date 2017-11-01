package xiao.oa.service;

import java.util.List;

import xiao.oa.base.DaoSupport;
import xiao.oa.domain.PageBean;
import xiao.oa.domain.Reply;
import xiao.oa.domain.Topic;


public interface ReplyService extends DaoSupport<Reply> {

	/**
	 * 查询指定主题中的回复列表，排序：最新的回复排到最后面。
	 * 
	 * @param topic
	 * @return
	 */
	List<Reply> findByTopic(Topic topic);

	/**
	 * 查询分页的回复列表数据
	 * 
	 * @param pageNum
	 * @param topic
	 * @return
	 */
	@Deprecated
	PageBean getPageBeanByTopic(int pageNum, Topic topic);

}
