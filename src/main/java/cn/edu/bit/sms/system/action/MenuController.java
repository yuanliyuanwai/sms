package cn.edu.bit.sms.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bit.sms.common.action.BaseAction;
import cn.edu.bit.sms.common.http.SessionUser;
import cn.edu.bit.sms.common.http.SessionUtil;
import cn.edu.bit.sms.common.vo.res.MessageResult;
import cn.edu.bit.sms.system.dto.TreeNode;
import cn.edu.bit.sms.system.service.MenuService;
import cn.edu.bit.sms.system.vo.req.SystemMenuParam;

@RequestMapping("/system/menu")
@RestController
public class MenuController extends BaseAction {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/findByParentId")
	public MessageResult<List<TreeNode>> findByParentId(HttpServletRequest request, SystemMenuParam param) {
		SessionUser sessionUser = SessionUtil.getCurrentUser(request.getSession());
		List<TreeNode> menuNodes = menuService.findByParentId(param.getParentId(), sessionUser == null ? null : sessionUser.getRole());
		return new MessageResult<List<TreeNode>>(menuNodes);
	}

}
