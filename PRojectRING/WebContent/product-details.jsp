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
    <title>RING! - ${book.title}</title>
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
	
	<section>
		<div class="container">
			<div class="row">
			
				<jsp:include page="left.jsp"></jsp:include>
				
				<div class="col-sm-9 padding-right">
					<div class="product-details"><!--product-details-->
						<div class="col-sm-5">
							<div class="view-product">
								<img src="${book.image}" alt="" />
							</div>
							<br></br>
							<div id="product-images" class="carousel slide" data-ride="carousel">
								
								  <!-- Wrapper for slides -->
								    <div class="carousel-inner">
										<div class="item active">
										  <a href=""><img src="${book.image}" alt=""></a>
										  <a href=""><img src="${book.image}" alt=""></a>
										  <a href=""><img src="${book.image}" alt=""></a>
										</div>
										<div class="item">
										  <a href=""><img src="${book.image}" alt=""></a>
										  <a href=""><img src="${book.image}" alt=""></a>
										  <a href=""><img src="${book.image}" alt=""></a>
										</div>
									</div>

								  <!-- Controls -->
								  <a class="left item-control" href="#product-images" data-slide="prev">
									<i class="fa fa-angle-left"></i>
								  </a>
								  <a class="right item-control" href="#product-images" data-slide="next">
									<i class="fa fa-angle-right"></i>
								  </a>
							</div>

						</div>
						<div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
								<img src="images/product-details/new.jpg" class="newarrival" alt="" />
								<h2>${book.title}</h2>
								<h3>
									<c:forEach begin="1" end="${book.rating[1]}" var="i"><i class="fa fa-star"></i></c:forEach><c:if test="${book.rating[2] != 0}"><i class="fa fa-star-half-o"></i><c:forEach begin="1" end="${5 - book.rating[1] - 1}" var="i"><i class="fa fa-star-o"></i></c:forEach></c:if><c:if test="${book.rating[2] == 0}"><c:forEach begin="1" end="${5 - book.rating[1]}" var="i"><i class="fa fa-star-o"></i></c:forEach></c:if>
									(${book.rating[0]} đánh giá)
								</h3>
								<br></br>
								<h2>${book.price}đ</h2>
								<h3>Chi tiết</h3>
								<p><b>Nhà xuất bản: </b>${bookPublisher}</p>
								<p><b>Tác giả: </b>${book.author}</p>
								<p><b>Hình thức bìa: </b>${book.type}</p>
								<br>
								<h3>Mô tả sản phẩm</h3>
								<p id="short-description">${book.description}</p>
								<a href="#details" data-toggle="tab">Xem thêm...</a>
								
								<br>
								<h3>Số lượng: </h3>
								<%-- <form action="addtocart" method="get" id="cart">
								<div class="input-group mb-3">
									<button class="btn btn-quantity" onclick="quantityChange(-1)" type="button">-</button>
									<input type="text" value="1" id="quantityInput" name="pquantity">
									<input type="hidden" value="${book.id}" id="quantityInput" name="pid">
									<button class="btn btn-quantity" onclick="quantityChange(1)" type="button">+</button>
								</div>
							
								<div class="d-grid gap-2 d-md-block">
									<button type="submit" class="btn btn-default cart" form="cart" id = "buy-button">
										<i class="fa fa-shopping-cart"></i>
										Thêm vào giỏ
									</button>
								</div>
								</form> --%>
								
								<form id="cart">
                                <div class="input-group mb-3">
                                    <button class="btn btn-quantity" onclick="quantityChange(-1)" type="button">-</button>
                                    <input type="text" value="1" id="quantityInput">
                                    <button class="btn btn-quantity" onclick="quantityChange(1)" type="button">+</button>
                                </div>

                                <div class="d-grid gap-2 d-md-block">
                                    <button class="btn btn-default cart" type="button" id="buy-button" onclick="addToCart(${book.id}, document.getElementById('quantityInput').value)">
                                        <i class="fa fa-shopping-cart"></i>
                                        Thêm vào giỏ
                                    </button>
                                </div>
                            	</form>
							</div><!--/product-information-->
						</div>
					</div><!--/product-details-->
					
					<div class="category-tab shop-details-tab"><!--category-tab-->
						<div class="col-sm-12">
							<ul class="nav nav-tabs">
								<li><a href="#details" data-toggle="tab">Thông tin chi tiết</a></li>
								<li class="active"><a href="#reviews" data-toggle="tab">Đánh giá (${totalReview})</a></li>
								<li><a href="#similars" data-toggle="tab">Sản phẩm liên quan</a></li>
							</ul>
						</div>
						<div class="tab-content">
							<div class="tab-pane fade" id="details" >
								<div class="col-sm-12">
									<h4>Thông tin chi tiết: </h4>
									<p>Mã hàng: ${book.id}</p>
									<p>Tác giả: ${book.author}</p>
									<p>NXB: ${bookPublisher}</p>
									<p>Trọng lượng (gr): ${bookDetail.weight} gr</p>
									<p>Kích thước: ${bookDetail.size} cm</p>
									<p>Số trang: ${bookDetail.pages}</p>
									<p>Hình thức: ${book.type}</p>
									<br>
									<textarea rows="20" cols="" style="background-color:#fff;" disabled>${book.description}</textarea>
									<br>
								</div>
							</div>
							
							<div class="tab-pane fade" id="similars" >
								<c:forEach items = "${listRelate}" var = "book">
									<div class="col-sm-3">
										<div class="product-image-wrapper">
											<div class="single-products">
												<div class="productinfo text-center">
													<a href = "details?pid=${book.id}"><img src="${book.image}" alt="" /></a>
													<h2>${book.price}đ</h2>
													<p><a href = "details?pid=${book.id}">${book.title}</a></p>
													<%-- <button type="button" onclick="location.href='addtocart?pid=${book.id}'" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button> --%>
													<button type="button" onclick="addToCart(${book.id}, 1)" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							
							<div class="tab-pane fade active in" id="reviews" >
								<div class="col-sm-12">
									<c:forEach items = "${listReview}" var = "review">
										<ul>
											<li><a href=""><i class="fa fa-user"></i>${review.username}</a></li>
											<li><a><i class="fa fa-clock-o"></i>${review.getTimeString()}</a></li>
											<li><a><i class="fa fa-calendar-o"></i>${review.getDateString()}</a></li>
											<li class="pull-right"><a><i class="fa fa-star"></i>${review.rating}</a></li>
										</ul>
										<p>${review.content}</p>
										<br><br>
									</c:forEach>
									
									<c:if test="${listReview.size() != 0}">
										<ul class="pagination">
											<c:if test = "${currentPage < 3}">
												<c:if test="${currentPage > 1}">
													<li><a href="details?pid=${book.id}&selectedPage=${currentPage - 1}">&laquo;</a></li>	<!-- trang trước -->
												</c:if>
											
												<c:forEach begin="1" end="${currentPage + 4}" var="i">
													<c:if test = "${i <= endPage}">
														<li class= ${currentPage == i ? "active" : ""}><a href="details?pid=${book.id}&selectedPage=${i}">${i}</a></li>
													</c:if>
												</c:forEach>
												
												<c:if test = "${currentPage < endPage - 2 && endPage > curentPage + 4}"> <!-- đưa về trang cuối -->
													<li><p class="temp">...</p></li>
													<li><a href="details?pid=${book.id}&selectedPage=${endPage}">${endPage}</a></li>
												</c:if>
												
												<c:if test = "${currentPage != endPage}">
													<li><a href="details?pid=${book.id}&selectedPage=${currentPage + 1}">&raquo;</a></li> <!-- trang kế -->
												</c:if>
											</c:if>
											
											<c:if test = "${currentPage >= 3}">
												<li><a href="details?pid=${book.id}&selectedPage=${currentPage - 1}">&laquo;</a></li>	<!-- trang trước -->
												
												<c:if test = "${currentPage >= 4}">  <!-- trang đầu -->
													<li><a href="details?pid=${book.id}&selectedPage=1">1</a></li>
													<li><p class="temp">...</p></li>
												</c:if>
												
												<c:forEach begin="${currentPage - 2}" end="${currentPage + 2}" var="i">
													<c:if test = "${i <= endPage}">
														<li class= ${currentPage == i ? "active" : ""}><a href="details?pid=${book.id}&selectedPage=${i}">${i}</a></li>
													</c:if>
												</c:forEach>
													
												<c:if test = "${currentPage < endPage - 2 && endPage > curentPage + 4}}"> <!-- đưa về trang cuối -->
													<li><p class="temp">...</p></li>
													<li><a href="details?pid=${book.id}&selectedPage=${endPage}">${endPage}</a></li>
												</c:if>
												
												<c:if test = "${currentPage != endPage}">
													<li><a href="details?pid=${book.id}&selectedPage=${currentPage + 1}">&raquo;</a></li> <!-- trang kế -->
												</c:if>
											</c:if>
										</ul>
									</c:if>
									
									<c:if test = "${sessionScope.loginedUser != null}">
										<p><b>Để lại đánh giá của bạn</b></p>
										
										<form action="review">
											<textarea name="content" required></textarea>
											<input type="hidden" name="pid" value="${book.id}"></input>
											<span class="pull-left">
											<b>Đánh giá:  </b> 
												<select class="rating-control" id="rating-dropdown" name="rating">
											      <option value = "1" id = "rating-option">1</option> 
											      <option value = "2" id = "rating-option">2</option>
											      <option value = "3" id = "rating-option">3</option>
											      <option value = "4" id = "rating-option">4</option>
											      <option value = "5"  id = "rating-option" selected>5</option>
											    </select>
											</span>
											<button type="submit" class="btn btn-default pull-right">
												Gửi đánh giá
											</button>
										</form>
									</c:if>
									
									<c:if test = "${sessionScope.loginedUser == null}">
										<button type="button" onclick="location.href='login.jsp'" class="btn btn-default pull-center">
												Đăng nhập để đánh giá sản phẩm
											</button>
									</c:if>
								</div>
							</div>
							
						</div>
					</div><!--/category-tab-->
					
					<div class="recommended_items"><!--recommended_items-->
						<h2 class="title text-center">Sản phẩm nổi bật</h2>
						
						<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner">
							
								<c:forEach begin="0" end="${Math.ceil(listRecommend.size()/4) - 1}" var="i">
									<c:if test="${i <= 0}">
										<div class="item active">	
											<c:forEach begin="0" end="3" var = "j">
												<div class="col-xs-12 col-sm-6 col-md-3">
													<div class="product-image-wrapper">
														<div class="single-products">
															<div class="productinfo text-center">
																<c:set var = "book" value = "${listRecommend.get((4*i)+j)}"/>
																<a href = "details?pid=${book.id}"><img src="${book.image}" alt="" /></a>
																<h2>${book.price}đ</h2>
																<p><a href = "details?pid=${book.id}&?bookCategory=${selectedCategory}">${book.title}</a></p>
																<%-- <button type="button" onclick="location.href='addtocart?pid=${book.id}'" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button> --%>
																<button type="button" onclick="addToCart(${book.id}, 1)" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</c:if>
									
									<c:if test="${i > 0}">
										<div class="item">	
											<c:forEach begin="0" end="3" var = "j">
												<c:if test="${((4*i)+j) < listRecommend.size()}">
													<div class="col-xs-12 col-sm-6 col-md-3">
														<div class="product-image-wrapper">
															<div class="single-products">
																<div class="productinfo text-center">
																	<c:set var = "book" value = "${listRecommend.get((4*i)+j)}"/>
																	<a href = "details?pid=${book.id}"><img src="${book.image}" alt="" /></a>
																	<h2>${book.price}đ</h2>
																	<p><a href = "details?pid=${book.id}&?bookCategory=${selectedCategory}">${book.title}</a></p>
																	<%-- <button type="button" onclick="location.href='addtocart?pid=${book.id}'" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>--%>
																	<button type="button" onclick="addToCart(${book.id}, 1)" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
																</div>
															</div>
														</div>
													</div>
												</c:if>
											</c:forEach>
										</div>
									</c:if>
								</c:forEach>
							</div>
							 <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
								<i class="fa fa-angle-left"></i>
							  </a>
							  <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
								<i class="fa fa-angle-right"></i>
							  </a>			
						</div>
					</div><!--/recommended_items-->
					<br><br>
					
				</div>
			</div>
		</div>
	</section>
	
	<jsp:include page="footer.jsp"></jsp:include>
  
    <script src="js/jquery.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
    
	<script type="text/javascript">
	    var i = 1;
	    function quantityChange(n) {
	        var quantity = document.getElementById("quantityInput");
	        i = i + n;
	        if (i < 1) {
	
	            i = 1;
	        }
	        quantity.value = i;
	    };
	    	
    	function addToCart(pid, pquantity){
    		$.ajax({
				type: 'GET',
				url: 'addtocart?pid=' + pid + '&pquantity=' + pquantity + '&type=ajax',
				header: {
					Accept : "application/json; charset=utf-8",
					"Cotent-Type": "application/json; charset=utf-8"
				},
				success: function(result){
					var status = $.parseJSON(result);
					$("#cart-icon").html('<i class="fa fa-shopping-cart"></i> Giỏ hàng (' + status + ')');
					$(".popup").show();
                    $(window).scrollTop(0);
				}
    		});
    	}
	</script>
</body>
</html>