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
import beans.OrderDetail;
import dao.AccountDAO;
import dao.BookDAO;
import dao.OrderDAO;

@WebServlet("/admin")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		//b1: lấy data trong DAO
		BookDAO bookDAO = new BookDAO();
		AccountDAO accountDAO = new AccountDAO();
		OrderDAO orderDAO = new OrderDAO();
		
		String isChart = request.getParameter("isChart");
		String isTable = request.getParameter("isTable");
		
		HttpSession session = request.getSession();
		Account user = (Account) session.getAttribute("loginedUser");
		
		if (user != null && user.getIsAdmin() == 1) {
			
			if (isChart != null && Integer.parseInt(isChart) == 1) { //Chart
				
				List<Account> listAccount = accountDAO.getTop5Seller();
				List<OrderDetail> listOrderByMonths = orderDAO.getOrderByMonths();
				List<OrderDetail> listOrderByBooks = orderDAO.getOrderByBooks();
				
				request.setAttribute("listAccount", listAccount);
				request.setAttribute("listOrderByMonths", listOrderByMonths);
				request.setAttribute("listOrderByBooks", listOrderByBooks);
				request.getRequestDispatcher("/chart.jsp").forward(request, response);
			} else if (isTable != null && Integer.parseInt(isTable) == 1) { //Table

				List<Book> listBook = bookDAO.getAllBook();
				List<Account> listAccount = accountDAO.getAllAccount();
				
				request.setAttribute("listBook", listBook);
				request.setAttribute("listAccount", listAccount);
				request.getRequestDispatcher("/table.jsp").forward(request, response);
			} else { //Dashboard
				
				List<Book> listBook = bookDAO.getAllBook();
				List<Account> listAccount = accountDAO.getAllAccount();
				List<OrderDetail> listOrderByMonths = orderDAO.getOrderByMonths();
				List<OrderDetail> listOrderByBooks = orderDAO.getOrderByBooks();
				
				//b2: set data vào jsp
				request.setAttribute("listBook", listBook);
				request.setAttribute("listAccount", listAccount);
				request.setAttribute("listOrderByMonths", listOrderByMonths);
				request.setAttribute("listOrderByBooks", listOrderByBooks);
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
			}
			
		} else {
			
			response.sendRedirect("home");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
