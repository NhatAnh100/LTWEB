package servlet.admin_management;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import dao.AccountDAO;

@WebServlet("/removeuser")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String rUserID = request.getParameter("removedUser"); //remove
		String rSelectedAll = request.getParameter("selectedAll");
		String[] rSelectedUserID = request.getParameterValues("selectedUser");
		String currentURL = request.getHeader("referer");
		
		HttpSession session = request.getSession();
		Account user = (Account) session.getAttribute("loginedUser");
		
		if (user != null && user.getIsAdmin() == 1) {
				
			AccountDAO accountDAO = new AccountDAO();
			
			//Hành động
			if (rUserID != null) { //xoá tài khoản
				
				accountDAO.deleteAccount(rUserID);
				session.setAttribute("managerAccountMessage", "Đã xoá tài khoản khỏi danh sách");
				
			} else if (request.getParameter("rSelectedUserBtn") != null) { //Xoá nhiều tài khoản
				
				if (rSelectedUserID != null) {
					
					for (int i = 0; i < rSelectedUserID.length; i++) {

					    if (rSelectedUserID[i] != null) {
					    	
				    		accountDAO.deleteAccount(rSelectedUserID[i]);
				    		session.setAttribute("managerAccountMessage", "Đã xoá các tài khoản khỏi danh sách");
						}
				    }
				}
			} else if (rSelectedAll != null && Integer.parseInt(rSelectedAll) == 1) { //Xoá tất cả
				
				accountDAO.deleteAllAccount();
				session.removeAttribute("loginedUser");
				response.sendRedirect("home");
				return;
			}
			
			response.sendRedirect(currentURL);
		} else {
			
			response.sendRedirect("home");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
