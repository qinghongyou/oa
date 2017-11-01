package xiao.oa.cfg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ����������Ϣ�ģ���ȡ�����ļ���
 * 
 * @author Administrator
 * 
 */
public class Configuration {

	private static int pageSize = 10;

	static {
		InputStream in = null;
		try {
			
			// ����default.properties�����ļ�
			Properties props = new Properties();
			in = Configuration.class.getClassLoader().getResourceAsStream("default.properties");
			props.load(in);

			// ��ȡ���õ�ֵ
			pageSize = Integer.parseInt(props.getProperty("pageSize"));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static int getPageSize() {
		return pageSize;
	}

	public static void setPageSize(int pageSize) {
		Configuration.pageSize = pageSize;
	}

}
