package xiao.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ʵ�壺����
 * 
 * @author Administrator
 * 
 */
public class Topic extends Article {

	/** ��ͨ�� */
	public static final int TYPE_NORMAL = 0;

	/** ������ */
	public static final int TYPE_BEST = 1;

	/** �ö��� */
	public static final int TYPE_TOP = 2;

	private String title; // ����
	private int type; // ����

	private int replyCount; // �ظ�����
	private Date lastUpdateTime; // ������µķ���ʱ��
	private Reply lastReply; // ���ظ�

	private Forum forum;
	private Set<Reply> replies = new HashSet<Reply>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Reply getLastReply() {
		return lastReply;
	}

	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

}
