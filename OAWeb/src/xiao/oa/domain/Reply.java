package xiao.oa.domain;

/**
 * ʵ�壺����
 * 
 * @author Administrator
 * 
 */
public class Reply extends Article {
	private Topic topic; // ����������
	private boolean deleted; // �Ƿ���ɾ��

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
