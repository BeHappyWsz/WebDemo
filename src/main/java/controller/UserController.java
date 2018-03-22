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
 * 用户操作
 * @author Administrator
 *
 */
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("action");
		if("add".equals(param)) {//添加保存
			addUser(request,response);
		}else if("addForm".equals(param)){//添加页面
			request.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
		}else if("delete".equals(param)) {//直接删除
			deleteUser(request,response);
		}else if("update".equals(param)) {//更新保存密码
			update(request,response);
		}else if("updateForm".equals(param)){//更新页面
			String uid = request.getParameter("id");
			List<User> user = userService.findUserById(Integer.valueOf(uid));
			if(user.isEmpty() || user.size() >1) {
				request.setAttribute("error", "用户查询失败");
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
			}else {
				request.setAttribute("userMsg", user.get(0));
				request.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
			}
		}else if("show".equals(param)) {
			request.setAttribute("userList", userService.getAllUser());
			request.getRequestDispatcher("/WEB-INF/jsp/show.jsp").forward(request, response);
		}else if("search".equals(param)) {
			search(request,response);
		}else if("logout".equals(param)) {
			request.getSession().invalidate();
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	private UserService userService = new UserServiceImpl();
	
	public void search(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		try {
			if("".equals(username) || null == username) {//查询所有
				response.sendRedirect("/WebDemo/UserController?action=show");
			}else {
				List<User> list = userService.findUserByName(username);
				request.setAttribute("userList", list);
				request.getRequestDispatcher("/WEB-INF/jsp/show.jsp").forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void addUser(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rpassword = request.getParameter("rpassword");
		try {
			if(username.length() <6) {
				request.setAttribute("error", "用户名长度不能小于6");
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
			}
			if(!password.equals(rpassword)) {
				request.setAttribute("error", "密码不一致");
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
			}
			boolean flag = userService.createUser(new User(username,password));
			if(flag) {
				response.sendRedirect("/WebDemo/UserController?action=show");
			}else {
				request.setAttribute("error", "新增用户失败");
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user.getId() == id) {
				request.setAttribute("error", "不可删除当前用户");
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
			}else {
				boolean flag = userService.deleteUser(id);
				if(flag) {//删除成功
					response.sendRedirect("/WebDemo/UserController?action=show");
				}else {
					request.setAttribute("error", "删除失败");
					request.getRequestDispatcher("/fail.jsp").forward(request, response);
				}
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 只更新用户密码
	 * @param request
	 * @param response
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) {
		String uid = request.getParameter("uid");
		String password = request.getParameter("password");
		String rpassword = request.getParameter("rpassword");
		try {
			if(!password.equals(rpassword)) {
				request.setAttribute("error", "两次密码不一致");
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
			}
			boolean flag = userService.updatePwd(Integer.valueOf(uid), rpassword);
			if(flag) {
				User user = (User) request.getSession().getAttribute("user");
				if(uid.equals(String.valueOf(user.getId()))) {//修改当前用户的密码，重新登录
					request.getSession().invalidate();
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}else {
					response.sendRedirect("/WebDemo/UserController?action=show");
				}
			}else {
				request.setAttribute("error", "更新失败");
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
