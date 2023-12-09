package beans;

public class OrderDetail {
	
	private Book book;
	private int quantity;
	private double price;
	
	//Admin
	private int month;
	private int bookId;
	
	public OrderDetail(Book book, int quantity, double price) {
		
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}
	
	public OrderDetail() {
		
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
}
