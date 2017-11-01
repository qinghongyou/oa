package xiao.oa.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class TestLog {
	
	private Log log =LogFactory.getLog(TestLog.class);
	
	@Test
	public void testLog() throws Exception {
		log.debug("这是debug级别！");
		log.info("这是info级别！");
		log.warn("这是warn级别！");
		log.error("这是error级别！");
		log.fatal("这是fatal级别！");
	}

}
