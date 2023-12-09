package servlet.cart;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Account;
import beans.Cart;
import beans.Item;
import beans.Order;
import beans.OrderDetail;
import dao.OrderDAO;
import mail.EmailSender;

@WebServlet("/checkout")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		Account user = (Account) request.getSession().getAttribute("loginedUser");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		String firstName = "";
		String lastName = "";
		String fullName = "";
		String email = "";
		String phone = "";
		String city = "";
		String state = "";
		String address = "";
		String fullAddress = "";
		String message = "";
		
		firstName = request.getParameter("fName");
		lastName = request.getParameter("lName");
		email = request.getParameter("email");
		phone = request.getParameter("phone");
		city = request.getParameter("city");
		state = request.getParameter("state");
		address = request.getParameter("address");
		message = request.getParameter("message");
		
		if (cart != null) {
			
			if (firstName == "" || lastName == "" || email == "" || phone == "" 
					|| city == "" || state == "" || address == "") { //fail
				
				request.setAttribute("checkOutMessage", "Vui lòng nhập đủ thông tin người nhận");
				request.getRequestDispatcher("/checkout.jsp").forward(request, response);
			} else {
				
				fullName = firstName + " " + lastName;
				fullAddress = city + "/ " + state + "/ " + address;
				
				//tạo order + set orderdetails
				Date date = new Date();
				Order order = null;
				
				if (user != null) {
					
					order = new Order(-1 , user, fullName, email, phone, fullAddress, message, new Timestamp(date.getTime()), cart.getCartTotalPrice() + cart.getCartTotalPrice() * 0.1);
				} else {
					
					order = new Order(-1 , null, fullName, email, phone, fullAddress, message, new Timestamp(date.getTime()), cart.getCartTotalPrice() + cart.getCartTotalPrice() * 0.1);
				}
				
				for (Item i : cart.getItems()) {
					
					OrderDetail orderDetail = new OrderDetail(i.getBook(), i.getQuantity(), i.getPrice());
					order.addDetails(orderDetail);
				}
				
				OrderDAO orderDAO = new OrderDAO();
				orderDAO.checkout(order);
				
				cart.clearItems(); //xoá sp khỏi giỏ
				
				//Mail
				ServletContext context = getServletContext();
				
				String host;
				String port;
				String mailUser;
				String mailPass;
				
		        host = context.getInitParameter("host");
		        port = context.getInitParameter("port");
		        mailUser = context.getInitParameter("user");
		        mailPass = context.getInitParameter("pass");
		        
		        //Gửi
		        String subject = "RING! - Bookstore your order information!";
		        String content = "<h1 style=\"font-family:Roboto; color:#63e399\">RING!</h1>\n"
		        		+ "<br><h2 style=\"font-family:Roboto; color:white; background-color:#63e399\">Đơn hàng RING! - Bookstore của bạn đã được đặt!</h2>\n"
		        		+ "<h3>Chi tiết đơn hàng:</h3>\n"
		        		+ "<p>Tên người nhận: <b>" + fullName + "</b></p>\n"
		        		+ "<p>SĐT người nhận: <b>" + phone + "</b></p>\n"
		        		+ "<p>Địa chỉ: <b>" + fullAddress + "</b></p>\n"
		        		+ "<br><p>Lời nhắn cho shipper: <b>" + message + "</b></p>\n"
		        		+ "<br><br><h3>Chi tiết sản phẩm:</h3>\n";
		        		
		        		for (OrderDetail o : order.getDetails()) {
		        			content += "<p>Tên sản phẩm: <b>" + o.getBook().getTitle() + "</b></p>\n"
		    		        		+ "<img src=" + o.getBook().getImage() + ">\n"
		    		        		+ "<p>Số lượng: <b>" + o.getQuantity() + "</b></p>\n"
		    		        		+ "<p>Thành tiền: <b>" + o.getBook().getPrice() + "đ </b></p>\n"
		    		        		+ "<br><br>\n";
		        		}
		        		
		        		content += "<h3 style=\"font-family:Roboto; color:red\">Thành tiền: "+ order.getTotal() + "đ </h3>";
		        try {
		        	EmailSender.sendEmail(host, port, mailUser, mailPass, email, subject,
		                    content);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				
				request.setAttribute("checkOutMessage", "Đặt hàng thành công");
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
		} else {
			
			response.sendRedirect("/cart.jsp"); //trả về trang cart
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
