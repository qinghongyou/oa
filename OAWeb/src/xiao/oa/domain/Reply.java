package xiao.oa.domain;

/**
 * 实体：回帖
 * 
 * @author Administrator
 * 
 */
public class Reply extends Article {
	private Topic topic; // 所属的主题
	private boolean deleted; // 是否已删除

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
