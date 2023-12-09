package beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private int id;
	private Account user;
	private String fullName;
	private String email;
	private String phone;
	private String address;
	private String message;
	private Timestamp date;
	private double total;
	private List<OrderDetail> details;
	
	public Order(int id, Account user, String fullName, String email, String phone, String address,
			String message, Timestamp date, double total) {
		
		this.id = id;
		this.setUser(user);
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.message = message;
		this.date = date;
		this.total = total;
		this.details = new ArrayList<OrderDetail>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", message=" + message + ", date=" + date + ", total=" + total + "]";
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}
	
	public void addDetails(OrderDetail detail) {
		
		this.details.add(detail);
	}
}
