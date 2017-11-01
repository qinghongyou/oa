package xiao.oa.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import xiao.oa.domain.Forum;
import xiao.oa.domain.PageBean;
import xiao.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;

public class QueryHelperTest {
	/**
	 * 0 ��ʾȫ������<br>
	 * 1 ��ʾȫ��������
	 */
	private int viewType = 1;

	/**
	 * 0 ��ʾĬ������(�����ö�����ǰ�棬����������ʱ�併������)<br>
	 * 1 ��ʾֻ��������ʱ������<br>
	 * 2 ��ʾֻ�����ⷢ��ʱ������<br>
	 * 3 ��ʾֻ���ظ���������
	 */
	private int orderBy = 0;

	/**
	 * true��ʾ����<br>
	 * false��ʾ����
	 */
	private boolean asc = false;

	private Forum forum = new Forum();

	@Test
	public void testQueryHelper() {
		QueryHelper queryHelper = new QueryHelper(Topic.class, "t")//
				.addWhereCondition("t.forum=?", forum)//
				.addWhereCondition((viewType == 1), "t.type=?", Topic.TYPE_BEST) // 1 ��ʾֻ��������
				.addOrderByProperty((orderBy == 1), "t.lastUpdateTime", asc) // 1 ��ʾֻ��������ʱ������
				.addOrderByProperty((orderBy == 2), "t.postTime", asc) // ��ʾֻ�����ⷢ��ʱ������
				.addOrderByProperty((orderBy == 3), "t.replyCount", asc) // ��ʾֻ���ظ���������
				.addOrderByProperty((orderBy == 0), "(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)//
				.addOrderByProperty((orderBy == 0), "t.lastUpdateTime", false); // 0 ��ʾĬ������(�����ö�����ǰ�棬����������ʱ�併������)
		
		String listHql = queryHelper.getQueryListHql();
		String countHql = queryHelper.getQueryCountHql();
		List<Object> parameters = queryHelper.getParameters();

		System.out.println(listHql);
		System.out.println(countHql);
		System.out.println(parameters);
	}
}
