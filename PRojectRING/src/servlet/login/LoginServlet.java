package servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import dao.AccountDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		AccountDAO accountDAO = new AccountDAO();
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		
		Account user = accountDAO.login(username, password);
		
		if (user == null) { //fail
			
			request.setAttribute("loginMessage", "Sai tài khoản hoặc mật khẩu");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			
			HttpSession session = request.getSession();
			session.setAttribute("loginedUser", user);
			response.sendRedirect("home");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
