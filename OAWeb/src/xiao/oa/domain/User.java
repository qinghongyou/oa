package xiao.oa.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

/**
 * �û�
 * 
 * @author Administrator
 * 
 */
public class User implements java.io.Serializable {
	private Long id;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();

	private String loginName; // ��¼��
	private String password; // ����
	private String name; // ��ʵ����
	private String gender; // �Ա�
	private String phoneNumber; // �绰����
	private String email; // �����ʼ�
	private String description; // ˵��

	/**
	 * �жϵ�ǰ�û��Ƿ���ָ�����Ƶ�Ȩ��
	 * 
	 * @param privName
	 *            Ȩ�޵�����
	 */
	public boolean hasPrivilegeByName(String privName) {
		// ����ǳ�������Ա���������е�Ȩ��
		if (isAdmin()) {
			return true;
		}

		// �������ͨ�û�������Ҫ�ж�Ȩ����
		for (Role role : roles) {
			for (Privilege p : role.getPrivileges()) {
				if (p.getName().equals(privName)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * �жϵ�ǰ�û��Ƿ���ָ��URL��Ȩ��
	 * 
	 * @param privUrl
	 *            Ȩ�޵�URL
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privUrl) {
		// ����ǳ�������Ա���������е�Ȩ��
		if (isAdmin()) {
			return true;
		}

		// a, ȥ������Ĳ����ַ���������У�
		int pos = privUrl.indexOf("?");
		if (pos > -1) {
			privUrl = privUrl.substring(0, pos);
		}
		// b, ȥ�������UI��׺������У�
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}

		// �������ͨ�û�������Ҫ�ж�Ȩ����
		// a, ������URL�ǲ���Ҫ���ƵĹ��ܣ���¼�����ֱ��ʹ�õģ�������Ӧֱ�ӷ���true
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		}
		// b, ������URL����Ҫ���ƵĹ��ܣ���¼�󻹵��ж�Ӧ��Ȩ�޲���ʹ�õģ�������Ӧ�ж�Ȩ��
		else {
			for (Role role : roles) {
				for (Privilege p : role.getPrivileges()) {
					if (privUrl.equals(p.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * �жϵ�ǰ�û��Ƿ��ǳ�������Ա
	 */
	public boolean isAdmin() {
		return "admin".equals(loginName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
