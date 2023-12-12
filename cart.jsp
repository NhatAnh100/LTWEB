<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>RING! - Giỏ hàng</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>

	<jsp:include page="navigation.jsp"></jsp:include>
	
	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="home">Trang chủ</a></li>
				  <li class="active">Giỏ hàng</li>
				</ol>
			</div>
			
			<c:if test="${checkOutMessage != null}">
				<div class="alert alert-success alert-dismissible" role="alert">
				<span aria-hidden="true"><i class="fa fa-check"></i></span>
	 				 ${checkOutMessage}
	 				 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    					<span aria-hidden="true">&times;</span>
 					</button>
				</div>
			</c:if>
			
			<c:if test="${cartMessage != null}">
				<div class="alert alert-danger alert-dismissible" role="alert">
				<span aria-hidden="true"><i class="fa fa-exclamation-triangle"></i></span>
	 				 ${cartMessage}
	 				 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    					<span aria-hidden="true">&times;</span>
 					</button>
				</div>
			</c:if>
			
			<c:if test="${sessionScope.cart.status == 0 || sessionScope.cart == null}">
				<div class="cart_empty">
	                <img src="images/home/empty.png" / class="empty-icon">
	                <h3>Giỏ hàng của bạn đang trống!</h3>
	                <button class="btn btn-default empty-cart-button" type="button" onclick="location.href='home'">
	                    Tiếp tục mua sắm
	                </button>
	            </div>
			</c:if>
			
			<c:if test="${sessionScope.cart.status != 0 && sessionScope.cart != null}">
				<div class="table-stacked cart_info">
					<table class="table table-condensed">
						<thead>
							<tr class="cart_menu">
								<td class="image col-sm-2">Tổng sản phẩm (${sessionScope.cart.status})</td>
								<td class="description col-sm-3"></td>
								<td class="price col-sm-2">Giá</td>
								<td class="quantity col-sm-2">Số lượng</td>
								<td class="total col-sm-2">Thành tiền</td>
								<td class="trash col-sm-2">
									<a href="cart?clearCart=ok"><i class="fa fa-times"></i></a>
								</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items = "${sessionScope.cart.items}" var = "item">
								<tr>
									<td class="cart_product">
										<a href="details?pid=${item.book.id}"><img src="${item.book.image}" alt=""></a>
									</td>
									<td class="cart_description">
										<h4><a href="details?pid=${item.book.id}">${item.book.title}</a></h4>
									</td>
									<td class="cart_price">
										<p>${item.price}đ</p>
									</td>
									<td class="cart_quantity">
										<button class="btn btn-quantity" onclick="location.href='cart?decreasedBook=${item.id}'" type="button">-</button>
										<form action="cart" method = "post">
											<input type="text" name="itemQuantity" value="${item.quantity}" autocomplete="off">
											<input type="hidden" name="changedBook" value="${item.id}" />
										</form>
										<button class="btn btn-quantity" onclick="location.href='cart?increasedBook=${item.id}'" type="button">+</button>
									</td>
									<td class="cart_total">
										<p class="cart_total_price">${item.totalPrice}đ</p>
									</td>
									<td class="cart_delete">
										<a class="cart_quantity_delete" href="cart?removedBook=${item.id}"><i class="fa fa-times"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>
	</section> <!--/#cart_items-->
	
	<c:if test="${sessionScope.cart.status != 0 && sessionScope.cart != null}">
		<section id="do_action">
			<div class="container">
				<div class="heading">
					<h3>Đơn giá giỏ hàng dự tính</h3>
					<p>Bạn có thể áp dụng mã giảm giá hoặc chọn các dịch vụ khác nhau khi thanh toán</p>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="total_area">
							<ul>
								<li>Thành tiền: <span>${sessionScope.cart.cartTotalPrice}đ</span></li>
								<li>VAT: <span>10%</span></li>
								<li>Phí ship: <span>Free</span></li>
								<li>Tổng: <span class="total">${sessionScope.cart.cartTotalPrice + sessionScope.cart.cartTotalPrice * 0.1}đ</span></li>
							</ul>
							<c:if test="${sessionScope.cart.status != 0 && sessionScope.cart != null}">
								<a class="btn btn-default check_out" href="checkout.jsp">Thanh toán</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</section><!--/#do_action-->
	</c:if>

	<jsp:include page="footer.jsp"></jsp:include>	

    <script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>