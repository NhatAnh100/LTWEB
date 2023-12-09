package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import beans.Account;
import beans.Order;
import beans.OrderDetail;
import context.DBContext;

public class OrderDAO {

	Connection conn = null; //kết nối sql
	PreparedStatement ps = null; //thực hiện lệnh
	ResultSet rs = null; //kết quả
	
	public void insertOrder(Order order){
		
		String query = "insert into OrderReceipt values(?, ?, ?, ?, ?, ?, ?, getdate(), ?);";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			
			Account user = order.getUser();
			if (user != null) {
				
				ps.setInt(1, order.getUser().getId());
				ps.setString(2, order.getUser().getUsername());
			} else {
				
				ps.setNull(1, Types.INTEGER);
				ps.setString(2, null);
			}
			
			ps.setString(3, order.getFullName());
			ps.setString(4, order.getEmail());
			ps.setString(5, order.getPhone());
			ps.setString(6, order.getAddress());
			ps.setString(7, order.getMessage());
			ps.setDouble(8, order.getTotal());
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			
		}
	}
	
	public void insertOrderDetail (OrderDetail orderDetail, int orderId){
		
		String query = "insert into OrderDetail values(?, ?, ?, ?);";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setInt(2, orderDetail.getBook().getId());
			ps.setInt(3, orderDetail.getQuantity());
			ps.setDouble(4, orderDetail.getPrice());
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			
		}
	}
	
	public void checkout(Order order) {
		
		String query = "select IDENT_CURRENT('OrderReceipt')"; //chọn id của order vừa dc insert vào
		insertOrder(order);
		int insertedId = -1;
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				insertedId = rs.getInt(1);
			}
			
			for (OrderDetail o : order.getDetails()) {
				
				insertOrderDetail(o, insertedId);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public List<OrderDetail> getOrderByBooks(){ //for admin
		
		List<OrderDetail> list = new ArrayList<>();
		String query = "select top 10 bookid, sum(amount) as SL from OrderDetail group by bookid order by SL desc";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				OrderDetail temp = new OrderDetail();
				
				temp.setBookId(rs.getInt(1));
				temp.setQuantity(rs.getInt(2));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<OrderDetail> getOrderByMonths(){
		
		List<OrderDetail> list = new ArrayList<>();
		String query = "select month(o1.oDate) as thang, sum(o2.amount) as SL\r\n" + 
				"from OrderReceipt o1 join OrderDetail o2 on o1.id = o2.orderID\r\n" + 
				"group by month(o1.oDate)";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				OrderDetail temp = new OrderDetail();
				
				temp.setMonth(rs.getInt(1));
				temp.setQuantity(rs.getInt(2));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
}
