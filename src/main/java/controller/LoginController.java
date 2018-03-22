package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.UserService;
import service.imple.UserServiceImpl;

/**
 * 登录
 * @author wsz
 */
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private UserService userService = new UserServiceImpl();
	
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = userService.login(new User(username,password));
		if(null != user) {
			request.getSession().setAttribute("user", user);
			List<User> all = userService.getAllUser();
			request.setAttribute("userList", all);
			request.getRequestDispatcher("/WEB-INF/jsp/show.jsp").forward(request, response);
		}else {
			request.setAttribute("error", "登录失败");
			request.getRequestDispatcher("/fail.jsp").forward(request, response);
		}
	}

}
