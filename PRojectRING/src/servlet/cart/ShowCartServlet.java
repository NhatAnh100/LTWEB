package servlet.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cart;

@WebServlet("/cart")
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		if (cart == null) { //tạo giỏ mới khi chưa có
			
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		String rBookID = request.getParameter("removedBook"); //remove
		String dBookID = request.getParameter("decreasedBook"); //decrease
		String iBookID = request.getParameter("increasedBook"); //increase
		String clearCart = request.getParameter("clearCart"); //clear
		String itemQuantity = request.getParameter("itemQuantity");
		String cBookID = request.getParameter("changedBook"); //change quantity
		
		if (cBookID != null && itemQuantity != null) {
			
			try {
				
				int newQuantity = Integer.parseInt(itemQuantity);
				
				if (newQuantity > 0) {
					
					cart.changeItemQuantity(Integer.parseInt(cBookID), newQuantity);
				}
			} catch (Exception e) {
				
			}
		} if (dBookID != null) {
			
			cart.decreaseItem(Integer.parseInt(dBookID));
		} else if (iBookID != null) {
			
			cart.increaseItem(Integer.parseInt(iBookID));
		} else if (rBookID != null) {
			
			cart.removeItem(Integer.parseInt(rBookID));
			request.setAttribute("cartMessage", "Đã gỡ sản phẩm khỏi giỏ hàng");
		} else if (clearCart != null) {
			
			cart.clearItems();
			request.setAttribute("cartMessage", "Đã gỡ tất cả sản phẩm khỏi giỏ hàng");
		}
		
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
