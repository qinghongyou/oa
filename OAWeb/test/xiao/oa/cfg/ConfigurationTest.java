package xiao.oa.cfg;

import org.junit.Test;

import xiao.oa.cfg.Configuration;

public class ConfigurationTest {

	@Test
	public void testGetPageSize() {
		int pageSize = Configuration.getPageSize();
		System.out.println("pageSize = " + pageSize);
	}

}
