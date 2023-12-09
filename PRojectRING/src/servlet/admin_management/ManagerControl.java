package servlet.admin_management;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import beans.Book;
import dao.BookDAO;

@WebServlet("/manager")
public class ManagerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Account user = (Account) session.getAttribute("loginedUser");
		
		if (user != null && user.getIsSeller() == 1) {
			
			BookDAO bookDAO = new BookDAO();
			int userId = user.getId();
			List<Book> listBook = bookDAO.getAllBookBySeller(userId);
			
			request.setAttribute("listBook", listBook);
			request.getRequestDispatcher("/manager.jsp").forward(request, response);
		} else {
			
			response.sendRedirect("home");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
