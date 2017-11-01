package xiao.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import xiao.oa.domain.Department;


public class DepartmentUtils {

	/**
	 * �����������������еĲ��Ŷ��ĵ����ƺ�ŵ�ͬһ��List�з��ء�ͨ�������еĿո��ʾ���
	 * 
	 * @param topList
	 * @param removedDepartment
	 *            ������ź�������ŵ����ﲿ�Ŷ���Ҫ�����Ϊnull����ʾû��Ҫ�Ƴ��Ĳ��ŷ�֧
	 * @return
	 */
	public static List<Department> getAllDepartmentList(List<Department> topList, Department removedDepartment) {
		List<Department> list = new ArrayList<Department>();
		walkTree(topList, "��", list, removedDepartment);
		return list;
	}

	// �ݹ����
	private static void walkTree(Collection<Department> topList, String prefix, List<Department> list, Department removedDepartment) {
		for (Department top : topList) {
			// ȥ��ָ���Ĳ��ŷ�֧
			if (removedDepartment != null && top.getId().equals(removedDepartment.getId())) {
				continue;
			}

			// ����
			Department copy = new Department(); // ��Ҫ�޸�Session�����еĶ������ʹ�ø���
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			list.add(copy); // ע�⣬��ӵ���copy�Ķ���
			// ����
			walkTree(top.getChildren(), "��" + prefix, list, removedDepartment); // Ҫʹ��ȫ�ǵĿո�Ҫ��Ȼ��html��ֻ��ʾһ���ո�
		}
	}

}
