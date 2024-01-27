package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Product;
import dao.ProductDAO;

@WebServlet("/loadmore")
public class LoadMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		ProductDAO productDAO = new ProductDAO();
		
		String cId = request.getParameter("cateId");
		int amount = Integer.parseInt(request.getParameter("total"));
		
		if (cId != null) { //load random
			List<Product> listproduct = productDAO.getNextproductByCategory(cId, amount);
			PrintWriter out = response.getWriter();
			for (Product product : listproduct) {
				out.println("<div class=\"col-sm-3 product-random-" + cId +"\">\r\n" + 
							"	<div class=\"product-image-wrapper\">\r\n" + 
							"		<div class=\"single-products\">\r\n" + 
							"			<div class=\"productinfo text-center\">\r\n" + 
							"				<a href = \"details?pid="+ product.getId() +"\"><img src=\"" + product.getImage() +"\" alt=\"\" /></a>\r\n" + 
							"				<h2>" + product.getPrice() + "đ</h2>\r\n" + 
							"				<p><a href = \"details?pid=" + product.getId() + "\">" + product.getTitle() + "</a></p>\r\n" + 
							"				<button type=\"button\" onclick=\"addToCart(" + product.getId() + ", 1)\" class=\"btn btn-default add-to-cart\"><i class=\"fa fa-shopping-cart\"></i>Thêm vào giỏ</button>\r\n" + 
							"			</div>\r\n" + 
							"		</div>\r\n" + 
							"	</div>\r\n" + 
							"</div>");
			}
		} else { //load sản phẩm
			List<Product> listproduct = productDAO.getNextproduct(amount);
			PrintWriter out = response.getWriter();
			for (Product product : listproduct) {
				
				String content = "<div class=\"col-sm-4 product-show\">\r\n" + 
						"									<div class=\"product-image-wrapper\">\r\n" + 
						"										<div class=\"single-products\">\r\n" + 
						"											<div class=\"productinfo text-center\">\r\n" + 
						"												<a href = \"details?pid=" + product.getId() + "\"><img src=\"" + product.getImage() + "\" alt=\"\" /></a>\r\n" + 
						"												<h2>" + product.getPrice() + " đ</h2>\r\n" + 
						"												<p><a href = \"details?pid=" + product.getId() + "\">" + product.getTitle() + "</a></p>\r\n" + 
						"												<a onclick=\"addToCart(" + product.getId() + ", 1)\" class=\"btn btn-default add-to-cart\"><i class=\"fa fa-shopping-cart\"></i>Thêm vào giỏ</a>\r\n" + 
						"											</div>\r\n" + 
						"										</div>\r\n" + 
						"										<div class=\"choose\">\r\n" + 
						"											<ul class=\"nav nav-pills nav-justified\">\r\n" + 
						"												<li><a href=\"\"><i class=\"fa fa-plus-square\"></i>Yêu thích</a></li>\r\n" + 
						"												<li>\r\n" + 
						"												<a class =\"rating\">\r\n";
				
				for (int i = 1; i <= product.getRating()[1]; i++) {
					content += "<i class=\"fa fa-star\"></i>";
				}
				if (product.getRating()[2] != 0) {
					
					content += "<i class=\"fa fa-star-half-o\"></i>";
					
					for (int j = 1; j <= (5 - product.getRating()[1] - 1); j++) {
						content += "<i class=\"fa fa-star-o\"></i>";
					}
				} else {
					
					for (int j = 1; j <= (5 - product.getRating()[1]); j++) {
						content += "<i class=\"fa fa-star-o\"></i>";
					}
				}
				content += "(" + product.getRating()[0] +")\r\n" + 
						"												</a>\r\n" + 
						"												</li>\r\n" + 
						"											</ul>\r\n" + 
						"										</div>\r\n" + 
						"									</div>\r\n" + 
						"								</div>";
				
				out.println(content);	
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
