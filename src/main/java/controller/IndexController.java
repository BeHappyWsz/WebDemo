package controller;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 首页
 * @author Administrator
 */
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		request.setAttribute("list", list);
		request.setAttribute("a", "aaaaaaaaaaa");
		
		String param = (String)request.getParameter("param");
		System.out.println(param);
		if("forward".equals(param)) {
			request.getRequestDispatcher("/WEB-INF/jsp/show.jsp").forward(request, response);
		}else {
			response.sendRedirect("http://www.baidu.com");
//			response.setStatus(response.SC_MOVED_TEMPORARILY);
//			response.setHeader("Location", "http://www.baidu.com");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
