package cn.edu.bit.sms.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.bit.sms.common.action.BaseServiceAction;
import cn.edu.bit.sms.common.annotation.Comment;
import cn.edu.bit.sms.common.http.SessionUser;
import cn.edu.bit.sms.common.http.SessionUtil;
import cn.edu.bit.sms.common.vo.res.MessageResult;
import cn.edu.bit.sms.system.entity.User;
import cn.edu.bit.sms.system.service.UserService;
import cn.edu.bit.sms.system.vo.req.LoginParam;
import cn.edu.bit.sms.system.vo.req.UpdatePasswordParam;

@RequestMapping("/system/user")
@RestController
@Comment("老师信息")
public class UserAction extends BaseServiceAction<User, UserService> {
	
	@RequestMapping("/login")
	@Comment("登录")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @Valid LoginParam param, 
			BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/system/user/reLogin");
			request.getSession().setAttribute("error", "1");
			return view;
		}
		User user = super.service.login(param.getUsername());
		if (user == null || !user.getPassword().equals(param.getPassword())) {
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/system/user/reLogin");
			request.getSession().setAttribute("error", "2");
			return view;
		}
		SessionUser sessionUser = new SessionUser();
		sessionUser.setUserId(user.getId());
		sessionUser.setName(user.getName());
		sessionUser.setUsername(user.getUsername());
		sessionUser.setRole(user.getRole());
		SessionUtil.setCurrentUser(request.getSession(), sessionUser);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/admin/admin.jsp");
		return view;
	}
	
	@RequestMapping("/reLogin")
	@Comment("重新登录")
	public ModelAndView reLogin(String error) {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/admin/login.jsp");
		return view;
	}
	
	@RequestMapping("/logOut")
	@Comment("登出")
	public ModelAndView logOut(HttpServletRequest request) {
		SessionUtil.removeCurrentUser(request.getSession());
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/system/user/reLogin");
		return view;
	}
	
	@RequestMapping("/getName")
	@Comment("获取用户名重新登录")
	public MessageResult<String> getName(HttpServletRequest request) {
		SessionUser sessionUser = SessionUtil.getCurrentUser(request.getSession());
		String name = sessionUser == null ? "" :sessionUser.getName();
		return new MessageResult<String>(name);
	}
	
	@RequestMapping("/getUsername")
	@Comment("获取用户名重新登录")
	public MessageResult<String> getUsername(HttpServletRequest request) {
		SessionUser sessionUser = SessionUtil.getCurrentUser(request.getSession());
		String username = sessionUser == null ? "" :sessionUser.getUsername();
		return new MessageResult<String>(username);
	}
	
	@RequestMapping("/updatePassword")
	@Comment("修改密码")
	public MessageResult<Void> updatePassword(HttpServletRequest request, UpdatePasswordParam param) {
		SessionUser sessionUser = SessionUtil.getCurrentUser(request.getSession());
		super.service.updatePassword(sessionUser.getUsername(), param.getPassword(), param.getPassword2());
		return new MessageResult<Void>();
	} 

}
