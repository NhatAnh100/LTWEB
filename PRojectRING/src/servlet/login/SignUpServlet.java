package servlet.login;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Account;
import dao.AccountDAO;
import mail.EmailSender;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void sendSignUpMail(String username, String password, String email) {
		
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
        String subject = "RING! - Bookstore sign-up information!";
        String content = "<h1 style=\"font-family:Roboto; color:#63e399\">RING!</h1>\n"
        		+ "<br><h2 style=\"font-family:Roboto; color:white; background-color:#63e399; text-align:center\">Tài khoản RING! - Bookstore của bạn đã được đăng ký thành công!</h2>\n \n"
        		+ "<br><br><p>Tên tài khoản: <b>" + username + "</b></p>\n"
        		+ "<p>Mật khẩu: <b>" + password + "</b></p>";
 
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
		
		String username = "";
		String password = "";
		String repassword = "";
		String email = "";
	
		username = request.getParameter("userR").trim();
		password = request.getParameter("passR");
		repassword = request.getParameter("repassR");
		email = request.getParameter("emailR").trim();
		
		if (username != "" && password != "" && repassword != "" && email != "") {
			
			if (!password.equals(repassword)) { //khác mật khẩu
				
				request.setAttribute("signUpMessage", "Mật khẩu không trùng khớp");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				
				AccountDAO accountDAO = new AccountDAO();
				Account user = accountDAO.checkAccountIsExists(username);
				
				if (user == null) { //sign up
					
					accountDAO.signUp(username, password, email);
					sendSignUpMail(username, password, email);
					request.setAttribute("signUpSuccess", "Tạo tài khoản thành công!");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				} else {
					
					request.setAttribute("signUpMessage", "Tài khoản đã tồn tại");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}
		} else {
			
			request.setAttribute("signUpMessage", "Vui lòng nhập đầy đủ thông tin");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
