package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mail.EmailSender;

@WebServlet("/emailtest")
public class EmailTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void sendTestEmail(String email) {
		
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
        String subject = "RING! - Bookstore advertisement email!";
        String content = "<h1 style=\"font-family:Roboto; color:#63e399\">RING!</h1>\n"
        		+ "<br><h2 style=\"font-family:Roboto; color:white; background-color:#63e399; text-align:center\">Tài khoản RING! - Bookstore của bạn đã đăng ký nhận tin!</h2>\n \n"
        		+ "<br><br><p>Bạn đã đăng ký nhận tin nhắn quảng cáo của web RING! - Bookstore</p>";
 
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
			
			sendTestEmail(email);
		} else {
			
		}
		
		response.sendRedirect(request.getHeader("referer"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
