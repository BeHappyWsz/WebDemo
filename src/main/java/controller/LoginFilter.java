package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;

/**
* 登录拦截
*@author  wsz
*@createdTime 2018年3月20日
*/
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;  
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        
        User user = (User) request.getSession().getAttribute("user");  
        int aa = request.getRequestURI().lastIndexOf("/")+1;
        String action = request.getRequestURI().substring(aa);
        if(!"LoginController".equals(action)) {
            if(null == user ) {
//            	response.sendRedirect("/WebDemo/WebContent/index.jsp");
            	request.getRequestDispatcher("/index.jsp").forward(request, response);
            }else {
            	filterChain.doFilter(request, response);
            }
        }else {
        	filterChain.doFilter(request, response);
        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
