package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Book;
import beans.Cart;
import beans.Category;
import beans.Publisher;
import dao.BookDAO;
import dao.CategoryDAO;

@WebServlet("/home")
public class HomeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		//b1: lấy data trong DAO
		BookDAO bookDAO = new BookDAO();
		CategoryDAO cateDAO = new CategoryDAO();
		
		//Tạo giỏ khi load web
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		if (cart == null) { //tạo giỏ mới khi chưa có
			
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
//		List<Book> listBook = dao.getAllBook();
		List<Book> listBook = bookDAO.getAllBookPaging(1, 6);
		List<Category> listCategory = cateDAO.getAllCategory();
		List<Category> listRandomCategory = cateDAO.getRandomCategory();
		List<Publisher> listPublisher = cateDAO.getAllPublisher();
		List<Book> listRandom = bookDAO.getRandomBook();
		List<Book> listRecommend = bookDAO.getRecommendBook(); //Not yet
		
		//b2: set data vào jsp
		request.setAttribute("listBook", listBook);
		request.setAttribute("listRandom", listRandom);
		request.setAttribute("listRecommend", listRecommend);
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("listRandomCategory", listRandomCategory);
		request.setAttribute("listPublisher", listPublisher);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
