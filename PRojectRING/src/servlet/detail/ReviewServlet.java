package servlet.detail;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import beans.Review;
import dao.BookDAO;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String currentURL = request.getHeader("referer");
		
		int userId = -1;
		String username = "";
		int bookId = -1;
		String content = "";
		int rating = -1;
		Account user = (Account) session.getAttribute("loginedUser");
		
		if (user != null) {
			
			userId = user.getId();
			username = user.getUsername();
			bookId = Integer.parseInt(request.getParameter("pid"));
			content = request.getParameter("content");
			rating = Integer.parseInt(request.getParameter("rating"));
			
			if (bookId != -1 && content != "") {
				
				Review temp = new Review(-1, userId, username, bookId, content, rating);
				BookDAO bookDAO = new BookDAO();
				bookDAO.review(temp);
				
				response.sendRedirect(currentURL);
			} else {
				
				response.sendRedirect(currentURL);
			}
		} else {
			
			response.sendRedirect(currentURL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
