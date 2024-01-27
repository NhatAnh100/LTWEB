<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header id="header"><!--header-->
	<div class="header_top"><!--header_top-->
		<div class="container">
			<div class="row">
				<div class="col-sm-6 ">
					<div class="contactinfo">
						<ul class="nav nav-pills">
							<li><a href=""><i class="fa fa-phone"></i> +84123456789</a></li>
							<li><a href=""><i class="fa fa-envelope"></i> daihocnonglam@hcmuaf.edu.vn</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="social-icons pull-right">
						<ul class="nav navbar-nav">
							<li><a href=""><i class="fa fa-facebook"></i></a></li>
							<li><a href=""><i class="fa fa-twitter"></i></a></li>
							<li><a href=""><i class="fa fa-google-plus"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div><!--/header_top-->
	
	<div class="header-middle"><!--header-middle-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<div class="logo pull-left">
						<div class="mainlogo">
							<h2><a href="home"><span><img src="images/home/bell.png" alt="" /> RING!</span> - books</a></h2>
						</div>
					</div>
				</div>
				<div class="col-sm-8">
					<div class="shop-menu pull-right">
						<ul class="nav navbar-nav">
							<li><a href=""><i class="fa fa-bell"></i> Thông báo</a></li>
							<li>
								<c:if test = "${(sessionScope.cart.status != null && sessionScope.cart.status < 1) || sessionScope.cart.status == null}"> 
									<a href="cart" id="cart-icon" class = ${requestScope['javax.servlet.forward.request_uri'].endsWith("/cart") ? "active" : ""}><i class="fa fa-shopping-cart"></i> Giỏ hàng</a>
								</c:if>
							
								<c:if test = "${sessionScope.cart.status != null && sessionScope.cart.status >= 1}"> 
									<a href="cart" id="cart-icon" class = ${requestScope['javax.servlet.forward.request_uri'].endsWith("/cart") ? "active" : ""}><i class="fa fa-shopping-cart"></i> Giỏ hàng (${sessionScope.cart.status})</a>
								</c:if>
 								
 								<a class="popup" style="display: none">
                                     <span class="popuptext show" id="myPopup">
                                           Đã thêm sản phẩm vào giỏ hàng
                                           <button type="button" class="close" onclick="$('.popup').hide()">
		    							<span class="close-box">&times;</span>
		 								</button>
		 								<button type="button" onclick="location.href='cart'" class="btn btn-default checkcart">Tiến hành thanh toán</button>
	 								</span>
 								</a>
 								
 								<c:if test="${addedBook != null}">
	 								<a class="popup">
		 								<span class="popuptext show" id="myPopup">
		 									Đã thêm sản phẩm vào giỏ hàng
											<button type="button" class="close" onclick="$('.popup').hide()">
			    							<span class="close-box">&times;</span>
			 								</button>
			 								<button type="button" class="btn btn-default checkcart">Tiến hành thanh toán</button>
		 								</span>
	 								</a>
 								</c:if>
 								
								</li>
							
							<li><a href=""><i class="fa fa-star"></i> Yêu thích</a></li>
							
							<c:if test = "${sessionScope.loginedUser != null}"> <!-- logined -->
								<li><a href=""><i class="fa fa-user"></i> ${sessionScope.loginedUser.username}</a></li>
								<li><a href="logout"><i class="fa fa-sign-out"></i> Đăng xuất</a></li>
							</c:if>
							
							<c:if test = "${sessionScope.loginedUser == null}"> <!-- guest -->
								<li><a href="login.jsp"><i class="fa fa-lock"></i> Đăng nhập</a></li>
							</c:if>
							
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div><!--/header-middle-->

	<div class="header-bottom"><!--header-bottom-->
		<div class="container">
			<div class="row">
				<div class="col-sm-9">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</div>
					<div class="mainmenu pull-left">
						<ul class="nav navbar-nav collapse navbar-collapse">
							<li><a href="search?searchBar=">Cửa hàng</a></li>
							<li><a href="">Sự kiện</a></li>
							<li><a href="">Hỗ trợ</a></li>
							<li><a href="">Thông tin</a></li>
							
							<c:if test = "${sessionScope.loginedUser.isSeller == 1}"> <!-- manager stuff -->
								<li><a href="manager">Quản lý sách</a></li>
							</c:if>
							
							<c:if test = "${sessionScope.loginedUser.isAdmin == 1}"> <!-- admin stuff -->
								<li><a href="admin">Admin</a></li>
							</c:if>
						</ul>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="search_box pull-right">
						<form action = "search" method = "get">
							<input name = "searchBar" type="text" value = "${searchBarString}" placeholder="Tìm kiếm ..."/>
						</form>
					</div>
				</div>
			</div>
			</div>
		</div>
</header>