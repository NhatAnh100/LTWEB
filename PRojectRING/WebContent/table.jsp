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
    <title>RING! - Admin - Table</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet" />
    <link href="css/jquery-ui.min.css" rel="stylesheet" />
       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    
</head>

<body> 
    <jsp:include page="adminNavigation.jsp"></jsp:include>

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
		
		<c:if test="${managerMessage != null}">
	    	<div class="alert alert-danger alert-dismissible" role="alert">
				<span aria-hidden="true"><i class="fa fa-exclamation-triangle"></i></span>
				 ${managerMessage}
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	 					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
    
		<div class="row">
            <div class="manager-title">
                <h2><i class="fa fa-table">&nbsp;</i>Bảng dữ liệu</h2>
            </div>
		</div>
	</div> 

    <section>
        <div class="container">
			<div class="row">
				<div class="col-sm-5 col-md-7">
					<div class="manager-title">
						<h2><i class="fa fa-book" aria-hidden="true">&nbsp;</i>Quản lý sản phẩm</h2>
					</div>
				</div>
			
				<div class="col-sm-7 col-md-5">
					<div class="manager_search_box">
						<form action = "" method = "get">
							<button type="submit" form="managerform" name="rSelectedBookBtn" class="btn btn-default danger-action pull-right"><i class="fa fa-trash-o">&nbsp;</i>Xoá</button>
							<button type="button" onclick="confirmation('removeproduct?selectedAll=1')" class="btn btn-default danger-action pull-right"><i class="fa fa-trash-o">&nbsp;</i>Xoá tất cả</button>
						</form>
					</div>
				</div>
			</div> 

			<form id="managerform" action="removeproduct" method="post">
            <div class="row">
                <div class="panel panel-default mb-4">
                    <div class="panel-heading">
                        <i class="fa fa-book" aria-hidden="true">&nbsp;</i>
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
                                    <th>ID người bán</th>
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
                                    <th>ID người bán</th>
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
	                                    <td>${book.title}</td>
	                                    <td style="width: 10%; justify-content: center"><a href=""><img id="admin-product-img" src="${book.image}" alt=""></a></td>
	                                    <td>${book.price}đ</td>
	                                    <td>${book.quantity}</td>
	                                    <td>${book.sellerId}</td>
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
            </form>
            <div class="row">
				<div class="col-sm-5 col-md-7">
					<div class="manager-title">
						<h2><i class="fa fa-users" aria-hidden="true">&nbsp;</i>Quản lý tài khoản</h2>
					</div>
				</div>
			
				<div class="col-sm-7 col-md-5">
					<div class="manager_search_box">
						<form action = "" method = "get">
							<button type="button" onclick="updateRole()" name="rSelectedUserBtn" class="btn btn-default main-action pull-right"><i class="fa fa-floppy-o">&nbsp;</i>Lưu thay đổi</button>
							<button type="submit" form="accountform" name="rSelectedUserBtn" class="btn btn-default danger-action pull-right"><i class="fa fa-trash-o">&nbsp;</i>Xoá</button>
							<button type="button" onclick="confirmation('removeuser?selectedAll=1')" class="btn btn-default danger-action pull-right"><i class="fa fa-trash-o">&nbsp;</i>Xoá tất cả</button>
						</form>
					</div>
				</div>
			</div>

			<form id="accountform" action="removeuser" method="post">
			<input type="hidden" id="rUpdateRole" name="rUpdateRole" value="1">
            <div class="row">
                <div class="panel panel-default mb-4">
                    <div class="panel-heading">
                        <i class="fa fa-users" aria-hidden="true">&nbsp;</i>
                        Danh sách tài khoản
                    </div>
                    <div class="panel-body">
                        <table id="datatableAccount">
                            <thead>
                                <tr>
                                    <th>Chọn</th>
                                    <th>ID</th>
                                    <th>Tên đăng nhập</th>
                                    <th>Mật khẩu</th>
                                    <th>Email</th>
                                    <th>Bán hàng</th>
                                    <th>Admin</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Chọn</th>
                                    <th>ID</th>
                                    <th>Tên đăng nhập</th>
                                    <th>Mật khẩu</th>
                                    <th>Email</th>
                                    <th>Bán hàng</th>
                                    <th>Admin</th>
                                    <th>Hành động</th>
                                </tr>
                            </tfoot>
                            <tbody>
                            	<c:forEach items = "${listAccount}" var = "account">
									<tr>
										<td class="cart_select">
											<input type="checkbox" name="selectedUser" value="${account.id}">
										</td>
	                                    <td>${account.id}</td>
	                                    <td>${account.username}</td>
	                                    <td>${account.password}</td>
	                                    <td>${account.email}</td>
	                                    <td class="cart_select">
											<input type="checkbox" name="isSeller" value="${account.id}" ${account.isSeller == 1 ? "checked" : ""}>
										</td>
										<td class="cart_select">
											<input type="checkbox" name="isAdmin" value="${account.id}" ${account.isAdmin == 1 ? "checked" : ""}>
										</td>
	                                    <td class="cart_action">
											<a class="cart_edit" href="edituser?uid=${account.id}"><i class="fa fa-edit"></i></a>
											<a class="cart_delete" href="removeuser?removedUser=${account.id}"><i class="fa fa-times"></i></a>
										</td>
	                                </tr>
								</c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </section>
    
    <jsp:include page="footer.jsp"></jsp:include>

    <script src="js/jquery.js"></script>
    <script src="js/jquery-ui.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>

    <script src="js/datatables-modify.js"></script>
    <script src="js/simple-datatables@latest.js"></script>
    
    <script type="text/javascript">
    //Hộp thoại confirm
    function confirmation(url) {
   	  let text = "Xác nhận xoá?";
   	  if (confirm(text) == true) {
   		  window.location.href = url;
   	  } else {
   	  }
   	}
    
    //Xử lý action khác trong accountform
    function updateRole(){
    	document.getElementById("accountform").action = 'edituser';
    	$("#accountform").submit();
    }
    </script>
</body>
</html>