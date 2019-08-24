package cn.edu.bit.sms.system.dto;

import cn.edu.bit.sms.system.constant.CheckStatus;

public class CheckTreeNode extends TreeNode {
	
	private boolean checked;
	
	private CheckStatus checkStatus;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public CheckStatus getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(CheckStatus checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	
}
