package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Category;
import beans.Publisher;
import beans.Sub;
import context.DBContext;

public class CategoryDAO {
	
	Connection conn = null; //kết nối sql
	PreparedStatement ps = null; //thực hiện lệnh
	ResultSet rs = null; //kết quả
	
	public List<Sub> getSubByCategory (int categoryId){
		
		List<Sub> list = new ArrayList<>();
		String query = "select * from Sub where cateId = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setInt(1,  categoryId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				list.add(new Sub(rs.getInt(1),
						rs.getString(2)));
			}
		} catch (Exception e) {
			
			
		}
		
		return list;
	}
	
	public List<Category> getAllCategory(){
		
		List<Category> list = new ArrayList<>();
		String query = "select * from Category";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Category temp = new Category(rs.getInt(1), rs.getString(2));
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		for (Category c : list) {
			
			c.setSubCate(getSubByCategory(c.getcId()));
		}
		
		return list;
	}
	
	public List<Category> getRandomCategory(){
		
		List<Category> list = new ArrayList<>();
		String query = "select top 3 * FROM Category order by newid()";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Category temp = new Category(rs.getInt(1), rs.getString(2));
				list.add(temp);
			}
		} catch (Exception e) {
			
			
		}
		
		for (Category c : list) {
			
			c.setSubCate(getSubByCategory(c.getcId()));
		}
		
		return list;
	}

	public String getSelectedCategoryName (String categoryId){
		
		String result = "";
		String query = "select categoryName from Category where id = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  categoryId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				result = rs.getString(1);
			}
		} catch (Exception e) {
			
			
		}
		
		return result;
	}
	
	public List<Publisher> getAllPublisher(){
		
		List<Publisher> list = new ArrayList<>();
		String query = "select * from Publisher";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				list.add(new Publisher(rs.getInt(1)
						, rs.getString(2)));
			}
		} catch (Exception e) {
			
			
		}
		
		return list;
	}
	
	public String getPublisherName (String publisherId){
		
		String result = "";
		String query = "select pubName from Publisher where id = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  publisherId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				result = rs.getString(1);
			}
		} catch (Exception e) {
			
			
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		CategoryDAO dao = new CategoryDAO();
		List<Category> list = dao.getRandomCategory();
		for (Category o : list) {
			
			System.out.println(o);
		}
	}
}
