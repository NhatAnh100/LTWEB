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
    <title>RING! - Đăng nhập</title>
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
	
	<jsp:include page="simpleNavigation.jsp"></jsp:include>
	
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Đăng nhập</h2>
						<p class = "text-danger">${loginMessage}</p>
						<p class = "text-success">${loginSuccess}</p>
						<form action="login" method = "post">
							<input type="text" name = "user" placeholder="Tên đăng nhập" required>
							<input type="password" name = "pass" placeholder="Mật khẩu" required>
							<span>
								<input type="checkbox" class="checkbox"> 
								Lưu đăng nhập
								<a href="forgot.jsp" class="pull-right">Quên mật khẩu?</a>
							</span>
							<br></br>
							<button type="submit" name = "loginBtn" value = "login"  class="btn btn-default">Đăng nhập</button>
						</form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">HOẶC</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
						<h2>Đăng ký tài khoản mới</h2>
						<p class = "text-danger">${signUpMessage}</p>
						<p class = "text-success">${signUpSuccess}</p>
						<form action="signup" method = "post">
							<input type="text" name = "userR" placeholder="Tên đăng nhập " required>
							<input type="email" name = "emailR" placeholder="Địa chỉ email" required>
							<input type="password" name = "passR" placeholder="Mật khẩu " required>
							<input type="password" name = "repassR" placeholder="Nhập lại mật khẩu" required>
							<br>
							<button type="submit" name = "signUpBtn" value = "signUp" class="btn btn-default">Đăng ký</button>
						</form>
					</div><!--/sign up form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
	
	<jsp:include page="footer.jsp"></jsp:include>	
  
    <script src="js/jquery.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>