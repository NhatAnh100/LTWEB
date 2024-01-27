package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Product;
import beans.Category;
import beans.Publisher;
import dao.ProductDAO;
import dao.CategoryDAO;

@WebServlet("/publisher")
public class ShowPublisher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDAO productDAO = new ProductDAO();
		CategoryDAO cateDAO = new CategoryDAO();
		
		response.setContentType("text/html;charset=UTF-8");
		String pubID = request.getParameter("pid");
		String selectedPage = request.getParameter("selectedPage");
		
		int page = 1;
		int total = 15;
		
		if (selectedPage != null) {
			
			page = Integer.parseInt(selectedPage); //trang hiện đang chọn
		}
		
		//phân trang
		int count = productDAO.getTotalproductByPublisher(pubID);
		int endPage = count / total;
		
		if (count % total != 0) {
			
			endPage++;
		}
		
		List<Product> listproduct = productDAO.getproductPagingByPublisher(pubID, page, total);
		List<Category> listCategory = cateDAO.getAllCategory();
		List<Publisher> listPublisher = cateDAO.getAllPublisher();
		String selectedPublisher = cateDAO.getPublisherName(pubID);
		
		request.setAttribute("currentPage", page);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listproduct", listproduct);
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("listPublisher", listPublisher);
		request.setAttribute("selectedPID", pubID);
		request.setAttribute("selectedPublisher", selectedPublisher);
		
		request.getRequestDispatcher("/shop.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
