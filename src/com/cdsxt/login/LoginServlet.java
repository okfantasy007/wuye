package com.cdsxt.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String opt = req.getParameter("opt");
		if (opt.equals("login")) {
			login(req, resp);
		}else if (opt.equals("logout")) {
			logout(req,resp);
		}		
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("user");
		resp.sendRedirect("login.jsp");
		return;
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		User user = new LoginDao().find(uname, pwd);
		if (null != user) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			System.out.println("========分=========隔========线========");
			req.getRequestDispatcher("main.jsp").forward(req, resp);
			return;
		}else{
			resp.sendRedirect("login/login.jsp");
			return;
		}
	}	
}
	
