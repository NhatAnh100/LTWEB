package beans;

public class Account {

	private int id;
	private String username;
	private String password;
	private String email;
	private int isSeller;
	private int isAdmin;
	private int totalBooks = 0;
	
	public Account(int id, String username, String password, String email, int isSeller, int isAdmin) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.setEmail(email);
		this.isSeller = isSeller;
		this.isAdmin = isAdmin;
	}
	
	public Account() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getIsSeller() {
		return isSeller;
	}
	
	public void setIsSeller(int isSeller) {
		this.isSeller = isSeller;
	}
	
	public int getIsAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setTotalBooks(int total) {
		this.totalBooks = total;
	}
	
	public int getTotalBooks() {
		return this.totalBooks;
	}
	
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", isSeller=" + isSeller + ", isAdmin=" + isAdmin + "]";
	}
}
