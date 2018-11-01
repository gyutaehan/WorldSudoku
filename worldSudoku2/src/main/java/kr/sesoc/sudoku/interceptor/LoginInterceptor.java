package kr.sesoc.sudoku.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("interceptor...afterCompletion..call....");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("interceptor...afterConcurrentHandlingStarted..call....");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("interceptor...postHandle..call....");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("interceptor...preHandle..call....");
		
		HttpSession httpSession = request.getSession();
		String userId = (String)httpSession.getAttribute("userId");
		
		if(userId != null) {
			return true;
		}else {
			httpSession.setAttribute("msg", "로그인이 필요한 서비스입니다.");
			response.sendRedirect("loginForm");
			return false;
		}
	}
	
	

}
