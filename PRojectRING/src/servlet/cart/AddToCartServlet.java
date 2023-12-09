package servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Book;
import beans.Cart;
import beans.Item;
import dao.BookDAO;

@WebServlet("/addtocart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BookDAO dao = new BookDAO();
		
		response.setContentType("text/html;charset=UTF-8");
		
		String bookID = request.getParameter("pid");
		String currentURL = request.getHeader("referer");
		
		HttpSession session = request.getSession();
		
		int quantity = 1; // mặc định 1
		
		if (bookID != null) {
			Book book = dao.getBookById(bookID);
			
			if (book != null) {
				
				if (request.getParameter("pquantity") != null) { //thêm nhiều sách
					
					quantity = Integer.parseInt(request.getParameter("pquantity"));
				}
				
				if (session.getAttribute("cart") == null) { //chưa có giỏ hàng
					
					Cart cart = new Cart();
					List<Item> list = new ArrayList<Item>();
					Item item = new Item(book.getId() , book, quantity, book.getPrice());
					list.add(item);
					cart.setItems(list);
					
					cart.updateStatus();
					session.setAttribute("cart", cart);
				} else { //có giỏ rồi
					
					Cart cart = (Cart) session.getAttribute("cart"); //Lấy giỏ từ session
					List<Item> list = cart.getItems();
					boolean isExist = false; //Bắt đầu kiểm tra sản phẩm đang thêm có trong giỏ chưa
					
					for (Item i : list) {
						
						if (i.getId() == book.getId()) {
							
							i.setQuantity(i.getQuantity() + quantity);
							isExist = true;
						}
					}
					
					if (!isExist) {
						
						Item item = new Item(book.getId() , book, quantity, book.getPrice());
						list.add(item);
					}
					
					cart.updateStatus();
					session.setAttribute("cart", cart);
					
					//Trả về json status ajax
					PrintWriter out = response.getWriter();
					Gson gson = new Gson();
					String type = request.getParameter("type");
					if (type.equalsIgnoreCase("ajax")) {
						out.print(gson.toJson(cart.getStatus()));
						out.flush();
						out.close();
					} else {
						//Không dùng ajax
						session.setAttribute("addedBook", bookID);
						response.sendRedirect(currentURL);
					}
				}
			}
			
//			session.setAttribute("addedBook", bookID);
//			response.sendRedirect(currentURL);
			
		} else {
			
			response.sendRedirect("404.html");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
