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
    <title>RING! - ${empty selectedCategory ? (empty selectedPublisher ? (empty searchBarString || searchBarString != "" ? "Tổng hợp sản phẩm" : searchBarString) : selectedPublisher) : selectedCategory }</title>
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
	
	<section id="advertisement">
		<div class="container">
			<img src="" alt="" />
		</div>
	</section>
	
	<section>
		<div class="container">
			<div class="row">
				
				<jsp:include page="left.jsp"></jsp:include>
				
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">${empty selectedCategory ? (empty selectedPublisher ? (empty searchBarString || searchBarString != "" ? "Tổng hợp sản phẩm" : searchBarString) : selectedPublisher) : selectedCategory }</h2>
							<c:forEach items = "${listBook}" var = "book">
								<div class="col-sm-4">
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
							
					</div><!--features_items-->
					
					<br>
					<c:if test="${listBook.size() != 0}">
						<ul class="pagination">
							<c:if test = "${currentPage < 3}">
								<c:if test = "${searchBarString != null}">
									<c:if test="${currentPage > 1}">
										<li><a href="search?selectedPage=${currentPage - 1}&searchBar=${searchBarString}">&laquo;</a></li>	<!-- trang trước -->
									</c:if>
								
									<c:forEach begin="1" end="${currentPage + 4}" var="i">
										<c:if test = "${i <= endPage}">
											<li class= ${currentPage == i ? "active" : ""}><a href="search?selectedPage=${i}&searchBar=${searchBarString}">${i}</a></li>
										</c:if>
									</c:forEach>
									
									<c:if test = "${currentPage < endPage - 2 && endPage > curentPage + 4}"> <!-- đưa về trang cuối -->
										<li><p class="temp">...</p></li>
										<li><a href="search?selectedPage=${endPage}&searchBar=${searchBarString}">${endPage}</a></li>
									</c:if>
									
									<c:if test = "${currentPage != endPage}">
										<li><a href="search?selectedPage=${currentPage + 1}&searchBar=${searchBarString}">&raquo;</a></li> <!-- trang kế -->
									</c:if>
								</c:if>
								
								<c:if test = "${selectedCategory != null}">
									<c:if test="${currentPage > 1}">
										<li><a href="category?selectedPage=${currentPage - 1}&cid=${selectedCID}">&laquo;</a></li>	<!-- trang trước -->
									</c:if>
								
									<c:forEach begin="1" end="${currentPage + 4}" var="i">
										<c:if test = "${i <= endPage}">
											<li class= ${currentPage == i ? "active" : ""}><a href="category?selectedPage=${i}&cid=${selectedCID}">${i}</a></li>
										</c:if>
									</c:forEach>
									
									<c:if test = "${currentPage < endPage - 2 && endPage > curentPage + 4}"> <!-- đưa về trang cuối -->
										<li><p class="temp">...</p></li>
										<li><a href="category?selectedPage=${endPage}&cid=${selectedCID}">${endPage}</a></li>
									</c:if>
									
									<c:if test = "${currentPage != endPage}">
										<li><a href="category?selectedPage=${currentPage + 1}&cid=${selectedCID}">&raquo;</a></li> <!-- trang kế -->
									</c:if>
								</c:if>
							</c:if>
							
							<c:if test = "${currentPage >= 3}">
								<c:if test = "${searchBarString != null}">
									<li><a href="search?selectedPage=${currentPage - 1}&searchBar=${searchBarString}">&laquo;</a></li>	<!-- trang trước -->
									
									<c:if test = "${currentPage >= 4}">  <!-- trang đầu -->
										<li><a href="search?selectedPage=1&searchBar=${searchBarString}">1</a></li>
										<li><p class="temp">...</p></li>
									</c:if>
									
									<c:forEach begin="${currentPage - 2}" end="${currentPage + 2}" var="i">
										<c:if test = "${i <= endPage}">
											<li class= ${currentPage == i ? "active" : ""}><a href="search?selectedPage=${i}&searchBar=${searchBarString}">${i}</a></li>
										</c:if>
									</c:forEach>
										
									<c:if test = "${currentPage < endPage - 2 && endPage > curentPage + 4}"> <!-- đưa về trang cuối -->
										<li><p class="temp">...</p></li>
										<li><a href="search?selectedPage=${endPage}&searchBar=${searchBarString}">${endPage}</a></li>
									</c:if>
									
									<c:if test = "${currentPage != endPage}">
										<li><a href="search?selectedPage=${currentPage + 1}&searchBar=${searchBarString}">&raquo;</a></li> <!-- trang kế -->
									</c:if>
								</c:if>
								
								<c:if test = "${selectedCategory != null}">
									<li><a href="category?selectedPage=${currentPage - 1}&cid=${selectedCID}">&laquo;</a></li>	<!-- trang trước -->
									
									<c:if test = "${currentPage >= 4}">  <!-- trang đầu -->
										<li><a href="category?selectedPage=1&cid=${selectedCID}">1</a></li>
										<li><p class="temp">...</p></li>
									</c:if>
									
									<c:forEach begin="${currentPage - 2}" end="${currentPage + 2}" var="i">
										<c:if test = "${i <= endPage}">
											<li class= ${currentPage == i ? "active" : ""}><a href="category?selectedPage=${i}&cid=${selectedCID}">${i}</a></li>
										</c:if>
									</c:forEach>
										
									<c:if test = "${currentPage < endPage - 2 && endPage > curentPage + 4}"> <!-- đưa về trang cuối -->
										<li><p class="temp">...</p></li>
										<li><a href="category?selectedPage=${endPage}&cid=${selectedCID}">${endPage}</a></li>
									</c:if>
									
									<c:if test = "${currentPage != endPage}">
										<li><a href="category?selectedPage=${currentPage + 1}&cid=${selectedCID}">&raquo;</a></li> <!-- trang kế -->
									</c:if>
								</c:if>
							</c:if>
						</ul>
					</c:if>
					
				</div>
			</div>
		</div>
	</section>
	
	<jsp:include page = "footer.jsp"></jsp:include>
  
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