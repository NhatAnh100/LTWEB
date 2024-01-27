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
    <title>RING!-MANAGEMENT - Quản lý</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet" />
	
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>

	<jsp:include page="simpleNavigation.jsp"></jsp:include>
		
	<br>
   	<div class="container">
    	<c:if test="${sessionScope.managerMessage != null}">
	    	<div class="alert alert-danger alert-dismissible" role="alert">
				<span aria-hidden="true"><i class="fa fa-exclamation-triangle"></i></span>
				 ${sessionScope.managerMessage}
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	 					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<c:remove var="managerMessage" scope="session" />
		</c:if>
	
		<div class="row">
			<div class="col-sm-5 col-md-7">
				<div class="manager-title">
					<h2>Sản phẩm của: ${sessionScope.loginedUser.username}</h2>
				</div>
			</div>
		
			<div class="col-sm-7 col-md-5">
				<div class="manager_search_box">
					<form action = "" method = "get">
						<button type="button" onclick="location.href='addproduct'" class="btn btn-default main-action pull-right"><i class="fa fa-plus">&nbsp;</i>Thêm</button>
						<button type="submit" form="managerform" name="rSelectedBookBtn" class="btn btn-default danger-action pull-right"><i class="fa fa-trash-o">&nbsp;</i>Xoá</button>
						<button type="button" onclick="location.href='removeproduct?selectedAll=1'" class="btn btn-default danger-action pull-right"><i class="fa fa-trash-o">&nbsp;</i>Xoá tất cả</button>
					</form>
				</div>
			</div>
		</div> 
	</div>
		
	<form id="managerform" action="removeproduct" method="post">
		<section>
			<div class="container">
	            <div class="row">
	                <div class="panel panel-default mb-4">
	                    <div class="panel-heading">
	                        <i class="fa fa-table" aria-hidden="true"></i>
	                        Danh sách sản phẩm
	                    </div>
	                    <div class="panel-body">
	                        <table id="datatableProduct">
	                            <thead>
	                                <tr>
	                                    <th>Chọn</th>
	                                    <th>ID</th>
	                                    <th>Tên</th>
	                                    <th>Ảnh</th>
	                                    <th>Giá</th>
	                                    <th>Số lượng</th>
	                                    <th>Hành động</th>
	                                </tr>
	                            </thead>
	                            <tfoot>
	                                <tr>
	                                	<th>Chọn</th>
	                                    <th>ID</th>
	                                    <th>Tên</th>
	                                    <th>Ảnh</th>
	                                    <th>Giá</th>
	                                    <th>Số lượng</th>
	                                    <th>Hành động</th>
	                                </tr>
	                            </tfoot>
	                            <tbody>
		                            <c:forEach items = "${listBook}" var = "book">
										<tr>
											<td class="cart_select">
												<input id="product-select" type="checkbox" name="selectedBook" value="${book.id}">
											</td>
		                                    <td>${book.id}</td>
		                                    <td><a href="details?pid=${book.id}">${book.title}</a></td>
		                                    <td style="width: 10%; justify-content: center"><a href="details?pid=${book.id}"><img id="admin-product-img" src="${book.image}" alt=""></a></td>
		                                    <td>${book.price}đ</td>
		                                    <td>${book.quantity}</td>
		                                    <td class="cart_action">
												<a class="cart_edit" href="editproduct?pid=${book.id}"><i class="fa fa-edit"></i></a>
												<a class="cart_delete" href="removeproduct?removedBook=${book.id}"><i class="fa fa-times"></i></a>
											</td>
		                                </tr>
									</c:forEach>
	                            </tbody>
	                        </table>
	                    </div>
	                </div>
	            </div>
	        </div>
    	</section>
   	</form>
    
	<!-- <div class="row">
		<div class="col-sm-5 col-md-7">
			<div class="manager-title">
				<h2>Danh sách sản phẩm</h2>
			</div>
		</div>
		
		<div class="col-sm-7 col-md-5">
			<div class="manager_search_box">
				<form action = "" method = "get">
					<input name="searchBar" type="text" class="pull-left" placeholder="Tìm kiếm ..."/>
					<button type="button" onclick="location.href='addproduct'" class="btn btn-default main-action pull-right"><i class="fa fa-plus">&nbsp;</i>Thêm</button>
					<button type="submit" form="managerform" name="rSelectedBookBtn" class="btn btn-default main-action pull-right"><i class="fa fa-trash-o">&nbsp;</i>Xoá</button>
				</form>
			</div>
		</div>
	</div> -->
	
	<%-- <div class="container">
	 <div class="row">
			<div class="col-sm-5 col-md-7">
				<div class="manager-title">
					<h2>Danh sách sản phẩm</h2>
				</div>
			</div>
			
			<div class="col-sm-7 col-md-5">
				<div class="manager_search_box">
					<form action = "" method = "get">
						<input name="searchBar" type="text" class="pull-left" placeholder="Tìm kiếm ..."/>
						<button type="button" onclick="location.href='addproduct'" class="btn btn-default main-action pull-right"><i class="fa fa-plus">&nbsp;</i>Thêm</button>
						<button type="submit" form="managerform" name="rSelectedBookBtn" class="btn btn-default main-action pull-right"><i class="fa fa-trash-o">&nbsp;</i>Xoá</button>
					</form>
				</div>
			</div>
		</div> 
	</div>
	
	<form id="managerform" action="manager" method="post">
	<section id="manager_list">
		<div class="container">
			<div class="table-stacked cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="select col-sm-1"><input type="checkbox" name="selectedAll" value="all" onClick="toggle(this)"></td>
							<td class="id col-sm-1">ID</td>
							<td class="name col-sm-4">Tên</td>
							<td class="image col-sm-2">Ảnh</td>
							<td class="price col-sm-1">Giá</td>
							<td class="quantity col-sm-1">Số lượng</td>
							<td class="action col-sm-2">Hành động</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items = "${listBook}" var = "book">
							<tr>
								<td class="cart_select">
									<input type="checkbox" name="selectedBook" value="${book.id}">
								</td>
								<td class="cart_id">
									<p>${book.id}</p>
								</td>
								<td class="cart_name">
									<h4><a href="">${book.title}</a></h4>
								</td>
								<td class="cart_product">
									<a href=""><img src="${book.image}" alt=""></a>
								</td>
								<td class="cart_price">
									<p>${book.price}đ</p>
								</td>
								<td class="cart_quantity">
									<p>${book.quantity}</p>
								</td>
								<td class="cart_action">
									<a class="cart_edit" href="editproduct?pid=${book.id}"><i class="fa fa-edit"></i></a>
									<a class="cart_delete" href="manager?removedBook=${book.id}"><i class="fa fa-times"></i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<c:if test="${listBook.size() != 0}">
				<ul class="pagination">
					<c:if test = "${currentPage < 3}">
						<c:if test="${currentPage > 1}">
							<li><a href="manager?selectedPage=${currentPage - 1}">&laquo;</a></li>	<!-- trang trước -->
						</c:if>
						
						<c:forEach begin="1" end="${currentPage + 4}" var="i">
							<c:if test = "${i <= endPage}">
								<li class= ${currentPage == i ? "active" : ""}><a href="manager?selectedPage=${i}">${i}</a></li>
							</c:if>
						</c:forEach>
						
						<c:if test = "${currentPage < endPage - 2 && endPage > curentPage + 4}"> <!-- đưa về trang cuối -->
							<li><p class="temp">...</p></li>
							<li><a href="manager?selectedPage=${endPage}">${endPage}</a></li>
						</c:if>
						
						<c:if test = "${currentPage != endPage}">
							<li><a href="manager?selectedPage=${currentPage + 1}">&raquo;</a></li> <!-- trang kế -->
						</c:if>
					</c:if>
					
					<c:if test = "${currentPage >= 3}">
						<li><a href="manager?selectedPage=${currentPage - 1}">&laquo;</a></li>	<!-- trang trước -->
						
						<c:if test = "${currentPage >= 4}">  <!-- trang đầu -->
							<li><a href="manager?selectedPage=1">1</a></li>
							<li><p class="temp">...</p></li>
						</c:if>
						
						<c:forEach begin="${currentPage - 2}" end="${currentPage + 2}" var="i">
							<c:if test = "${i <= endPage}">
								<li class= ${currentPage == i ? "active" : ""}><a href="manager?selectedPage=${i}">${i}</a></li>
							</c:if>
						</c:forEach>
							
						<c:if test = "${currentPage < endPage - 2 && endPage > curentPage + 4}"> <!-- đưa về trang cuối -->
							<li><p class="temp">...</p></li>
							<li><a href="manager?selectedPage=${endPage}">${endPage}</a></li>
						</c:if>
						
						<c:if test = "${currentPage != endPage}">
							<li><a href="manager?selectedPage=${currentPage + 1}">&raquo;</a></li> <!-- trang kế -->
						</c:if>
					</c:if>
				</ul>
			</c:if>
		</div>
	</section> <!--/#cart_items-->
	</form>		 --%>
	
	<jsp:include page="footer.jsp"></jsp:include>

    <script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
    
    <script src="js/simple-datatables@latest.js"></script>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	if (datatableProduct) {
		        new simpleDatatables.DataTable("#datatableProduct", {
		            labels: {
		                placeholder: "Tìm kiếm ...",
		                perPage: "{select} Sản phẩm hiển thị",
		                noRows: "Không có sản phẩm",
		                info: "Hiển thị {start} tới {end} của {rows} sản phẩm",
		            }
		        });
		    }
	    });
    </script>
</body>
</html>