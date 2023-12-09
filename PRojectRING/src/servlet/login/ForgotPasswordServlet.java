package servlet.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Account;
import dao.AccountDAO;
import mail.EmailSender;

@WebServlet("/forgot")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void sendForgotMail(List<Account> list, String email) {
		
		String host;
		String port;
		String mailUser;
		String mailPass;
		
		//Đọc dữ liệu mail
		ServletContext context = getServletContext();
		
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        mailUser = context.getInitParameter("user");
        mailPass = context.getInitParameter("pass");
        
        //Gửi
        String subject = "RING! - Bookstore password recovery!";
        String content = "<h1 style=\"font-family:Roboto; color:#63e399\">RING!</h1>\n"
        		+ "<br><h2 style=\"font-family:Roboto; color:white; background-color:#63e399; text-align:center\">Mật khẩu RING! - Bookstore của bạn!</h2>\n \n";
        		
        		for (Account account : list) {
        		
        			content += "<br><br><p>Tên tài khoản: <b>" + account.getUsername() + "</b></p>\n"
        	        		+ "<p>Mật khẩu: <b>" + account.getPassword() + "</b></p>";
        		}
        		
 
        try {
        	EmailSender.sendEmail(host, port, mailUser, mailPass, email, subject,
                    content);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String email = "";
	
		email = request.getParameter("email").trim();
		
		if (email != "") {
			
			AccountDAO accountDAO = new AccountDAO();
			List<Account> listAcc = accountDAO.getAccountByEmail(email);
			
			if (listAcc == null || listAcc.size() == 0) { 
				
				request.setAttribute("forgotMessage", "Tài khoản không tồn tại!");
				request.getRequestDispatcher("/forgot.jsp").forward(request, response);
			} else {
				
				sendForgotMail(listAcc, email);
				request.setAttribute("loginSuccess", "Mật khẩu đã được gửi về email của bạn!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} else {
			
			request.setAttribute("forgotMessage", "Vui lòng nhập email");
			request.getRequestDispatcher("/forgot.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
