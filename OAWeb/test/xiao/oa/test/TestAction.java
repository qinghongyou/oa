package xiao.oa.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class TestAction extends ActionSupport {

	@Resource
	private TestService testService;

	public String execute() throws Exception {
		System.out.println("-------> TestAction.execute()");
		System.out.println("-------> testService = " + testService);
		testService.saveTwoUser();
		return "success";
	}

}
