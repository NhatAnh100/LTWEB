package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.Product;
import beans.ProductDetail;
import beans.Review;
import context.DBContext;

public class ProductDAO {
	
	Connection conn = null; //kết nối sql
	PreparedStatement ps = null; //thực hiện lệnh
	ResultSet rs = null; //kết quả
	
	public List<Review> getAllReviewByproduct (String productId){
		
		List<Review> list = new ArrayList<>();
		String query = "select * from Review where productID = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1, productId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				list.add(new Review(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getTimestamp(7)));
			}
		} catch (Exception e) {
			
			
		}
		
		return list;
	}
	
	public List<Review> getReviewPagingByproduct (String productId, int page, int total){
		
		List<Review> list = new ArrayList<>();
		String query = "select * from Review\r\n" + 
				"where productID = ?\r\n" + 
				"order by id\r\n" + 
				"offset ? rows fetch next ? rows only";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1, productId);
			ps.setInt(2, (page - 1) * total);
			ps.setInt(3, total);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				list.add(new Review(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getTimestamp(7)));
			}
		} catch (Exception e) {
			
			
		}
		
		return list;
	}
	
	public int getTotalReviewByproduct (String productId){
		
		String query = "select count(*) from Review where productID = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1, productId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				return rs.getInt(1);
			}
		} catch (Exception e) {
			
			
		}
		
		return 0;
	}
	
	public int getReviewTotalRatingByproduct (String productId){
		
		String query = "select sum(rating) from review where productId = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1, productId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				return rs.getInt(1);
			}
		} catch (Exception e) {
			
			
		}
		
		return 0;
	}
	
	public void setRating(List<Product> list) {
		
		for (Product b : list) {
			
			String id = String.valueOf(b.getId());
			b.setRating(getReviewTotalRatingByproduct(id), getTotalReviewByproduct(id));
		}
	}
	
	public void review(Review review){
		
		String query = "insert into Review values(?, ?, ?, ?, ?, getdate());";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setInt(1, review.getUserId());
			ps.setString(2, review.getUsername());
			ps.setInt(3, review.getproductId());
			ps.setString(4, review.getContent());
			ps.setInt(5, review.getRating());
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			
		}
	}
	
	public List<Product> getAllproduct(){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product temp = new Product(rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
				
				temp.setSellerId(rs.getString(11));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		setRating(list);
		return list;
	}
	
	public int getTotalproduct (){
		
		String query = "select count(*) from product";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				return rs.getInt(1);
			}
		} catch (Exception e) {
			
			
		}
		
		return 0;
	}
	
	public List<Product> getAllproductPaging (int page, int total){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product\r\n" + 
				"order by id\r\n" + 
				"offset ? rows fetch next ? rows only";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setInt(1, (page - 1) * total);
			ps.setInt(2, total);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product temp = new Product(rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		setRating(list);
		return list;
	}
	
	public List<Product> getRandomproduct(){
		
		List<Product> list = new ArrayList<>();
		String query = "select top 8 * FROM product order by newid()";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
			
				Product temp = new Product(rs.getInt(1),
				rs.getString(2),
				rs.getDouble(3),
				rs.getInt(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7),
				rs.getString(8),
				rs.getString(9));
		
				list.add(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Product> getRecommendproduct(){
		
		List<Product> list = new ArrayList<>();
		String query = "select top 8 b.*, count(o.productID) as SL from product b join orderdetail o on b.id = o.productID\r\n" + 
				"group by b.id, b.image, b.price, b.amount, b.title, b.description, b.author, b.publisher, b.cateID, b.sellID\r\n" +
				"order by count(o.productID) desc";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
			
				Product temp = new Product(rs.getInt(1),
				rs.getString(2),
				rs.getDouble(3),
				rs.getInt(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7),
				rs.getString(8),
				rs.getString(9));
		
				list.add(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Product> getNextproductByCategory(String cId, int amount){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product where cateID = ?\r\n" + 
				"order by id\r\n" + 
				"offset ? rows\r\n" + 
				"fetch next 4 rows only";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1, cId);
			ps.setInt(2, amount);
			rs = ps.executeQuery();
			
			while(rs.next()) {
			
				Product temp = new Product(rs.getInt(1),
				rs.getString(2),
				rs.getDouble(3),
				rs.getInt(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7),
				rs.getString(8),
				rs.getString(9));
		
				list.add(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Product> getNextproduct(int amount){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product\r\n" + 
				"order by id\r\n" + 
				"offset ? rows\r\n" + 
				"fetch next 3 rows only";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			rs = ps.executeQuery();
			
			while(rs.next()) {
			
				Product temp = new Product(rs.getInt(1),
				rs.getString(2),
				rs.getDouble(3),
				rs.getInt(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7),
				rs.getString(8),
						rs.getString(9));
		
				list.add(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		setRating(list);
		return list;
	}
	
	public List<Product> getproductByCategory(String categoryId){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product where cateId = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  categoryId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product temp = new Product(rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		setRating(list);
		return list;
	}
	
	public int getTotalproductByCategory (String categoryId){
		
		String query = "select count(*) from product where cateId = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  categoryId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				return rs.getInt(1);
			}
		} catch (Exception e) {
			
			
		}
		
		return 0;
	}

	public int getTotalproductByPublisher (String pubId){
		
		String query = "select count(*) from product where publisher = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  pubId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				return rs.getInt(1);
			}
		} catch (Exception e) {
			
			
		}
		
		return 0;
	}
	
	public List<Product> getproductPagingByCategory (String categoryId, int page, int total){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product\r\n" + 
				"where cateID = ?\r\n" + 
				"order by id\r\n" + 
				"offset ? rows fetch next ? rows only";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  categoryId);
			ps.setInt(2, (page - 1) * total);
			ps.setInt(3, total);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product temp = new Product(rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		setRating(list);
		return list;
	}
	
	public List<Product> getproductPagingByPublisher (String pubId, int page, int total){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product\r\n" + 
				"where publisher = ?\r\n" + 
				"order by id\r\n" + 
				"offset ? rows fetch next ? rows only";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  pubId);
			ps.setInt(2, (page - 1) * total);
			ps.setInt(3, total);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product temp = new Product(rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		setRating(list);
		return list;
	}
	
	public List<Product> getproductFromSearch(String searchString){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product where title like ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  "%" + searchString + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product temp = new Product(rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		setRating(list);
		return list;
	}
	
	public int getTotalproductFromSearch (String searchString){
		
		String query = "select count(*) from product where title like ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  "%" + searchString + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				return rs.getInt(1);
			}
		} catch (Exception e) {
			
			
		}
		
		return 0;
	}
	
	public List<Product> getproductPagingFromSearch (String searchString, int page, int total){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product\r\n" + 
				"where title like ?\r\n" + 
				"order by id\r\n" + 
				"offset ? rows fetch next ? rows only";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  "%" + searchString + "%");
			ps.setInt(2, (page - 1) * total);
			ps.setInt(3, total);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product temp = new Product(rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
					
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		setRating(list);
		return list;
	}
	
	public Product getproductById(String id){
		
		String query = "select * from product where id = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product temp = new Product(rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));

				temp.setRating(getReviewTotalRatingByproduct(id), getTotalReviewByproduct(id));
				return temp;
			}
		} catch (Exception e) {
			
			
		}
		
		return null;
	}
	
	public List<Product> getAllproductBySeller(int id){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product where sellID = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setInt(1,  id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product temp = new Product(rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		return list;
	}
	
	public int getTotalproductBySeller(int id){
		
		String query = "select count(*) from product where sellID = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setInt(1,  id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				return rs.getInt(1);
			}
		} catch (Exception e) {
			
			
		}
		
		return 0;
	}
	
	public List<Product> getproductPagingBySeller (int id, int page, int total){
		
		List<Product> list = new ArrayList<>();
		String query = "select * from product\r\n" + 
				"where sellID = ?\r\n" + 
				"order by id\r\n" + 
				"offset ? rows fetch next ? rows only";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setInt(1,  id);
			ps.setInt(2, (page - 1) * total);
			ps.setInt(3, total);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Product temp = new Product(rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
					
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		return list;
	}
	
	public void deleteproduct (String id) {
		
		String query = "delete from productDetail where productID = ?\r\n" + 
				"alter table Review nocheck constraint all\r\n" + 
				"alter table OrderDetail nocheck constraint all \r\n" + 
				"delete from product where id = ?\r\n" + 
				"alter table Review check constraint all \r\n" + 
				"alter table OrderDetail check constraint all";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  id);
			ps.setString(2,  id);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteAllproductBySeller (int seller) {
		
		String query = "delete from productdetail where productID in\r\n" + 
				"(select productID from product b join productdetail b2 on b.id = b2.productID where sellID = ?)\r\n" + 
				"alter table Review nocheck constraint all\r\n" + 
				"alter table OrderDetail nocheck constraint all \r\n" + 
				"delete from product where sellID = ?\r\n" + 
				"alter table Review check constraint all \r\n" + 
				"alter table OrderDetail check constraint all";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setInt(1, seller);
			ps.setInt(2, seller);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteAllproduct () {
		
		String query = "delete from productdetail where productID in\r\n" + 
				"(select productID from product b join productdetail b2 on b.id = b2.productID)\r\n" + 
				"alter table Review nocheck constraint all\r\n" + 
				"alter table OrderDetail nocheck constraint all \r\n" + 
				"delete from product\r\n" + 
				"alter table Review check constraint all \r\n" + 
				"alter table OrderDetail check constraint all";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void insertproduct (Product product, int seller) {
		
		String query = "insert into product(image, price, amount, title, description, author, publisher, cateID, sellID) \r\n" +
				"values (?, ? ,? ,?  ,? ,? ,? ,? ,?);";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1, product.getImage());
			ps.setDouble(2, product.getPrice());
			ps.setInt(3, product.getQuantity());
			ps.setString(4, product.getTitle());
			ps.setString(5, product.getDescription());
			ps.setString(6, product.getAuthor());
			ps.setString(7, product.getPublisher());
			ps.setString(8, product.getCateId());
			ps.setInt(9, seller);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void insertproductDetail (Timestamp date, String madeIn, int productId) {
		
		String query = "insert into productDetail(productID, pDate, pMadeIn) \r\n" +
				"values (?, ? ,? ,? ,?, ?);";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setTimestamp(2, date);
			ps.setString(3, madeIn);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addproduct(Product product, Timestamp date, String madeIn, int seller) {
		
		String query = "select IDENT_CURRENT('product')"; //chọn id của order vừa dc insert vào
		insertproduct(product, seller);
		int insertedId = -1;
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				insertedId = rs.getInt(1);
			}
			
			insertproductDetail( date, madeIn, insertedId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public ProductDetail getproductDetailByproduct (String id){
		
		String query = "select * from productDetail where productID = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ProductDetail temp = new ProductDetail(
						rs.getTimestamp(2),
						rs.getString(3));
				
				return temp;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void updateproduct (Product product) {
		
		String query = "update product\r\n" + 
				"set [image] = ?,\r\n" + 
				"price = ?,\r\n" + 
				"amount = ?,\r\n" + 
				"title = ?,\r\n" + 
				"[description] = ?,\r\n" + 
				"author = ?,\r\n" +
				"publisher = ?,\r\n" + 
				"cateID = ?\r\n" + 
				"where id = ?";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1, product.getImage());
			ps.setDouble(2, product.getPrice());
			ps.setInt(3, product.getQuantity());
			ps.setString(4, product.getTitle());
			ps.setString(5, product.getDescription());
			ps.setString(6, product.getAuthor());
			ps.setString(7, product.getPublisher());
			ps.setString(8, product.getCateId());
			ps.setInt(9, product.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void updateproductDetail (ProductDetail productDetail, int id) {
		
		String query = "update productDetail\r\n" + 
				"set " +
				"pDate = ?,\r\n" +
				"pMadeIn = ?\r\n" +
				"where productID = ?";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setTimestamp(1, productDetail.getDate());
			ps.setString(2, productDetail.getMadeIn());
			ps.setInt(3, id);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void editproduct(Product product, ProductDetail productDetail) {
		
		updateproduct(product);
		updateproductDetail(productDetail,  product.getId());
	}
	
	public static void main(String[] args) {
		
		ProductDAO dao = new ProductDAO();
		
		List<Product> list2 = dao.getproductPagingFromSearch("", 1, 15);
		for (Product o : list2) {
			
			System.out.println(o);
		}
		
		List<Review> list3 = dao.getAllReviewByproduct("1");
		for (Review o : list3) {
			
			System.out.println(o);
		}
	}
}
