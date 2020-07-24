package Book;

public class Book {
	// 제목 , ISBN 번호, 저자, 판매자, id
	// <Book> 형태의 ArrayList에 책의 정보를 누적시킴
	private String bookTitle;
	private String bookISBN;
	private String bookAuthor;
	private String bookPublisher;
	private String bookPubYear;
	private String bookPrice;
	private String bookStatus;
	private String bookSeller;
	private String userID;
	private String sale;
	
	public Book() {	
		bookTitle = "";
		bookISBN = "";
		bookAuthor = "";
		bookPublisher = "";
		bookPubYear = "";
		bookPrice = "";
		bookStatus = "";
		bookSeller = "";
		userID = "";
		sale = "OK";
	}
	
	public String getbookTitle() {
		return bookTitle;
	}
	
	public void setbookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getbookISBN() {
		return bookISBN;
	}

	public void setbookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public String getbookAuthor() {
		return bookAuthor;
	}

	public void setbookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	public String getbookPublisher() {
		return bookPublisher;
	}

	public void setbookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	
	public String getbookPubYear() {
		return bookPubYear;
	}

	public void setbookPubYear(String bookPubYear) {
		this.bookPubYear = bookPubYear;
	}
	
	public String getbookPrice() {
		return bookPrice;
	}
	
	public void setbookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	public String getbookStatus() {
		return bookStatus;
	}
	
	public void setbookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	
	public String getbookSeller() {
		return bookSeller;
	}

	public void setbookSeller(String bookSeller) {
		this.bookSeller = bookSeller;
	}

	public String getuserID() {
		return userID;
	}

	public void setuserID(String userID) {
		this.userID = userID;
	}
	
	public String getSale() {
		return sale;
	}
	
	public void setSale(String sale) {
		this.sale = sale;
	}
	
}

