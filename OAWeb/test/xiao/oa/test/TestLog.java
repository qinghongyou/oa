package xiao.oa.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class TestLog {
	
	private Log log =LogFactory.getLog(TestLog.class);
	
	@Test
	public void testLog() throws Exception {
		log.debug("����debug����");
		log.info("����info����");
		log.warn("����warn����");
		log.error("����error����");
		log.fatal("����fatal����");
	}

}
