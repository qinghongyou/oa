package xiao.oa.action;

import xiao.oa.base.BaseAction;

public class SystemAction extends BaseAction {
	
	/** 系统信息 */
	public String list() throws Exception {
		return "list";
	}
	
	/** 联系我们 */
	public String link() throws Exception {
		return "link";
	}
	
	/** 版权所有 */
	public String copyright() throws Exception {
		return "copyright";
	}

}
