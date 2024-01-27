<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-sm-3">
	<div class="left-sidebar">
		<h2>Danh sách sản phẩm</h2>
		<div class="panel-group category-products" id="accordian"><!--category-productsr-->
		
		<c:forEach items = "${listCategory}" var = "category">
			
			<c:if test="${category.subCate.size() != 0}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordian" href="#${category.cId}">
								<span class="badge pull-right"><i class="fa fa-plus"></i></span>
								<a href="category?cid=${category.cId}">${category.cName}</a>
							</a>
						</h4>
					</div>
					<div id="${category.cId}" class="panel-collapse collapse">
						<div class="panel-body">
							<ul>
								<c:forEach items="${category.subCate}" var="sub">
									<li><a href="category?cid=${category.cId}">${sub.sName}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
			
			<c:if test="${category.subCate.size() == 0}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title"><a href="category?cid=${category.cId}">${category.cName}</a></h4>
					</div>
				</div>
			</c:if>
		</c:forEach>
		</div><!--/category-productsr-->
	
		<div class="brands_products"><!--brands_products-->
			<h2>Nhà xuất bản</h2>
			<div class="brands-name">
				<ul class="nav nav-pills nav-stacked">
					<c:forEach items = "${listPublisher}" var = "pub">
						<li><a href="publisher?pid=${pub.pId}">${pub.pName}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div><!--/brands_products-->
		
		<div class="price-range"><!--price-range-->
			<h2>Tầm giá</h2>
			<div class="well">
				<div class="slider-main">
					<input type="text" class="span2" value="" data-slider-min="0" data-slider-max="1000000" data-slider-step="5" data-slider-value="[0,200000]" id="sl2" ><br />
				</div>
				 <b>0đ</b> <b class="pull-right">1000000đ</b>
			</div>
		</div><!--/price-range-->
		
		<div class="mini_ad text-center"><!--shipping-->
			<img src="images/home/ad.jpg" alt="" />
		</div><!--/shipping-->
		
		<br><br>
	</div>
</div>