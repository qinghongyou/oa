package xiao.oa.domain;

import java.util.List;

/**
 * ��ҳ�õ�һҳ����Ϣ����
 * 
 * @author Administrator
 * 
 */
public class PageBean {

	// ���ݵĲ��������õ�ֵ
	private int currentPage; // ��ǰҳ
	private int pageSize; // ÿҳ��ʾ�ļ�¼��

	// ��ѯ���ݿ�
	private int recordCount; // �ܼ�¼��
	private List recordList; // ��ҳ�������б�

	// ���������
	private int pageCount; // ��ҳ��
	private int beginPageIndex; // ҳ���б�Ŀ�ʼ����
	private int endPageIndex; // ҳ���б�Ľ�������

	/**
	 * ֻ����ǰ4����Ҫ�����Ե�ֵ�����Զ��ļ������3�����Ե�ֵ
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param recordCount
	 * @param recordList
	 */
	public PageBean(int currentPage, int pageSize, int recordCount, List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;

		// ����pageCount
		pageCount = (recordCount + pageSize - 1) / pageSize;

		// ����begPageIndex��endPageIndex
		// a, ��ҳ��������10ҳ����ȫ����ʾ
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// b, ��ҳ��������10ҳ������ʾ��ǰҳ�����Ĺ�10��ҳ�루ǰ4�� + ��ǰҳ + ��5����
		else {
			// ��ʾ��ǰҳ�����Ĺ�10��ҳ�루ǰ4�� + ��ǰҳ + ��5����
			beginPageIndex = currentPage - 4; // 7 - 4 = 3
			endPageIndex = currentPage + 5; // 7 + 5 = 12
			// ��ǰ�治��4��ҳ��ʱ������ʾǰ10ҳ
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// �����治��5��ҳ��ʱ������ʾ��10ҳ
			else if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1; // ע������ʾ��ʱ���ǰ��������߽��
			}
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

}
