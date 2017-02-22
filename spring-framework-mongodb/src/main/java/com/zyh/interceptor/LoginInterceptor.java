package com.zyh.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.model.User;
/**
 * 登录的拦截器未登录会被强制跳转到登录页面
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}
	/**
	 * 之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		List<String> accept=new ArrayList<String>();
		accept.add("/");
		accept.add("/login.do");
		String url = request.getRequestURI().substring(request.getContextPath().length());
		User user=(User) request.getSession().getAttribute("user");
		if(!accept.contains(url)){
			if(user==null){
				response.sendRedirect("/");
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}

}
