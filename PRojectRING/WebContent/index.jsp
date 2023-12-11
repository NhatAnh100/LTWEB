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
    <title>RING! - Trang chủ</title>
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
	
	<c:if test="${listRandom.size() != 0}">
		<section id="slider"><!--slider-->
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div id="slider-carousel" class="carousel slide" data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
								<c:forEach begin="2" end="${listRandom.size()}" var="i">
									<li data-target="#slider-carousel" data-slide-to="${i - 1}"></li>
								</c:forEach>
							</ol>
							
							<div class="carousel-inner">
								<div class="item active">
									<div class="col-sm-6">
										<h1><span>RING!</span> - BOOKS</h1>
										<c:set var = "book" value = "${listRandom.get(0)}"/>
										<h2><a href = "details?pid=${book.id}">${book.title}</a></h2>
										<p>${book.description}</p>
										<%-- <button type="button" onclick="location.href='addtocart?pid=${book.id}'" class="btn btn-default get">Mua ngay</button> --%>
										<button type="button" onclick="addToCart(${book.id}, 1)" class="btn btn-default get">Mua ngay</button>
									</div>
									<div class="col-sm-6">
										<a href = "details?pid=${book.id}"><img src="${book.image}" class="best img-responsive" alt="" /></a>
										<img src=""  class="pricing" alt="" />
									</div>
								</div>
							
							<c:forEach begin="2" end="${listRandom.size()}" var="i">
								<div class="item">
									<div class="col-sm-6">
										<h1><span>RING!</span> - BOOKS</h1>
										<c:set var = "book" value = "${listRandom.get(i - 1)}"/>
										<h2><a href = "details?pid=${book.id}">${book.title}</a></h2>
										<p>${book.description}</p>
										<%-- <button type="button" onclick="location.href='addtocart?pid=${book.id}'" class="btn btn-default get">Mua ngay</button> --%>
										<button type="button" onclick="addToCart(${book.id}, 1)" class="btn btn-default get">Mua ngay</button>
									</div>
									<div class="col-sm-6">
										<a href = "details?pid=${book.id}"><img src="${book.image}" class="best img-responsive" alt="" /></a>
										<img src=""  class="pricing" alt="" />
									</div>
								</div>
							</c:forEach> 
								
							</div>
							
							<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
								<i class="fa fa-angle-left"></i>
							</a>
							<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
								<i class="fa fa-angle-right"></i>
							</a>
						</div>
						
					</div>
				</div>
			</div>
		</section><!--/slider-->
	</c:if>
	
	<section>
		<div class="container">
			<div class="row">
				
				<jsp:include page="left.jsp"></jsp:include> <!-- category -->
				
					<div class="col-sm-9 padding-right">
						<div class="features_items"><!--features_items-->
							<h2 class="title text-center">${empty selectedCategory ? "Tổng hợp sản phẩm" : selectedCategory }</h2>
							<div id="list-show">
							<c:forEach items = "${listBook}" var = "book">
								<div class="col-sm-4 product-show">
									<div class="product-image-wrapper">
										<div class="single-products">
											<div class="productinfo text-center">
												<a href = "details?pid=${book.id}"><img src="${book.image}" alt="" /></a>
												<h2>${book.price} đ</h2>
												<p><a href = "details?pid=${book.id}">${book.title}</a></p>
												<%-- <a href="addtocart?pid=${book.id}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</a> --%>
												<a onclick="addToCart(${book.id}, 1)" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</a>
											</div>
											<%-- <div class="product-overlay">
												<div class="overlay-content">
													<h2>${book.price} đ</h2>
													<p>${book.title}</p>
													<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</a>
												</div>
											</div> --%>
										</div>
										<div class="choose">
											<ul class="nav nav-pills nav-justified">
												<li><a href=""><i class="fa fa-plus-square"></i>Yêu thích</a></li>
												<li>
												<a class ="rating">
													<c:forEach begin="1" end="${book.rating[1]}" var="i"><i class="fa fa-star"></i></c:forEach><c:if test="${book.rating[2] != 0}"><i class="fa fa-star-half-o"></i><c:forEach begin="1" end="${5 - book.rating[1] - 1}" var="i"><i class="fa fa-star-o"></i></c:forEach></c:if><c:if test="${book.rating[2] == 0}"><c:forEach begin="1" end="${5 - book.rating[1]}" var="i"><i class="fa fa-star-o"></i></c:forEach></c:if>
													(${book.rating[0]})
												</a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</c:forEach>
							</div>
							
						    <div class="col text-center">
								<!-- <button type="button" onclick="location.href='search?searchBar='" class="btn btn-default get">Xem thêm</button> -->
								<button type="button" onclick="loadMoreDefault()" class="btn btn-default get">Xem thêm</button>
							</div>
						</div><!--features_items-->
					
					<br><br>
					<c:forEach items = "${listRandomCategory}" var = "category">
						<h2 class="title text-center">${category.cName}</h2>
						<div class="category-tab"><!--category-tab-->
							<div class="col-sm-12">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#all" data-toggle="tab">Tất cả</a></li>
									<c:forEach items="${category.subCate}" var="sub" varStatus = "status">
										<li><a href="#${sub.sId}" data-toggle="tab">${sub.sName}</a></li>
									</c:forEach> 									
								</ul>
							</div>
							<div class="tab-content">
								<div class="tab-pane fade active in" id="list-random-${category.cId}" >
									<%-- <c:forEach items="${listRandom}" var="book">
										<div class="col-sm-3 product-random-${category.cId}">
											<div class="product-image-wrapper">
												<div class="single-products">
													<div class="productinfo text-center">
														<a href = "details?pid=${book.id}"><img src="${book.image}" alt="" /></a>
														<h2>${book.price}đ</h2>
														<p><a href = "details?pid=${book.id}&?bookCategory=${selectedCategory}">${book.title}</a></p>
														<button type="button" onclick="location.href='addtocart?pid=${book.id}'" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
														<button type="button" onclick="addToCart(${book.id}, 1)" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
													</div>
												</div>
											</div>
										</div>
									</c:forEach> --%>
								</div>
							</div>
						</div><!--/category-tab-->
						<div class="col text-center">
							<%-- <button type="button" onclick="location.href='category?cid=${category.cId}'" class="btn btn-default get">Xem thêm</button> --%>
							<button type="button" onclick="loadMore(${category.cId})" class="btn btn-default get">Xem thêm</button>
						</div>
						<br><br>
					</c:forEach>
					
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
																	<%-- <button type="button" onclick="location.href='addtocart?pid=${book.id}'" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button> --%>
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
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/price-range.js"></script>
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
    	
    	function loadMoreDefault(){
    		var amount = document.getElementsByClassName("product-show").length;
    		if ((amount % 3 != 0) || (amount >= 18)){ //hết sản phẩm hoặc không load nhiều quá
    			location.href='search?searchBar=';
    		} else {
    			$.ajax({
    				type: 'GET',
    				url: '/ProjectRING/loadmore',
    				data: {
    					total: amount
    				},
    				success: function(result){
    					var list = document.getElementById("list-show");
    					list.innerHTML += result;
    				},
    				error: function(e){
    					console.log(e);
    				}
        		});
    		}
    	}
    	
    	function loadMore(cId){
    		var amount = document.getElementsByClassName("product-random-" + cId).length;
    		if ((amount % 4 != 0) || (amount >= 12)){ //hết sản phẩm hoặc không load nhiều quá
	    		location.href='category?cid=' + cId;
    		} else {
    			$.ajax({
    				type: 'GET',
    				url: '/ProjectRING/loadmore',
    				data: {
    					cateId: cId,
    					total: amount
    				},
    				success: function(result){
    					var list = document.getElementById("list-random-" + cId);
    					
    					list.innerHTML += result;
    				},
    				error: function(e){
    					console.log(e);
    				}
        		});
    		}
    	}
    	
    	<c:forEach items = "${listRandomCategory}" var = "category">
    		loadMore(<c:out value="${category.cId}" />);
    	</c:forEach>
	</script>
</body>
</html>