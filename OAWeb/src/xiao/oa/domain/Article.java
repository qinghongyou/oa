package xiao.oa.domain;

import java.util.Date;

/**
 * ʵ�壺����
 * 
 * @author Administrator
 * 
 */
public abstract class Article {
	private Long id;
	private String content; // ���ݣ�TEXT���ͣ�
	private Date postTime; // ����ʱ��
	private String ipAddr; // IP��ַ
	private User author; // ����

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
