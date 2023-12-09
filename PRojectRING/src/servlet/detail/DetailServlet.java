package servlet.detail;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.BookDetail;
import beans.Category;
import beans.Publisher;
import beans.Review;
import dao.BookDAO;
import dao.CategoryDAO;

@WebServlet("/details")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BookDAO bookDAO = new BookDAO();
		CategoryDAO cateDAO = new CategoryDAO();
		
		response.setContentType("text/html;charset=UTF-8");
		String bookID = request.getParameter("pid");
		String selectedPage = request.getParameter("selectedPage");
		
		List<Category> listCategory = cateDAO.getAllCategory();
		List<Publisher> listPublisher = cateDAO.getAllPublisher();
		Book book = bookDAO.getBookById(bookID);
		
		
		int page = 1;
		int total = 5;
		
		if (selectedPage != null) {
			
			page = Integer.parseInt(selectedPage); //trang hiện đang chọn
		}
		
		//phân trang
		int count = bookDAO.getTotalReviewByBook(bookID);
		int endPage = count / total;
		
		if (count % total != 0) {
			
			endPage++;
		}
		
		if (book != null) {
			
			List<Book> listRelate = bookDAO.getBookPagingByCategory(book.getCateId(), 1, 4);
			List<Book> listRecommend = bookDAO.getRecommendBook();
			List<Review> listReview = bookDAO.getReviewPagingByBook(bookID, page, total);
			BookDetail bookDetail = bookDAO.getBookDetailByBook(bookID);
			String bookPublisher = cateDAO.getPublisherName(book.getPublisher());
					
			request.setAttribute("currentPage", page);
			request.setAttribute("endPage", endPage);
			
			request.setAttribute("listCategory", listCategory);
			request.setAttribute("listPublisher", listPublisher);
			
			request.setAttribute("listRelate", listRelate);
			request.setAttribute("listRecommend", listRecommend);
			
			request.setAttribute("listReview", listReview);
			request.setAttribute("totalReview", count);
			
			request.setAttribute("book", book);
			request.setAttribute("bookDetail", bookDetail);
			request.setAttribute("bookPublisher", bookPublisher);
			
			request.getRequestDispatcher("/product-details.jsp").forward(request, response);
		} else {
			
			response.sendRedirect("404.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
