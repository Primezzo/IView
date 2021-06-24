package com.yuanlrc.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuanlrc.model.Jifen;
import com.yuanlrc.model.PageBean;
import com.yuanlrc.model.User;
import com.yuanlrc.service.JifenService;
import com.yuanlrc.service.UserService;
import com.yuanlrc.tool.PublicStatic;
import com.yuanlrc.tool.Tool;

/*
 * 发帖页面
 * 
 */
@Controller
@RequestMapping("/user/jifen")
public class JifenContriller {
	
	@Autowired
	JifenService jifenService;
	@Autowired
	UserService userService;
	
	//跳转发帖页面
	@RequestMapping("index.do")
	public String findAllWhere(HttpServletRequest request, Model model,Jifen jifen,PageBean<Jifen> page) {
		User sessionuser=(User)request.getSession().getAttribute(PublicStatic.USER);
		jifen.setUserid(sessionuser.getId());
		page = jifenService.findpage(jifen, page);
		model.addAttribute("page", page);
		model.addAttribute("jifen", jifen);
		
		sessionuser = userService.findbyid(sessionuser);
		request.getSession().setAttribute(PublicStatic.USER,sessionuser);
		if(Tool.ismobile(request)){
			return "jsp/mobile/user/user-jifen";
		}else{
			return "jsp/index/user/user-jifen";
		}
	}
	
	
	
	
}
