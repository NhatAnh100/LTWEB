package servlet.admin_management;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import beans.Book;
import beans.Category;
import beans.Publisher;
import dao.BookDAO;
import dao.CategoryDAO;

@WebServlet("/addproduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Account user = (Account) session.getAttribute("loginedUser");
		int seller = user.getId();
		
		
		if (user != null && (user.getIsSeller() == 1 || user.getIsAdmin() == 1)) {
			
			if (request.getParameter("addBtn") != null) { // nhấn xác nhận
				
				//required
				String title = "";
				String image = "";
				String price = "";
				String quantity = "";
				String author = "";
				String description = "";
				String type = "";
				String publisher = "";
				String category = "";
				
				//optional
				String weight = "";
				String size = "";
				String pages = "";
				String date = "";
				String language = "";
				
				title = request.getParameter("title");
				image = request.getParameter("image");
				price = request.getParameter("price");
				quantity = request.getParameter("quantity");
				author = request.getParameter("author");
				description = request.getParameter("description");
				type = request.getParameter("type");
				publisher = request.getParameter("publisher");
				category = request.getParameter("category");
				
				weight = request.getParameter("weight");
				size = request.getParameter("size");
				pages = request.getParameter("pages");
				date = request.getParameter("date");
				language = request.getParameter("language");
				
				if (title == "" || image == "" || price == "" || quantity == "" || author == "" || description == ""
						|| type == null || publisher == null || category == null) {
					
					response.sendRedirect("/manager-add-product.jsp");
				} else {
					
					//book
					Book book = new Book(-1 ,image, Double.parseDouble(price), Integer.parseInt(quantity), title, description, type, author, publisher, category);
					BookDAO bookDAO = new BookDAO();
					CategoryDAO cateDAO = new CategoryDAO();
					
					//bookdetail
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date newDate = null;
					Timestamp time = null;
						
					if (date != "") { //parse date
						
						try {
							
							newDate = (Date) sdf.parse(date);
							time = new Timestamp(newDate.getTime());
						} catch (Exception e) {
							
							e.printStackTrace();
						}
					} 
					
					if (language == null) language = "Tiếng Việt"; //set default tiếng việt
					
					List<Category> listCategory = cateDAO.getAllCategory();
					List<Publisher> listPublisher = cateDAO.getAllPublisher();
					bookDAO.addBook(book, weight, size, pages, time, language, seller);
					request.setAttribute("managerMessage", "Đã thêm vào danh sách sản phẩm");
					request.setAttribute("listCategory", listCategory);
					request.setAttribute("listPublisher", listPublisher);
					request.getRequestDispatcher("/manager-add-product.jsp").forward(request, response);
				}
			} else { //chuyển trang add
				
				CategoryDAO cateDAO = new CategoryDAO();
				
				List<Category> listCategory = cateDAO.getAllCategory();
				List<Publisher> listPublisher = cateDAO.getAllPublisher();
				
				request.setAttribute("listCategory", listCategory);
				request.setAttribute("listPublisher", listPublisher);
				request.getRequestDispatcher("/manager-add-product.jsp").forward(request, response);
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
